//package view;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import model.Database;
//import javafx.stage.Stage;
//
//import static view.Main.getStageM;
//
//public class ChangeScene {
//    public ChangeScene(String fxml, Button component, Database DB)throws Exception{
//        Stage stageM = getStageM();
//        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//
//        Scene scene = new Scene(pane);
//        stageM.setScene(scene);
//        stageM.show();
//    }
//}