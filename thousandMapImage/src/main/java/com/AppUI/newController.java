package com.AppUI;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;

import javax.swing.text.View;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @Author TR
 * @Create 2019年12月27日
 * @Title:
 * @Description:
 */
public class newController implements Initializable {

    @FXML
    private AnchorPane Parent; // 父界面
    @FXML
    private AnchorPane SearchEngine; //搜索界面
    @FXML
    private TextField InputSearch; // 搜索关键字
    @FXML
    private TextField InputNumber; // 搜索数量
    @FXML
    private TextField InputFilePath; // 下载保存文件路径
    @FXML
    private TextArea SearchHint; // 搜索提示 默认 Life has no limitations.It always opens up for more possibilities for the better.

    @FXML
    private ImageView MouseSwitch1;// 搜索界面 右下角 鼠标图片 跳转 ！！没用
    @FXML
    private Pane MouseSwitch;// 搜索界面 右下角 鼠标图片 跳转
    @FXML
    private Pane ImageLeftDown;
    @FXML
    private Pane ImageRightUp;

    @FXML
    private AnchorPane ImageProcess; // 图片处理相关界面


    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: initialize
     * @Description: 初始化界面
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        MouseSwitch.setImage(imageViewSetting(this,"/backgroundImg/mouseSwitch.jpg"));// 搜索 右下角 图片
//        MouseSwitch.setBackground(imageBackgroundSetting("/image/backgroundImg/mouseSwitch.jpg",MouseSwitch.getWidth(), MouseSwitch.getHeight()));
//        MouseSwitch.setBackground(imageBackgroundSetting("/image/backgroundImg/mouseSwitch.jpg",367, 230));

    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: imageBackgroundSetting
     * @Description: 获得图片背景
     * @param  fileName 图片名+扩展名 背景的宽 width 高 height
     */
    public static Background imageBackgroundSetting(String fileName, double width, double height) {
        BackgroundImage appointBackgroundImage= new BackgroundImage(
                new Image(fileName,width,height,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        return new Background(appointBackgroundImage);
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: imageSetting
     * @Description: 图片设置 获得图片文件
     * @param object 当前class，传入this即可 ;fileName 图片名+扩展名
     */
    public static Image imageViewSetting(Object object, String fileName) {
        String appointImageUrl = object.getClass().getResource("/image/"+fileName).toString();
        return new Image(appointImageUrl);
    }

    /**
     * @Author TR
     * @Create 2019年12月27日
     * @Title: chooseLocalFile
     * @Description: 本地文件夹选取
     */
    public void chooseLocalFile(MouseEvent event) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        File file = directoryChooser.showDialog(new Stage());
        String choosePath = file.getPath();// 选择本地文件夹路径
        InputFilePath.setText(choosePath);
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

        // 完成下载后提示
        SearchHint.setText("You got out on that stage now,and you show them how beautiful you are.");// 搜索完成提示

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
}
