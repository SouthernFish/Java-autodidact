package com.ImageProcess;

import java.awt.image.BufferedImage;

/**
 * @Author TR
 * @Create 2019年12月31日
 * @Title: ImageHSV
 * @Description: 图片HSV
 */
public class ImageHSV {

    /*
     * RGB转HSV(表示 hue(色相)、saturation(饱和度)、value(色调) )
     * 输入范围 R,G,B, 0~255 输出范围[0]:0~1,[1]:0~1,[2]:0~255
     */
    public float[] rgb2hsv(int R, int G, int B){

        int max=Math.max(R, Math.max(G,B)); // R G B最大值
        int min=Math.min(R, Math.min(G,B)); // R G B最小值
        float hsv[] = {0,0,0}; // 返回的HSV 数组
        if(0 == max){ // 全0时 返回[0,0,0]
            return hsv;
        }

        float H = 0;
        float delta = max - min;
        float S = delta / max;
        float V = max;

        if(0 == delta){
            H = 0;
        }else if(G == max ){
            H = 2 + (B - R)  / delta; // between cyan & yellow 蓝绿色与黄色之间
        }else if(B == max){
            H = 4 + (R - G)  / delta; // between magenta & cyan		 品红洋红与蓝绿色之间
        }else if(R == max){
            H =  (G - B) / delta;     // between yellow & magenta 品红洋红与黄色之间
        }

        H *= 60;
        if(H < 0 ){
            H += 360;
        }

        hsv[0] = H/360.0f;
        hsv[1] = S;
        hsv[2] = V;
        return hsv;
    }

    // 图片的平均HSV 返回长度为3的一维数组
    public float[]  getAverageHSV(BufferedImage image){
        int R,G,B;
        float H = 0, S = 0, V = 0;
        int width =  image.getWidth();
        int height =  image.getHeight();
        int length = width * height;
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int RGB = image.getRGB(i,j);// 图片 rgb
                R = (RGB & 0xff0000) >> 16;
                G = (RGB & 0xff00) >> 8;
                B = (RGB & 0xff);
                float f[] = rgb2hsv(R, G, B);
                H += f[0];
                S += f[1];
                V += f[2];
            }
        }
        float averageHsv[] = new float[3];
        averageHsv[0] = H/length;
        averageHsv[1] = S/length;
        averageHsv[2] = V/length;
        return averageHsv;
    }

    // 图片HSV
    public float[][][]  getImageHSV(BufferedImage image){

        int R,G,B;
        int width =  image.getWidth();
        int height =  image.getHeight();
        float hsv[][][] = new float[width][height][3];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int RGB = image.getRGB(i,j);  // 具体位置对应的 rgb
                R = (RGB & 0xff0000) >> 16;
                G = (RGB & 0xff00) >> 8;
                B = (RGB & 0xff);
                float f[] = rgb2hsv(R, G, B);
                hsv[i][j][0] =  f[0];
                hsv[i][j][1] =  f[1];
                hsv[i][j][2] =  f[2];
            }
        }
        return hsv;
    }

    // 图片HSV
    public float[][][]  getImageDivisionHSV(BufferedImage image, int width, int height){
        int R,G,B;
        int cols=  image.getWidth()/width;
        int rows =  image.getHeight()/height;
        int length = width * height;
        float hsv[][][] = new float[rows][cols][3];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                int w = j * width + width;
                int h = i * height + height;
                float H = 0, S = 0, V = 0;
                for(int c = j*width; c < w; c++){
                    for(int r = i*height; r< h; r++){
                        int RGB = image.getRGB(c,r);  // 具体位置对应的 rgb
                        R = (RGB & 0xff0000) >> 16;
                        G = (RGB & 0xff00) >> 8;
                        B = (RGB & 0xff);
                        float f[] = rgb2hsv(R, G, B);
                        H += f[0];
                        S += f[1];
                        V += f[2];
                    }
                }
                hsv[i][j][0] =  H/length;
                hsv[i][j][1] =  S/length;
                hsv[i][j][2] =  V/length;
            }
        }

        return hsv;
    }

    // 颜色相似度 （类似余弦相似度）sqrt(Math.pow(hi-hj,2)+Math.pow(si-sj,2)+Math.pow(vi-vj,2))
    public double similarity(float[] HSV,float[] hsv){
        // 平方和开方
        return  Math.sqrt(Math.pow(HSV[0]-hsv[0],2)+Math.pow(HSV[1]-hsv[1],2)+Math.pow(HSV[2]-hsv[2],2));

    }

}
