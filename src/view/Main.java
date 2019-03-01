package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stageM;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stageM=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        primaryStage.setTitle("SpotCloud");
        //primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/media/logo.png")));
        Scene scene = new Scene(root,1200,650);
        primaryStage.setScene(scene);
        //primaryStage.minHeightProperty().bind(scene.widthProperty().multiply());
        //primaryStage.minWidthProperty().bind(scene.heightProperty().divide(3));
        primaryStage.show();
    }


    public static Stage getStageM() {
        return stageM;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
