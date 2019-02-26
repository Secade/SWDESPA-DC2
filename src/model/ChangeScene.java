package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static model.Main.getStageM;

public class ChangeScene {

    public ChangeScene(String fxml)throws Exception{
        Stage stageM = getStageM();
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));

        Scene scene = new Scene(pane);
        stageM.setScene(scene);
        stageM.show();
    }
}
