package com.ImageProcess;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description: 图片处理
 */
public class PictureProcess {

    public static int height= 16;// 处理后图片大小 height * width
    public static int width= 9;

    public BufferedImage targetPicture = null; // 目标图片 缩放至1024*576
    public BufferedImage targetResize = null; // 目标图片 缩放至1024*576
    public File[] imagePathList; // 图库
    public String saveBasePath; // 缩略图保存的基础路径

    public BufferedImage imageClub[] = null; // 缩放剪裁剪后的图片
    public float clubHsvs[][] = null; // 缩略图 图库图片的平均HSV数组

    public float HSV[][][] = null; // 目标图片的HSV数组

    // 缩放图库图片
    public PictureProcess(File targetImage, File[] imagePathList, String saveBasePath) throws IOException {
        this.targetPicture = ImageIO.read(targetImage);   //  读入目标图片（要拼的图片）
        File save = new File("F:\\thousandMapImaging\\targetImage\\yexiu_re2.jpg");
        PictureCompress imageCompress = new PictureCompress();
        this.targetResize = imageCompress.compress(targetImage,save,576,1024); // ***
//		saveBasePath = "F:\\thousandMapImaging\\resourceMultithread\\compress\\";
        this.saveBasePath = saveBasePath;  // 缩略图保存路径
        this.imagePathList = imagePathList; // 图库
    }

    // 图库图片缩放、裁剪缩放 获取缩略图的HSV和目标图片的HSV
    public void clipPicture(){
        int clubSize = this.imagePathList.length; //  原料图片个数 图库大小
        this.imageClub= new BufferedImage[clubSize];    //  初始化缩略图片的数组
        this.clubHsvs = new float[clubSize][3]; //  初始化图片的 平均HSV 数组
        PictureCompress imageCompress = new PictureCompress();
        PictureHSV pictureHSV = new PictureHSV();
        for (int i = 0; i < clubSize; i++) {
            File saveImagePath  = new File(saveBasePath+imagePathList[i].getName());// 缩略图保存全路径
            imageClub[i] = imageCompress.compress(imagePathList[i],saveImagePath,PictureProcess.width,PictureProcess.height); // ***
            this.clubHsvs[i] = pictureHSV.getAverageHSV(imageClub[i]);// 缩略图的平均HSV数组
        }

    }

    // 图库图片替换 块 替换图片的下标
    public int[][] parse(){
        PictureHSV pictureHSV = new PictureHSV();
//        this.HSV = pictureHSV.getImageHSV(this.targetPicture); // 目标图片的HSV数组
        //       this.HSV = new float[128][128][3];

        this.HSV = pictureHSV.getImageDivisionHSV(this.targetResize, width, height); // 目标图片的HSV数组
        System.out.println("--------------------------------------");
        for(int i=0; i<this.HSV.length;i++){
            for(int j = 0; j<this.HSV[i].length; j++){
                if(i%8==0 &&j%8==0)
                    System.out.println(this.HSV[i][j][0]+"--"+this.HSV[i][j][1]+"--"+this.HSV[i][j][2]);
            }
        }
        System.out.println("--------------------------------------");
        int repalceIndex[][] = new int[this.HSV.length][this.HSV[0].length];
        for(int i=0; i<this.HSV.length;i++){
            for(int j = 0; j<this.HSV[i].length; j++){
//				System.out.println(this.HSV[i][j][0]+"--"+this.HSV[i][j][1]+"--"+this.HSV[i][j][2]);
                double similary = 0;
                double min = 65535;
                for(int k = 0; k<this.clubHsvs.length; k++){
                    similary = pictureHSV.similary(this.HSV[i][j],this.clubHsvs[k]);
//					System.out.println(similary+"--"+this.clubHsvs[k][0]+"--"+this.clubHsvs[k][1]+"--"+this.clubHsvs[k][2]);
                    if(similary <=  min){
                        min = similary;
                        repalceIndex[i][j] = k;
                    }
                }
//				System.out.println(similary+"--"+repalceIndex[i][j]+"--"+this.HSV[i][j][0]+"--"+this.HSV[i][j][1]+"--"+this.HSV[i][j][2]);
                System.out.print(repalceIndex[i][j]+"-");
            }
            System.out.println();
        }
        return repalceIndex;
    }

    // 合并拼接 生成图片
    public void getResultPicture(String outPath){
        clipPicture();
        int[][] imageIndex = parse();
        PictureStitch pictureStitch = new PictureStitch();
        BufferedImage destImage = null;
        try {
            destImage = pictureStitch.mergeAllImage(imageClub,imageIndex,PictureProcess.width,PictureProcess.height);
            FileOutputStream ops = new FileOutputStream(new File(outPath));
            ImageIO.write(destImage, "jpg", ops);// 保存结果图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
