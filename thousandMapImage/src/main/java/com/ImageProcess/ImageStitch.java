package com.ImageProcess;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author TR
 * @Create 2019年12月31日
 * @Title: ImageStitch
 * @Description: 图片合成
 */
public class ImageStitch {

    // 合并所有图片 要求图片规格一样
    public BufferedImage mergeAllImage(BufferedImage img[],int[][] imageIndex,int width,int height) throws IOException {

        int rows = imageIndex.length;// 行数
        int cols = imageIndex[0].length;// 列数
        // 生成新图片
        BufferedImage DestImage = new BufferedImage(cols * width, rows*height, BufferedImage.TYPE_INT_RGB);
        int startHeight = 0;
        for(int i=0;i<imageIndex.length;i++){// 一行一行拼
            int[] ImageRgb = new int[width * height];
            int startWidth = 0; // width
            for(int j= 0;j<imageIndex[i].length;j++){
                ImageRgb = img[imageIndex[i][j]].getRGB(0, 0, width, height, ImageRgb, 0, width);
                DestImage.setRGB(startWidth, startHeight, width, height, ImageRgb, 0, width); // 设置上半部分或左半部分的RGB
                startWidth += width;
            }
            startHeight += height;
        }
        return DestImage;
    }

    /**
     * 待合并的两张图必须满足这样的前提，如果水平方向合并，则高度必须相等；如果是垂直方向合并，宽度必须相等。
     * mergeImage方法不做判断，自己判断。
     * @param img1   待合并的第一张图
     * @param img2   带合并的第二张图
     * @param isHorizontal  为true时表示水平方向合并，为false时表示垂直方向合并
     * @return 返回合并后的BufferedImage对象
     * @throws IOException
     */
    public BufferedImage mergeImage(BufferedImage img1, BufferedImage img2, boolean isHorizontal) throws IOException {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

        // 生成新图片
        BufferedImage DestImage = null;
        if (isHorizontal) { // 水平方向合并
            DestImage = new BufferedImage(w1+w2, h1, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
        } else { // 垂直方向合并
            DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
        }

        return DestImage;
    }



}
