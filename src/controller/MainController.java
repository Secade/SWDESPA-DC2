package controller;

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
import model.ChangeScene;
import model.User;
import model.UserService;

import java.util.List;

import static model.Main.DB;

public class MainController {

    @FXML
    private MediaView loginVideo;
    @FXML
    private TextField userNameInput, passwordInput, newUserNameInput, newPasswordInput;
    @FXML
    private Button logInBtn, signUpBtn, guestBtn, newSignUpBtn, newBackBtn;
    @FXML
    private AnchorPane signUpPage;
    @FXML
    private ImageView logInPic, signUpPic, newSignUpPic, newBackPic;
    @FXML
    private Label guestLbl, userNameLbl, passwordLbl, loginLbl, signUpLbl;

    private  int idCount;
    public static User loggedin;
    public void initialize(){

        UserService service = new UserService(DB);


        List<User> userIdList = service.getUserIdList();
        List<User> passwordList = service.getPasswordList();

        idCount = userIdList.size();

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
                new ChangeScene("/view/HomePage.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        logInBtn.setOnAction(event -> {
            if(true) {

                for(int i=0;i<userIdList.size();i++) {
                    if (userNameInput.getText().compareTo(userIdList.get(i).getUsername()) == 0 &&
                            passwordInput.getText().compareTo(passwordList.get(i).getPassword()) == 0) {

                        loggedin= new User();
                        loggedin.setUsername(String.valueOf(userIdList.get(i).getUsername()));
                        loggedin.setPassword(String.valueOf(userIdList.get(i).getPassword()));

                        mediaPlayer.stop();
                        try {
                            new ChangeScene("/view/HomePage.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
                }
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

        signUpBtn.setOnAction(event -> {
            userNameInput.setVisible(false);
            userNameInput.setDisable(true);
            userNameLbl.setVisible(false);
            userNameLbl.setDisable(true);
            passwordInput.setVisible(false);
            passwordInput.setDisable(true);
            passwordLbl.setVisible(false);
            passwordLbl.setDisable(true);
            logInPic.setVisible(false);
            logInPic.setDisable(true);
            logInBtn.setVisible(false);
            logInBtn.setDisable(true);
            loginLbl.setVisible(false);
            loginLbl.setDisable(true);
            guestLbl.setVisible(false);
            guestLbl.setDisable(true);
            guestBtn.setVisible(false);
            guestBtn.setDisable(true);
            signUpPic.setVisible(false);
            signUpPic.setDisable(true);
            signUpBtn.setVisible(false);
            signUpBtn.setDisable(true);
            signUpLbl.setVisible(false);
            signUpLbl.setDisable(true);

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
            newSignUpPic.setFitHeight(270);
            newSignUpPic.setFitWidth(300);
            newSignUpPic.setLayoutX(198);
            newSignUpPic.setLayoutY(141);
        });

        newSignUpBtn.setOnMouseExited(event -> {
            newSignUpPic.setFitHeight(220);
            newSignUpPic.setFitWidth(250);
            newSignUpPic.setLayoutX(220);
            newSignUpPic.setLayoutY(165);
        });

        newBackBtn.setOnMouseEntered(event -> {
            newBackPic.setFitHeight(270);
            newBackPic.setFitWidth(300);
            newBackPic.setLayoutX(198);
            newBackPic.setLayoutY(326);
        });

        newBackBtn.setOnMouseExited(event -> {
            newBackPic.setFitHeight(220);
            newBackPic.setFitWidth(250);
            newBackPic.setLayoutX(220);
            newBackPic.setLayoutY(350);
        });

        newBackBtn.setOnAction(event -> {
            userNameInput.setVisible(true);
            userNameInput.setDisable(false);
            userNameLbl.setVisible(true);
            userNameLbl.setDisable(false);
            passwordInput.setVisible(true);
            passwordInput.setDisable(false);
            passwordLbl.setVisible(true);
            passwordLbl.setDisable(false);
            logInPic.setVisible(true);
            logInPic.setDisable(false);
            logInBtn.setVisible(true);
            logInBtn.setDisable(false);
            loginLbl.setVisible(true);
            loginLbl.setDisable(false);
            guestLbl.setVisible(true);
            guestLbl.setDisable(false);
            guestBtn.setVisible(true);
            guestBtn.setDisable(false);
            signUpPic.setVisible(true);
            signUpPic.setDisable(false);
            signUpBtn.setVisible(true);
            signUpBtn.setDisable(false);
            signUpLbl.setVisible(true);
            signUpLbl.setDisable(false);

            signUpPage.setVisible(false);
            signUpPage.setDisable(true);
        });

        newSignUpBtn.setOnAction(event -> {
            if(true) {
                userNameInput.setVisible(true);
                userNameInput.setDisable(false);
                userNameLbl.setVisible(true);
                userNameLbl.setDisable(false);
                passwordInput.setVisible(true);
                passwordInput.setDisable(false);
                passwordLbl.setVisible(true);
                passwordLbl.setDisable(false);
                logInPic.setVisible(true);
                logInPic.setDisable(false);
                logInBtn.setVisible(true);
                logInBtn.setDisable(false);
                loginLbl.setVisible(true);
                loginLbl.setDisable(false);
                guestLbl.setVisible(true);
                guestLbl.setDisable(false);
                guestBtn.setVisible(true);
                guestBtn.setDisable(false);
                signUpPic.setVisible(true);
                signUpPic.setDisable(false);
                signUpBtn.setVisible(true);
                signUpBtn.setDisable(false);
                signUpLbl.setVisible(true);
                signUpLbl.setDisable(false);

                signUpPage.setVisible(false);
                signUpPage.setDisable(true);

                int y=0,z=0;

                for(int i=0;i<userIdList.size();i++) {
                    if (newUserNameInput.getText().compareTo(userIdList.get(i).getUsername()) == 0) {
                        z += 1;
                    }
                }

                if (z==0) {
                    if(!newUserNameInput.getText().trim().isEmpty()&&!newPasswordInput.getText().trim().isEmpty()) {

                        User c = new User();

                        c.setId(idCount + 1);
                        idCount++;
                        c.setUsername(newUserNameInput.getText());
                        c.setPassword(newPasswordInput.getText());

                        service.add(c);

                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                       System.out.println("Please Fill in all Information!");
                    }

                }
            }
            this.initialize();
        });


    }
}
