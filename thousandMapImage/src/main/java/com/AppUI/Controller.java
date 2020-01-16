package com.AppUI;


import com.DealDriver.imageCorrelationDriver;
import com.ImageProcess.ImageCompress;
import com.ImageProcess.SourceImageProcess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title: Controller
 * @Description: 界面控制 控制器
 */
public class Controller implements Initializable {

    @FXML
    private AnchorPane SearchEngine; //搜索界面
    @FXML
    private TextField InputSearch; // 搜索关键字
    @FXML
    private TextField InputNumber; // 搜索数量
    @FXML
    private TextField InputFilePath; // 下载保存文件路径
    @FXML
    private Text SearchHint; // 搜索提示 默认 Life has no limitations.It always opens up for more possibilities for the better.

    @FXML
    private AnchorPane ImageProcess; // 图片处理相关界面
    @FXML
    private ImageView TargetImage;// 目标图片
    @FXML
    private TextField TargetImageWidth; // 目标图片宽度
    @FXML
    private TextField TargetImageHeight; // 目标图片高度
    @FXML
    private Slider TargetWidthControl; // 目标图片宽度控制
    @FXML
    private Slider TargetHeightControl; // 目标图片高度度控制
    @FXML
    private TextField ResourcePath; // 图库资源路径
    @FXML
    private TextField MergeImageWidth; // 目标图片宽度
    @FXML
    private TextField MergeImageHeight; // 目标图片高度
    @FXML
    private Slider MergeWidthControl; // 碎片宽度控制
    @FXML
    private Slider MergeHeightControl; // 碎片高度度控制
    @FXML
    private  ImageView ResultImage;// 结果图片

    @FXML
    private AnchorPane Parent; // 父界面
    @FXML
    private Pane MouseSwitch;// 搜索界面 右下角 鼠标图片 跳转
    @FXML
    private Pane ImageLeftDown;
    @FXML
    private Pane ImageRightUp;


