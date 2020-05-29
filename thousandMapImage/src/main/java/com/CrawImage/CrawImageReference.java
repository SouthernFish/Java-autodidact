package com.CrawImage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author TR
 * @Create 2019年12月31日
 * @Title:
 * @Description:
 */
public class CrawImageReference {

    // 百度图片基础地址 未使用到
    static String baseUrl = "http://image.baidu.com";
    // 编码
    private final static String ENCODE = "GBK";
    // 使用代理 请求头内容
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36";
    private static String referer = "https://www.baidu.com/s?wd=%E7%99%BE%E5%BA%A6%E5%9B%BE%E7%89%87%20403&rsv_spt=1&rsv_iqid=0xec7a7a0d0012d0bb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=27&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=8747&rsv_sug4=9667";

    // 当前下标
    static int currentIndex;
    // 下载数量
    static int imageNumber ;
    // 存储位置
    static String basePath;

    public CrawImageReference(int imageNumber,int currentIndex,String basePath) {
        this.imageNumber = imageNumber;
        this.currentIndex = currentIndex;
        this.basePath = basePath;
    }

    // 网址中文乱码处理
    public static String matchUrl(String url){
        Pattern p = Pattern.compile("([\u4e00-\u9fa5])");
        Matcher m = p.matcher(url);
        List<String> list = new ArrayList<String>();
        while(m.find()){
            String match = m.group(0);
            String re_match = "";
            if(match != null){
                try {
                    re_match = java.net.URLEncoder.encode(match, ENCODE);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            list.add(re_match);
        }
        if(list.size()==0){
            return url;
        }else{
            return  "http://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word="
                    +list.stream().collect(Collectors.joining(""))
                    +"&pn=0&gsm=&ct=&ic=0&lm=-1&width=0&height=0";
        }
    }

    //  爬虫
    public void crawImageImplement(String targetUrl) throws Exception{
        targetUrl =  matchUrl(targetUrl);  // 处理URL中的中文字符
        System.setProperty("webdriver.chrome.driver", "D://ChromeDriver//chromedriver.exe");
        // 无界面浏览
        ChromeOptions options = new ChromeOptions(); // 设置chrome选项
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(options); // 建立selenium 驱动

//	   		WebDriver driver = new ChromeDriver();// 有界面

        try{
            ExecutorService pool = Executors.newCachedThreadPool(); // 创建一个缓冲池
            pool = Executors.newFixedThreadPool(9);// 设置其容量为9
            int page = 1; // 访问层数
            while(currentIndex <= imageNumber){
                System.out.println("第"+page+"页"+targetUrl);
                //				targetUrl = getImageUrl(driver,targetUrl);
                targetUrl = getImageUrl(driver,targetUrl,pool);
                System.out.println("当前最大下标："+(currentIndex-1));
                page++;
            }
            pool.shutdown();

        }catch(Exception e){
            System.out.print(e);
        }
    }


    // 多线程 下载图片
    public static String getImageUrl(WebDriver driver, String targetUrl, ExecutorService pool)  throws Exception{
        // 访问网页 得到数据
        driver.get(targetUrl);

        //获取所有 img 标签
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        System.out.println("本页数据："+imageList.size());
        // 休眠1秒
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 下载图片
        try {
            for (WebElement image : imageList) {
                String imageUrl = image.getAttribute("src");  //获取 img 标签 data- imgurl 属性值
//		             System.out.println(imageUrl);
                // 取 JPG 格式图片
                if(imageUrl.contains(".jpg") && currentIndex <= imageNumber ) {
                    imageUrl = matchUrl(imageUrl);  // 处理URL中的中文字符
                    DownloadImage loadImage = new DownloadImage(imageUrl, currentIndex, basePath);// 多线程爬取网页图片
                    pool.execute(loadImage);
                    currentIndex += 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String nextPage = driver.findElement(By.cssSelector("#page .n")).getAttribute("href");// 下一页
        return nextPage;
    }

    // 获取图片链接
    public static String getImageUrl(WebDriver driver, String targetUrl)  throws Exception{
        // 访问网页 得到数据
        driver.get(targetUrl);

        //获取所有 img 标签
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        System.out.println("本页数据："+imageList.size());
        // 休眠1秒
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 下载图片
        try {
            for (WebElement image : imageList) {
                String imageUrl = image.getAttribute("src");  //获取 img 标签 data- imgurl 属性值
//		             System.out.println(imageUrl);
                // 取 JPG 格式图片
                if( imageUrl.contains(".jpg") && currentIndex <= imageNumber ) {
                    imageUrl = matchUrl(imageUrl);  // 处理URL中的中文字符
                    downloadImage(imageUrl);
                    currentIndex += 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String nextPage = driver.findElement(By.cssSelector("#page .n")).getAttribute("href");// 下一页
        return nextPage;
    }

    // 访问URL 下载图片 单线程
    public static void downloadImage(String imageUrl)  throws Exception{
        String imageName = basePath + currentIndex +".jpg"; // 存储位置Config.DEFAULT_USER_AGENT

        URLConnection openConnection = new URL(imageUrl).openConnection();
        openConnection.addRequestProperty("User-Agent", userAgent);
        openConnection.addRequestProperty("Referer", referer);
        openConnection.connect();
        InputStream dis = openConnection.getInputStream();// 得到输入流，即获得了网页的内容
        OutputStream fos = new BufferedOutputStream(new FileOutputStream(imageName)); // 输出流

        // 设置2k的数据流缓冲
        byte[] buffer = new byte[1024 * 2];
        int length;
        while((length = dis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
        }
        dis.close();
        fos.close();
        System.out.println("保存："+imageName);
    }
}
