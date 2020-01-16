package com.ImageProcess;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author TR
 * @Create 2019年12月31日
 * @Title: SourceImageProcess
 * @Description: 资源图片处理
 */
public class SourceImageProcess {

    public File[] imagePathList; // 图库
    public String saveBasePath; // 缩略图保存的基础路径
    public BufferedImage targetImage; // 目标图片
    public int height;// 碎片大小 height * width
    public int width;
    public float clubHsv[][] = null; // 缩略图 图库图片的平均HSV数组
    
    public SourceImageProcess(File imageClubPath,BufferedImage targetImage, int height, int width){
        this.height = height;
        this.width = width;
        this.targetImage = targetImage;
        this.imagePathList = imageClubPath.listFiles();
        /*SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        Date date = new Date(System.currentTimeMillis());formatter.format(date)*/
        this.saveBasePath = imageClubPath.getParent()+"/clip";
    }

    
    // 图库图片缩放、裁剪缩放 获取缩略图的HSV和目标图片的HSV
    public BufferedImage[] clipPicture(){
        int clubSize = this.imagePathList.length; //  原料图片个数 图库大小
        BufferedImage imageClub[] = new BufferedImage[clubSize];    //  初始化缩略图片的数组
        this.clubHsv = new float[clubSize][3]; //  初始化图片的 平均HSV 数组
        ImageCompress imageCompress = new ImageCompress();
        ImageHSV imageHSV = new ImageHSV();
        File saveClubPath = new File(saveBasePath+"/"+this.width+"x"+this.height);
        if(!saveClubPath.exists()){
            saveClubPath.mkdirs();
        }
        //
        for (int i = 0; i < clubSize; i++) {
            File saveImagePath  = new File(saveClubPath+"/"+imagePathList[i].getName());// 缩略图保存全路径
            //imageClub[i] = imageCompress.compress(imagePathList[i],saveImagePath,this.width,this.height); // ***
            imageClub[i] = imageCompress.zipImageFile(imagePathList[i],saveImagePath,this.width, this.height,1f);
            this.clubHsv[i] = imageHSV.getAverageHSV(imageClub[i]);// 缩略图的平均HSV数组
        }
        // 图库的hsv保存到Excel
        String fileName = saveBasePath+"/hsv.xls";
        String sheetName = this.width+"x"+this.height;
        ImageHsvRedis redis = new ImageHsvRedis();
        redis.saveHsvClub(this.clubHsv,fileName,sheetName);
        return imageClub;
    }

    // 图库图片替换 碎片 替换图片的下标
    public int[][] parse(){
        ImageHSV imageHSV = new ImageHSV();
//        目标图片的HSV数组 目标图片的size 一个像素 一个HSV [targetImage.width][targetImage.height][3]
//        HSV = imageHSV.getImageHSV(this.targetImage);
        float HSV[][][] = imageHSV.getImageDivisionHSV(this.targetImage, width, height); // 目标图片的HSV数组
        int replaceIndex[][] = new int[HSV.length][HSV[0].length];
        for(int i=0; i< HSV.length;i++){
            for(int j = 0; j<HSV[i].length; j++){
                double similarity = 0;
                double min = 65535;
                for(int k = 0; k<this.clubHsv.length; k++){
                    similarity = imageHSV.similarity(HSV[i][j],this.clubHsv[k]);
                    if(similarity <=  min){
                        min = similarity;
                        replaceIndex[i][j] = k;
                    }
                }
            }
        }
        return replaceIndex;
    }

    // 图片格式转换
    public WritableImage convertToImage(BufferedImage bufferedImage){
        WritableImage writableImage = null;
        if (bufferedImage != null) {
            writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = writableImage.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                }
            }
        }
        return writableImage;
    }

    // 合并拼接 生成图片
    public BufferedImage getResultImage(){
        try {
            File saveClubPath = new File(saveBasePath+"/"+this.width+"x"+this.height);
            BufferedImage imageClub[] = null;
            if(!saveClubPath.exists()){
                imageClub = clipPicture();// 裁剪
            }else{// 读取缓存
                ImageHsvRedis redis = new ImageHsvRedis();
                String fileName = saveBasePath+"/hsv.xls";
                String sheetName = this.width+"x"+this.height;
                this.clubHsv = redis.readHsvClub(fileName, sheetName);
                imageClub = redis.readImageClub(saveClubPath);
            }
            int[][] imageIndex = parse();// 替换
            ImageStitch imageStitch = new ImageStitch();
            return imageStitch.mergeAllImage(imageClub,imageIndex,this.width,this.height);// 合成
        } catch (Exception e) {
            e.printStackTrace();
            return this.targetImage;
        }
    }

}