    imageCorrelationDriver ImgDriver;
    int currentIndex = 1;
    String sourceImagePath = null; // 目标原图路径
    String targetImagePath = null; // 目标原图处理后的图片路径
    BufferedImage targetImage = null; // 目标图片用于合成
    BufferedImage resultImage = null; // 合成的结果图片


    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: initialize
     * @Description: 初始化界面
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SearchHint.focusedProperty();// 聚焦
        ImgDriver = new imageCorrelationDriver();
        controlTargetImageSize(1,TargetWidthControl);// 目标图片宽度控制
        controlTargetImageSize(2,TargetHeightControl);// 目标图片高度控制
        controlTargetImageSize(3,MergeWidthControl);// 碎片宽度控制
        controlTargetImageSize(4,MergeHeightControl);// 碎片高度控制
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: chooseLocalFile
     * @Description: 本地文件夹选取
     */
    public String chooseLocalFile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(new Stage());
        String choosePath = "";// 选择本地文件夹路径
        if(file != null){
            choosePath = file.getPath();
        }
        return choosePath+"\\";

    }
    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: chooseLocalFileSearch
     * @Description: 本地文件夹选取
     */
    public void chooseLocalFileSearch(MouseEvent event) {
        String choosePath = chooseLocalFile();
        if(!choosePath.equals("")){
            InputFilePath.setText(choosePath);
        }
    }

    /**
     * @Author TR
     * @Create 2019年12月26日
     * @Title: searchAndDownload
     * @Description: 爬取图片并下载
     */
    public void searchAndDownload(MouseEvent event) {
        SearchHint.setText("Catch a falling start and put it in your pocket,never let it fade away.");// 搜索中提示

        String savePath = "F:/thousandMapImaging/resourcesPictures/resources/";// 保存路径
        if(!InputFilePath.getText().equals("")){
            savePath = InputFilePath.getText();
        }
        System.out.println("保存路径>> "+savePath);
        String keyWord = "动漫人物";// 搜索关键字 String keyWord = "动漫人物";
        if(!InputSearch.getText().equals("")){
            keyWord = InputSearch.getText();
        }
        System.out.println("关键字>> "+keyWord);
        int imageNumber = 5;// 搜索数量 int imageNumber = 5;
        if(!InputNumber.getText().equals("")){
            imageNumber = Integer.valueOf(InputNumber.getText());
        }
        System.out.println("数量>> "+imageNumber);
        // 爬虫
        ImgDriver.downloadImages(keyWord,imageNumber,savePath,currentIndex);
        // 完成下载后提示
        SearchHint.setText("You got out on that stage now,and you show them how beautiful you are.");// 搜索完成提示
        // 是否进入文件夹 操作 弹框

    }


    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: switchToMergeImage
     * @Description: 切换页面
     */
    public void switchToMergeImage(MouseEvent event) {
        SearchEngine.setVisible(false);// 搜索界面隐藏
        ImageProcess.setVisible(true);// 图片处理界面显示
        if(!InputFilePath.getText().equals("")){
            ResourcePath.setText(InputFilePath.getText());// 合成图片需要资源数据路径显示
        }
    }


    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: controlTargetImageSize
     * @Description: 图片size控制条
     */
    public void controlTargetImageSize(int type, Slider slider){
        switch(type){
            case 1:{
                slider.setMin(288);
                slider.setMax(576);
                slider.setValue(576);
                //slider.setShowTickLabels(true);
                slider.setShowTickMarks(true);
                slider.setMajorTickUnit(36);
                slider.setBlockIncrement(36);
                break;
            }
            case 2: {// 目标图片高度度控制
                slider.setMin(512);
                slider.setMax(1024);
                slider.setValue(1024);
                //slider.setShowTickLabels(true);
                slider.setShowTickMarks(true);
                slider.setMajorTickUnit(64);
                slider.setBlockIncrement(64);
                break;
            }
            case 3: {// 碎片高度度控制
                slider.setMin(3);
                slider.setMax(9);
                slider.setValue(9);
                //slider.setShowTickLabels(true);
                slider.setShowTickMarks(true);
                slider.setMajorTickUnit(3);
                slider.setBlockIncrement(3);
                break;
            }
            case 4: {// 碎片高度度控制
                slider.setMin(4);
                slider.setMax(16);
                slider.setValue(16);
                //slider.setShowTickLabels(true);
                slider.setShowTickMarks(true);
                slider.setMajorTickUnit(4);
                slider.setBlockIncrement(4);
                break;
            }
        }
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: switchToSearchEngine
     * @Description: 切换页面
     */
    public void switchToSearchEngine(MouseEvent event) {
        ImageProcess.setVisible(false);// 图片处理界面隐藏
        SearchEngine.setVisible(true);// 搜索界面显示
    }


    /**
     * @Author TR
     * @Create 2019年12月28日
     * @Title: chooseTargetImage
     * @Description: 选择目标图片并显示
     */
    public void chooseTargetImage(MouseEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        sourceImagePath = file.getAbsolutePath();
        System.out.println(sourceImagePath);
        TargetImage.setImage(new Image("file:"+sourceImagePath,0,0,false,true));
        targetImagePath = sourceImagePath;
        // 处理的图片 BufferedImage 原始图片
        targetImage = ImageIO.read(new File(sourceImagePath));

    }

    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: setTargetImageWidth
     * @Description: 目标图片剪切宽度设置
     */
    public void setTargetImageWidth(){
        TargetImageWidth.setText((int) Math.floor(TargetWidthControl.getValue())+"");
    }

    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: setTargetImageHeight
     * @Description: 目标图片剪切高度设置
     */
    public void setTargetImageHeight(MouseEvent event) {
        TargetImageHeight.setText((int)Math.floor(TargetHeightControl.getValue())+"");
    }
    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: ClipImage
     * @Description: 目标图片剪切压缩
     */
    public void ClipImage(MouseEvent event) {
        if(sourceImagePath == null){
            // 弹框

        }else{
            StringBuilder stringBuilder = new StringBuilder(sourceImagePath);
            targetImagePath = stringBuilder.insert(stringBuilder.indexOf("."),"(1)").toString();
            int width = 576,height = 1024;
            if(!TargetImageWidth.getText().equals("")){
                width = Integer.valueOf(TargetImageWidth.getText());
            }
            if(!TargetImageHeight.getText().equals("")){
                height = Integer.valueOf(TargetImageHeight.getText());
            }
            System.out.println(width+"--target--"+height+"--"+targetImagePath);
            ImageCompress imageCompress = new ImageCompress();// 裁剪图片到规定大小
            targetImage = imageCompress.compress(new File(sourceImagePath), new File(targetImagePath), width, height);
//            targetImage = imageCompress.zipImageFile(new File(sourceImagePath), new File(targetImagePath), width, height,1f);
            TargetImage.setImage(new Image("file:"+targetImagePath));
        }
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: chooseLocalFileMerge
     * @Description: 资源文件夹选取
     */
    public void chooseLocalFileMerge(MouseEvent event) {
        String choosePath = chooseLocalFile();
        if(!choosePath.equals("")){
            ResourcePath.setText(choosePath);
        }
    }


    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: setMergeImageWidth
     * @Description: 碎片宽度设置
     */
    public void setMergeImageWidth(MouseEvent event) {
        MergeImageWidth.setText((int)Math.floor(MergeWidthControl.getValue())+"");
    }

    /**
     * @Author TR
     * @Create 2019年12月31日
     * @Title: setMergeImageHeight
     * @Description: 碎片高度设置
     */
    public void setMergeImageHeight(MouseEvent event) {
        MergeImageHeight.setText((int)Math.floor(MergeHeightControl.getValue())+"");
    }


    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: mergeImage
     * @Description: 合成图像
     */
    public void mergeImage(MouseEvent event) {
        if(targetImagePath == null){
            // 弹框

        }else{
            int width = 9,height = 16;
            if(!MergeImageWidth.getText().equals("")){
                width = Integer.valueOf(MergeImageWidth.getText());
            }
            if(!MergeImageHeight.getText().equals("")){
                height = Integer.valueOf(MergeImageHeight.getText());
            }
            System.out.println(width+"--Merge--"+height);
            String sourcePath = "F:/thousandMapImaging/resourcesPictures/resources/";// 资源路径
            if(!ResourcePath.getText().equals("")){
                sourcePath = InputFilePath.getText();
            }
            File imageClub = new File(sourcePath);
            SourceImageProcess mergeImage = new SourceImageProcess(imageClub, targetImage, height, width);
            resultImage = mergeImage.getResultImage();
            ResultImage.setImage(mergeImage.convertToImage(resultImage));
        }
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: saveResultImage
     * @Description: 保存图像
     */
    public void saveResultImage(MouseEvent event) throws Exception{
        StringBuilder stringBuilder = new StringBuilder(sourceImagePath);
        String resultImagePath = stringBuilder.insert(stringBuilder.indexOf("."),"(1)").toString();
        String formatName = sourceImagePath.substring(sourceImagePath.lastIndexOf('.') + 1);   // 保存的格式 也就是原图的格式
        ImageIO.write(resultImage, formatName, new File(resultImagePath));
    }

    /**
     * @Author TR
     * @Create 2019年12月28日
     * @Title: menuChoose
     * @Description: 设置页面显示
     */
    public void menuChoose(MouseEvent event) {
    }




}

