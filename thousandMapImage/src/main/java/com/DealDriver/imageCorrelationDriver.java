package com.DealDriver;

import com.CrawImage.CrawImage;
import com.ImageProcess.PictureProcess;

import java.io.File;
import java.io.IOException;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description:
 */
public class imageCorrelationDriver {

    // 百度图片基础地址
    static String baseUrl = "http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1575339681068_R&pv=&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&sid=&word=";

    // 下载图片
    public static void downloadImages(String keyWord, int imageNumber, String savePath,int currentIndex){
        try {
            // 搜索关键词 String keyWord = "动漫人物";
            // 图片张数 int imageNumber = 5;
            // "F:\\thousandMapImaging\\resourcesPictures\\resources\\"保存路径
            CrawImage craw = new CrawImage(imageNumber, currentIndex, savePath);
            String targetUrl = baseUrl +keyWord;
            craw.crawImageImplement(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( "图片下载完成!" );
    }

    // 拼图成像com.thousandMapImage.jigsawPuzzle
    private static void mergeAndSave(){
// 		 	 Scanner input = new Scanner(System.in);
//   	     System.out.println("请输入图片全路径："); // F:\\thousandMapImaging\\resourceMultithread\\resources
//   	     String imageClubPath = input.nextLine();
//   	     System.out.println("请输入目标图片全路径："); // F:\\thousandMapImaging\\targetImage\\yexiu.jpg
//   	     String targetImage = input.nextLine();
//		     System.out.println("请输入结果图片保存路径："); // F:\\thousandMapImaging\\targetImage\\yexiu_re.jpg
//		     String outPath = input.nextLine();
//   	     input.close();
        // 目标图片
        File targetImage = new File("F:\\thousandMapImaging\\targetImage\\yexiu.jpg");
        // 结果图片全路径
        String  outPath = "F:\\thousandMapImaging\\targetImage\\yexiu_re1.jpg";

        // 图库 读取指定文件夹的所有文件
        File imageClubPath = new File("F:\\thousandMapImaging\\resourceMultithread\\resources");
        File[] imageClub = imageClubPath.listFiles();
        String saveBasePath = "F:\\thousandMapImaging\\resourceMultithread\\compress\\";
        try {
            PictureProcess imageStitch = new PictureProcess(targetImage, imageClub,saveBasePath);// 图库图片处理 得到图片RGB数组
            imageStitch.getResultPicture(outPath); // 像素替换 并保存到本地
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "合成图像完成!" );
    }

}
