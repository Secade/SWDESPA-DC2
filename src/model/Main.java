package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.MainController;

public class Main extends Application {

    public static final Database DB = new Database();
    public static Stage stageM;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stageM = primaryStage;
        FXMLLoader root = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
        primaryStage.setTitle("SpotCloud");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/media/logo.png")));
        Scene scene = new Scene(root.load(),1188,638);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStageM(){
        return stageM;
    }
}
