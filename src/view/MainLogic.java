package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class MainLogic extends View{

    @FXML
    private MediaView loginVideo;
    @FXML
    private TextField userNameInput, passwordInput, newUserNameInput, newPasswordInput;
    @FXML
    private Button logInBtn, signUpBtn, guestBtn, newSignUpBtn, newBackBtn;
    @FXML
    private AnchorPane signUpPage, loginPane;
    @FXML
    private ImageView logInPic, signUpPic, newSignUpPic, newBackPic;
    @FXML
    private Label guestLbl, userNameLbl, passwordLbl, loginLbl, signUpLbl;

    public void initialize(){

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/media/loginVideo.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        loginVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(()->{

        });

        guestBtn.setOnAction(event -> {
            mediaPlayer.stop();
            try {
                ChangeScene("/view/HomePage.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        logInBtn.setOnAction(event -> {
            if(true) {
                mediaPlayer.stop();
                try {
                    ChangeScene("/view/HomePage.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        logInBtn.setOnMouseEntered(event -> {
            logInPic.setFitHeight(250);
            logInPic.setFitWidth(250);
            logInPic.setLayoutX(178);
            logInPic.setLayoutY(306);
        });

        logInBtn.setOnMouseExited(event -> {
            logInPic.setFitHeight(200);
            logInPic.setFitWidth(200);
            logInPic.setLayoutX(200);
            logInPic.setLayoutY(330);
        });

        signUpBtn.setOnMouseEntered(event -> {
            signUpPic.setFitHeight(250);
            signUpPic.setFitWidth(250);
            signUpPic.setLayoutX(178);
            signUpPic.setLayoutY(416);
        });

        signUpBtn.setOnMouseExited(event -> {
            signUpPic.setFitHeight(200);
            signUpPic.setFitWidth(200);
            signUpPic.setLayoutX(200);
            signUpPic.setLayoutY(440);
        });

        signUpBtn.setOnAction(event -> {
            loginPane.setVisible(false);
            loginPane.setDisable(true);

            signUpPage.setVisible(true);
            signUpPage.setDisable(false);
        });

        guestBtn.setOnMouseEntered(event -> {
            guestLbl.setTextFill(Color.web("#f7620e"));
        });

        guestBtn.setOnMouseExited(event -> {
            guestLbl.setTextFill(Color.web("#FFFFFF"));
        });

        newSignUpBtn.setOnMouseEntered(event -> {
            newSignUpPic.setFitHeight(250);
            newSignUpPic.setFitWidth(250);
            newSignUpPic.setLayoutX(178);
            newSignUpPic.setLayoutY(306);
        });

        newSignUpBtn.setOnMouseExited(event -> {
            newSignUpPic.setFitHeight(200);
            newSignUpPic.setFitWidth(200);
            newSignUpPic.setLayoutX(200);
            newSignUpPic.setLayoutY(330);
        });

        newBackBtn.setOnMouseEntered(event -> {
            newBackPic.setFitHeight(250);
            newBackPic.setFitWidth(250);
            newBackPic.setLayoutX(178);
            newBackPic.setLayoutY(416);
        });

        newBackBtn.setOnMouseExited(event -> {
            newBackPic.setFitHeight(200);
            newBackPic.setFitWidth(200);
            newBackPic.setLayoutX(200);
            newBackPic.setLayoutY(440);
        });

        newBackBtn.setOnAction(event -> {
            loginPane.setVisible(true);
            loginPane.setDisable(false);

            signUpPage.setVisible(false);
            signUpPage.setDisable(true);
        });

        newSignUpBtn.setOnAction(event -> {
            if(true) {
                loginPane.setVisible(true);
                loginPane.setDisable(false);

                signUpPage.setVisible(false);
                signUpPage.setDisable(true);
            }
        });


    }
}
