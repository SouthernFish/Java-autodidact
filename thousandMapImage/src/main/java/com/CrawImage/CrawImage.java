package com.CrawImage;


//引入多线程池的包
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 一引入 selenium 包
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description:
 */
public class CrawImage {

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
    // 打印
    static String log;

    public CrawImage(int imageNumber,int currentIndex,String basePath,String log) {
        this.imageNumber = imageNumber;
        this.currentIndex = currentIndex;
        this.basePath = basePath;
        this.log = log;
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
    public String crawImageImplement(String targetUrl) throws Exception{
        targetUrl =  matchUrl(targetUrl);  // 处理URL中的中文字符
//        System.setProperty("webdriver.chrome.driver", "D://ChromeDriver//chromedriver.exe");/driver/chromedriver.exe
        String chrome = "11";//CrawImage.class.getClassLoader().getResource("").getPath() +"driver/chromedriver.exe"; // 本地驱动路径
        System.out.println(chrome);
        this.log = chrome +"\n";
        chrome = "driver/chromedriver.exe";//  驱动 jar  路径
//        chrome = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()+"/driver/chromedriver.exe";
        System.out.println(chrome);
        this.log = this.log + chrome+"\n";
//        chrome = this.getClass().getResource("/driver/chromedriver.exe").getPath();
        chrome = java.net.URLDecoder.decode(chrome, "UTF-8");
//        chrome = System.getProperty("java.class.path")+"/driver/chromedriver.exe";  //利用了java运行时的系统属性来得到jar文件位置，也是/xxx/xxx.jar这种形式。
       
        System.out.println(chrome);
        this.log = this.log + chrome+"\n";
        System.setProperty("webdriver.chrome.driver", chrome);
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
                this.log = this.log + "\n"+"第"+page+"页"+targetUrl;
                List<WebElement> pages = getImageUrl(driver,targetUrl,pool);
                targetUrl = pages.get(pages.size()-2).getAttribute("href");
                System.out.println("当前最大下标："+(currentIndex-1));
                this.log = this.log + "\n"+"当前最大下标："+(currentIndex-1);
                page++;
            }
            pool.shutdown();
            return this.log;
        }catch(Exception e){
            System.out.print(e);
            return this.log + e.getMessage();
        }
    }


    // 多线程 下载图片
    public static List<WebElement> getImageUrl(WebDriver driver, String targetUrl, ExecutorService pool)  throws Exception{

        driver.get(targetUrl);// 访问网页 得到数据
        List<WebElement> imageList = driver.findElements(By.tagName("img"));//获取所有 img 标签
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
                if(imageUrl.contains(".jpg") && currentIndex <= imageNumber ) {// 取 JPG 格式图片
                    imageUrl = matchUrl(imageUrl);  // 处理URL中的中文字符
                    DownloadImage loadImage = new DownloadImage(imageUrl, currentIndex, basePath);// 多线程爬取网页图片
                    pool.execute(loadImage);
                    currentIndex += 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WebElement> pages =  driver.findElements(By.cssSelector("#page .n"));
//        String nextPage = driver.findElement(By.cssSelector("#page .n")).getAttribute("href");// 下一页
        return pages;
    }
}
