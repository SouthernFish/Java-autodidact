package com.ImageProcess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * @Author TR
 * @Create 2019年12月31日
 * @Title: ImageCompress
 * @Description: 图像压缩
 */
public class ImageCompress {

    /**
     * @Description 实现图片的等比缩放和缩放后的截取
     * @param inFilePath 要截取文件流 saveFile 截取后输出的路径
     * @param width 要截取宽度 height 要截取的高度
     * @return  BufferedImage
     */
    public BufferedImage compress(File inFilePath, File saveFile, int width, int height) {
        BufferedImage srcImage = null;
        InputStream in = null;
        try {
            in = new FileInputStream(inFilePath);
            srcImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 缩放
        if (width > 0 || height > 0) {
/*        	// 原图的大小
            int sw = srcImage.getWidth();
            int sh = srcImage.getHeight();
            if (sw > width && sh > height) {
                srcImage = resize(srcImage, width, height); //  缩放到指定大小
            } else { // 如果原图片的大小小于要缩放的图片大小，直接将要缩放的图片复制过去
                String fileName = saveFile.getName(); // 图片名字
                String formatName = fileName.substring(fileName.lastIndexOf('.') + 1); // 图片格式
                try {
                	ImageIO.write(srcImage, formatName, saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return srcImage;
            }*/
            srcImage = resize(srcImage, width, height); //  缩放到指定大小
        }

        // 缩放后的图片的宽和高
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();
        // 如果缩放后的图片和要求的图片宽度一样，就对缩放的图片的高度进行截取
        if (w == width) {
            // 计算X轴坐标
            int x = 0;
            int y = h / 2 - height / 2;
            try {
                srcImage = saveSubImage(srcImage, new Rectangle(x, y, width, height), saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (h == height) {// 否则如果是缩放后的图片的高度和要求的图片高度一样，就对缩放后的图片的宽度进行截取
            // 计算X轴坐标
            int x = w / 2 - width / 2;
            int y = 0;
            try {
                srcImage = saveSubImage(srcImage, new Rectangle(x, y, width, height), saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return srcImage;
    }

    /**
     * @Description 实现图片的等比缩放
     * @param source 原图 targetW 目标宽 targetH 目标高
     * @return  BufferedImage
     */
    private  BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (sx < sy) {   // 实现等比缩放 如果不需要等比缩放将if else语句注释即可
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPreMultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPreMultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D drawImage = target.createGraphics();   // smoother than exlax
        drawImage.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawImage.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        drawImage.dispose();
        return target;
    }

    /**
     * @Description 截图
     * @param image 原始图片
     * @param subImageBounds 要截取的子图的范围
     * @param subImageFile 要保存的文件
     * @return  BufferedImage
     */
    private  BufferedImage saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile) throws IOException {
        if (subImageBounds.x < 0 || subImageBounds.y < 0
                || subImageBounds.width - subImageBounds.x > image.getWidth()
                || subImageBounds.height - subImageBounds.y > image.getHeight()) {
            return null;
        }
        BufferedImage subImage = image.getSubimage(subImageBounds.x,subImageBounds.y, subImageBounds.width, subImageBounds.height);
        String fileName = subImageFile.getName();
        String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);   // 保存的格式 也就是原图的格式
        ImageIO.write(subImage, formatName, subImageFile);
        return subImage;
    }

    /**
     * 直接指定压缩后的宽高：
     * (先保存原文件，再压缩、上传)
     * @param oldFile 要进行压缩的文件全路径
     * @param width 压缩后的宽度
     * @param height 压缩后的高度
     * @param quality 压缩质量
     * @return 返回压缩后的文件的全路径 File inFilePath, File saveFile, int width, int height
     */
    public static BufferedImage zipImageFile(File oldFile, File saveFile, int width, int height, float quality) {
        try {
            /**对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
            /** 压缩之后临时存放位置 */
            FileOutputStream out = new FileOutputStream(saveFile.getPath());
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            out.close();
            return ImageIO.read(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     * @param srcURL 原图地址
     * @param deskURL 缩略图地址
     * @param comBase 压缩基数
     * @param scale 压缩限制(宽/高)比例  一般用1：
     * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
     *  ImageCompressUtil.saveMinPhoto("f:/食尸鬼 - 藿香.jpg", "f:/11.jpg", 139, 0.9d);
     */
    public static void saveMinPhoto(String srcURL, String deskURL, double comBase, double scale) throws Exception {
        File srcFile = new java.io.File(srcURL);
        Image src = ImageIO.read(srcFile);
        int srcHeight = src.getHeight(null);
        int srcWidth = src.getWidth(null);
        int deskHeight = 0;// 缩略图高
        int deskWidth = 0;// 缩略图宽
        double srcScale = (double) srcHeight / srcWidth;
        /**缩略图宽高算法*/
        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
            if (srcScale >= scale || 1 / srcScale > scale) {
                if (srcScale >= scale) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            } else {
                if ((double) srcHeight > comBase) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            }
        } else {
            deskHeight = srcHeight;
            deskWidth = srcWidth;
        }
        BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
        tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
        FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
        encoder.encode(tag); //近JPEG编码
        deskImage.close();
    }

}
