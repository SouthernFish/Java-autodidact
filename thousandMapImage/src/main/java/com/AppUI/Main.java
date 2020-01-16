package com.AppUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * @Author TR
 * @Create 2019年12月26日
 * @Title:
 * @Description:
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOpacity(1);//设置透明度 0为完全透明 1为完全不透明 默认是1
        primaryStage.setWidth(915);
        primaryStage.setHeight(640);
        primaryStage.setResizable(false); // 不自适应
//        primaryStage.initStyle(StageStyle.UNDECORATED);//设定窗口无边框
        primaryStage.getIcons().add(new Image("/image/backgroundImg/mainWindowLeftUPIcon.jpg")); //设置窗口左上角的图标.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

