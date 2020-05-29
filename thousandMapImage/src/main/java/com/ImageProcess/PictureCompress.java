package com.ImageProcess;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description: 图片压缩
 */
public class PictureCompress {
    /**
     * 实现图片的等比缩放
     * @param source  原图
     * @param targetW 目标宽
     * @param targetH   目标高
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
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D drawImage = target.createGraphics();   // smoother than exlax
        drawImage.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawImage.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        drawImage.dispose();
        return target;
    }

    /**
     * 实现图片的等比缩放和缩放后的截取, 处理成功返回true, 否则返回false
     * @param inFilePath 要截取文件流
     * @param saveFile 截取后输出的路径
     * @param width 要截取宽度
     * @param height 要截取的高度
     * @throws Exception
     */
    public  BufferedImage compress(File inFilePath, File saveFile,  int width, int height) {
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
     * 截图
     * @param image 原始图片
     * @param subImageBounds 要截取的子图的范围
     * @param subImageFile 要保存的文件
     * @throws IOException
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
}
