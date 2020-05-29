package com.CrawImage;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description:
 */
public class DownloadImage implements Runnable {

    String downUrl;
    int imageNumber;
    // 存储位置
    static String basePath = "F:\\thousandMapImaging\\resourceMultithread\\resources\\";
    static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)";
    static String referer = "https://www.baidu.com/s?wd=%E7%99%BE%E5%BA%A6%E5%9B%BE%E7%89%87%20403&rsv_spt=1&rsv_iqid=0xec7a7a0d0012d0bb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=27&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=8747&rsv_sug4=9667";

    public DownloadImage(String downUrl,int imageNumber,String basePath) {
        this.basePath = basePath;
        this.downUrl = downUrl;
        this.imageNumber = imageNumber;
    }

    public void run() {

        BufferedInputStream bis = null; //  输入流
        FileOutputStream fos = null;  //  文件IO流

        try {
            URL url = new URL(downUrl);//  生成URL对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", userAgent); //防止报403错误
            connection.addRequestProperty("Referer", referer);
            int responseCode=connection.getResponseCode();   //获取服务器响应代码
            if(responseCode==200){
                bis = new BufferedInputStream(connection.getInputStream());//得到输入流，即获得了网页的内容
                String savePath =basePath + this.imageNumber+".jpg"; //  创建图片的存储对象
                fos = new FileOutputStream(savePath);
                int length;
                //  存储图片
                while ((length = bis.read()) != -1) {
                    fos.write(length);
                }
            }
            else{
                System.out.println("获取不到网页的源码，服务器响应代码为："+responseCode);
            }
        } catch (Exception e) {
            System.out.println("获取不到网页的源码,出现异常："+e);
        } finally {
            try {
                if (bis != null) {  // 关闭输入流
                    bis.close();
                }
                if (fos != null) {  // 关闭文件IO
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
