package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Database;
import model.User;
import model.UserService;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MainController {

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

    private  int idCount;
    private Database DB;
    public static User user;

    private static final Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*\\p{Punct})(?=.*[a-zA-z]).{6,20}$");

    public void setDatabase(Database DB){
        this.DB = DB;
    }

    public void initialize(){
        DB = new Database();

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

            user = new User();
            user.setId(userIdList.get(0).getId());
            user.setUsername(userIdList.get(0).getUsername());
            user.setPassword(userIdList.get(0).getPassword());
            user.setFavoritesong1(-1);
            user.setFavoritesong2(-1);
            user.setFavoritesong3(-1);

            System.out.println(userIdList.get(0).getId());
            System.out.println(userIdList.get(0).getUsername());
            System.out.println(userIdList.get(0).getPassword());

            try {
                FXMLLoader pane = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));
                Stage stage = (Stage) guestBtn.getScene().getWindow();
                Scene scene = new Scene(pane.load());
                HomePageController controller = pane.<HomePageController>getController();
                controller.setDatabase(DB);
                controller.setUser(user);
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        logInBtn.setOnAction(event -> {
            boolean check = false;
                for(int i=0;i<userIdList.size();i++) {
                    System.out.println(userIdList.get(i).getId());
                    System.out.println(userIdList.get(i).getUsername());
                    System.out.println(passwordList.get(i).getPassword()+"\n");
                    if (userNameInput.getText().compareTo(userIdList.get(i).getUsername()) == 0 &&
                            passwordInput.getText().compareTo(passwordList.get(i).getPassword()) == 0) {

                        user = new User();
                        user.setId(userIdList.get(i).getId());
                        System.out.println(userIdList.get(i).getId());
                        user.setUsername(String.valueOf(userIdList.get(i).getUsername()));
                        user.setPassword(String.valueOf(passwordList.get(i).getPassword()));
                        user.setFavoritesong1(-1);
                        user.setFavoritesong2(-1);
                        user.setFavoritesong3(-1);

                        mediaPlayer.stop();
                        try {
                            FXMLLoader pane = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));
                            Stage stage = (Stage) logInBtn.getScene().getWindow();
                            Scene scene = new Scene(pane.load());
                            HomePageController controller = pane.getController();
                            controller.setDatabase(DB);
                            controller.setUser(user);
                            stage.setScene(scene);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        check=true;
                    }

                }
            if(!check){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Invalid Username or Password");
                alert.showAndWait();
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
            newPasswordInput.clear();
            newUserNameInput.clear();
            userNameInput.clear();
            passwordInput.clear();

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
            newPasswordInput.clear();
            newUserNameInput.clear();
            userNameInput.clear();
            passwordInput.clear();

            loginPane.setVisible(true);
            loginPane.setDisable(false);

            signUpPage.setVisible(false);
            signUpPage.setDisable(true);
        });

        newSignUpBtn.setOnAction(event -> {



                int y=0,z=0;

                for(int i=0;i<userIdList.size();i++) {
                    if (newUserNameInput.getText().compareTo(userIdList.get(i).getUsername()) == 0) {
                        z += 1;
                    }

                }
                if(!pattern.matcher(newPasswordInput.getText()).matches()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Invalid Password");
                    alert.setContentText("Password Requires at least 1 number, 1 special character, 6-20 characters, 1 alphabetical character");
                    alert.showAndWait();
                    y+=1;
                }
                if (z==0&&y==0) {
                    if(!newUserNameInput.getText().trim().isEmpty()&&!newPasswordInput.getText().trim().isEmpty()) {

                        User c = new User();

                        c.setId(idCount);
                        idCount++;
                        c.setUsername(newUserNameInput.getText());
                        c.setPassword(newPasswordInput.getText());
                        c.setFavoritesong1(-1);
                        c.setFavoritesong2(-1);
                        c.setFavoritesong3(-1);

                        service.add(c);

                        loginPane.setVisible(true);
                        loginPane.setDisable(false);

                        signUpPage.setVisible(false);
                        signUpPage.setDisable(true);
                        this.initialize();
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            else if(z>0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Username has been Taken Already!");
                alert.showAndWait();
            }
            else if(newUserNameInput.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Pleases Input an Username");
                    alert.showAndWait();
                }

        });


    }
}
