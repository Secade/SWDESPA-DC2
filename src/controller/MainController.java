package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private MediaView loginVideo;

    @FXML
    private Button logInBtn, signUpBtn, guestBtn;

    @FXML
    private ImageView logInPic, signUpPic;
    @FXML
    private Label guestLbl;

    public void initialize(){

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/media/loginVideo.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        loginVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(()->{

        });

        logInBtn.setOnMouseEntered(event -> {
            logInPic.setFitHeight(270);
            logInPic.setFitWidth(300);
            logInPic.setLayoutX(668);
            logInPic.setLayoutY(430);
        });

        logInBtn.setOnMouseExited(event -> {
            logInPic.setFitHeight(220);
            logInPic.setFitWidth(250);
            logInPic.setLayoutX(690);
            logInPic.setLayoutY(454);
        });

        signUpBtn.setOnMouseEntered(event -> {
            signUpPic.setFitHeight(270);
            signUpPic.setFitWidth(300);
            signUpPic.setLayoutX(668);
            signUpPic.setLayoutY(600);
        });

        signUpBtn.setOnMouseExited(event -> {
            signUpPic.setFitHeight(220);
            signUpPic.setFitWidth(250);
            signUpPic.setLayoutX(690);
            signUpPic.setLayoutY(624);
        });

        guestBtn.setOnMouseEntered(event -> {
            guestLbl.setTextFill(Color.web("#f7620e"));
        });

        guestBtn.setOnMouseExited(event -> {
            guestLbl.setTextFill(Color.web("#FFFFFF"));
        });
    }
}
