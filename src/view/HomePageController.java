package view;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Database;
import model.User;
import model.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomePageController {
    @FXML
    private MediaView homeVideo, songPlayer;
    @FXML
    private Label songNameLbl, artistNameLbl, albumNameLbl, genreTypeLbl, yearLbl, favPlaylistLbl, favSong1Lbl, favSong2Lbl, favSong3Lbl,welcomLbl;
    @FXML
    private Label playlistLbl, songsLbl, editLbl,welcomeLbl, uploadLbl;
    @FXML
    private AnchorPane songInfoPane, userInfoPane, controlPane, mainPane, playlistPane,songListPane;
    @FXML
    private ScrollPane songScrollPane, playlistScrollPane;
    @FXML
    private VBox songList, playList;
    @FXML
    private Button playlistBtn, songsBtn, logoutBtn, editBtn,uploadBtn;
    @FXML
    private ImageView repeatBtn, playBackBtn, previousBtn, playBtn, forwardBtn, fastForwardBtn, shuffleBtn, songPic, expandBtn, shrinkBtn, logoutPic;

    private boolean isPlayingSong;
    private boolean songPaneOpen;
    private boolean playlistPaneOpen;

    private Database DB;
    private User user;

    public void setDatabase(Database DB){
        this.DB = DB;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void initialize(){

        isPlayingSong=false;
        songPaneOpen=false;
        playlistPaneOpen=false;



        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/media/loginVideo.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        homeVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(()->{

        });


        int numSongs = 5;

        ArrayList<StackPane> songStack = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        ArrayList<AnchorPane> anchors = new ArrayList<>();

        for(int i=0;i<numSongs;i++){
            songStack.add(new StackPane());
            rectangles.add(new Rectangle());
            rectangles.get(i).setWidth(960);
            rectangles.get(i).setHeight(50);
            rectangles.get(i).setFill(Color.web("#202020"));
            songStack.get(i).getChildren().add(rectangles.get(i));
            anchors.add(new AnchorPane());
            Label title = new Label("Title");
            title.setTextFill(Color.web("#FFFFFF"));
            title.setFont(new Font("Raleway",20));
            title.setLayoutX(30);
            title.setLayoutY(10);
            Label artist = new Label ("Artist");
            artist.setTextFill(Color.web("#FFFFFF"));
            artist.setFont(new Font("Raleway",20));
            artist.setLayoutX(235);
            artist.setLayoutY(10);
            Label album = new Label ("Album");
            album.setTextFill(Color.web("#FFFFFF"));
            album.setFont(new Font("Raleway",20));
            album.setLayoutX(390);
            album.setLayoutY(10);
            Label genre = new Label ("Genre");
            genre.setTextFill(Color.web("#FFFFFF"));
            genre.setFont(new Font("Raleway",20));
            genre.setLayoutX(545);
            genre.setLayoutY(10);
            Label date = new Label ("Date");
            date.setTextFill(Color.web("#FFFFFF"));
            date.setFont(new Font("Raleway",20));
            date.setLayoutX(700);
            date.setLayoutY(10);
            Label time = new Label ("Time");
            time.setTextFill(Color.web("#FFFFFF"));
            time.setFont(new Font("Raleway",20));
            time.setLayoutX(855);
            time.setLayoutY(10);
            anchors.get(i).getChildren().addAll(title,artist,album,genre,date,time);
            songStack.get(i).getChildren().addAll(anchors.get(i));
            songList.getChildren().add(songStack.get(i));
        }

        ArrayList<StackPane> playlistStack = new ArrayList<>();
        ArrayList<Rectangle> boxes = new ArrayList<>();
        ArrayList<AnchorPane> anchorPane = new ArrayList<>();

        for(int i=0;i<numSongs;i++){
            playlistStack.add(new StackPane());
            boxes.add(new Rectangle());
            boxes.get(i).setWidth(960);
            boxes.get(i).setHeight(50);
            boxes.get(i).setFill(Color.web("#202020"));
            playlistStack.get(i).getChildren().add(boxes.get(i));
            anchorPane.add(new AnchorPane());
            Label title = new Label("Title");
            title.setTextFill(Color.web("#FFFFFF"));
            title.setFont(new Font("Raleway",20));
            title.setLayoutX(30);
            title.setLayoutY(10);
            anchorPane.get(i).getChildren().addAll(title);
            playlistStack.get(i).getChildren().addAll(anchorPane.get(i));
            playList.getChildren().add(playlistStack.get(i));
        }

        editBtn.setOnMouseEntered(event -> {
            editLbl.setTextFill(Color.web("#f7620e"));
        });

        editBtn.setOnMouseExited(event -> {
            editLbl.setTextFill(Color.web("#FFFFFF"));
        });

        editBtn.setOnAction(event -> {

        });

        logoutBtn.setOnMouseEntered(event -> {
            logoutPic.setFitHeight(200);
            logoutPic.setFitWidth(200);
            logoutPic.setLayoutX(3);
            logoutPic.setLayoutY(466);
        });

        logoutBtn.setOnMouseExited(event -> {
            logoutPic.setFitHeight(150);
            logoutPic.setFitWidth(150);
            logoutPic.setLayoutX(25);
            logoutPic.setLayoutY(490);
        });

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to logout?");
        //alert.setContentText("Are you ok with this?");

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        logoutBtn.setOnAction(event -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                try {
                    FXMLLoader pane = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
                    Stage stage = (Stage) logoutBtn.getScene().getWindow();
                    Scene scene = new Scene(pane.load());
                    MainController controller = pane.getController();
                    controller.setDatabase(DB);
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.close();
            }
        });

        uploadBtn.setOnMouseEntered(event -> {
            uploadLbl.setTextFill(Color.web("#323232"));
        });

        uploadBtn.setOnMouseExited(event -> {
            uploadLbl.setTextFill(Color.web("#FFFFFF"));
        });
        
        playlistBtn.setOnMouseEntered(event -> {
            playlistLbl.setTextFill(Color.web( "#323232"));
        });

        playlistBtn.setOnMouseExited(event -> {
            playlistLbl.setTextFill(Color.web( "#FFFFFF"));
        });

        playlistBtn.setOnAction(event -> {
            if(playlistPaneOpen){
                playlistPane.setDisable(true);
                playlistPane.setVisible(false);
                playlistPaneOpen=false;
            }
            else {
                playlistPane.setDisable(false);
                playlistPane.setVisible(true);
                playlistPaneOpen=true;
                songListPane.setDisable(true);
                songListPane.setVisible(false);
                songPaneOpen=false;
            }
        });

        songsBtn.setOnMouseEntered(event -> {
            songsLbl.setTextFill(Color.web( "#323232"));
        });

        songsBtn.setOnMouseExited(event -> {
            songsLbl.setTextFill(Color.web( "#FFFFFF"));
        });


        songsBtn.setOnAction(event -> {
            if(songPaneOpen) {
                songListPane.setDisable(true);
                songListPane.setVisible(false);
                songPaneOpen=false;
            }else {
                songListPane.setDisable(false);
                songListPane.setVisible(true);
                songPaneOpen=true;
                playlistPane.setDisable(true);
                playlistPane.setVisible(false);
                playlistPaneOpen=false;
            }
        });

        shrinkBtn.setOnMouseClicked(event -> {
            userInfoPane.setVisible(false);
            userInfoPane.setDisable(true);
        });

        expandBtn.setOnMouseClicked(event -> {
            System.out.println(user.getUsername());
            welcomeLbl.setText(user.getUsername());

            userInfoPane.setVisible(true);
            userInfoPane.setDisable(false);
        });

        opacityOpenClose(shuffleBtn, repeatBtn, previousBtn);

        opacityOpenClose(forwardBtn, playBackBtn, fastForwardBtn);

        playBtn.setOnMouseEntered(event -> {
            playBtn.setOpacity(1.0);
            playBtn.setFitHeight(50);
            playBtn.setFitWidth(50);
            playBtn.setLayoutX(215);
            playBtn.setLayoutY(0);
        });

        playBtn.setOnMouseExited(event -> {
            playBtn.setOpacity(0.5);
            playBtn.setFitHeight(40);
            playBtn.setFitWidth(40);
            playBtn.setLayoutX(220);
            playBtn.setLayoutY(5);
        });

        playBtn.setOnMouseClicked(event -> {
            if(!isPlayingSong) {
                isPlayingSong = true;
                playBtn.setImage(new Image(getClass().getResourceAsStream("/media/Pause.png")));

                MediaPlayer songPlay = new MediaPlayer(new Media(getClass().getResource("/audio/AllIAsk.mp3").toExternalForm()));
                songPlay.setAutoPlay(true);
                songPlayer.setMediaPlayer(songPlay);
                songPlay.setOnReady(()->{

                });

            }else {
                isPlayingSong = false;
                playBtn.setImage(new Image(getClass().getResourceAsStream("/media/Play.png")));
            }
        });


    }

    private void opacityOpenClose(ImageView forwardBtn, ImageView playBtn, ImageView fastForwardBtn) {
        playBtn.setOnMouseEntered(event -> {
            playBtn.setOpacity(1.0);
        });

        playBtn.setOnMouseExited(event -> {
            playBtn.setOpacity(0.5);
        });

        forwardBtn.setOnMouseEntered(event -> {
            forwardBtn.setOpacity(1.0);
        });

        forwardBtn.setOnMouseExited(event -> {
            forwardBtn.setOpacity(0.5);
        });

        fastForwardBtn.setOnMouseEntered(event -> {
            fastForwardBtn.setOpacity(1.0);
        });

        fastForwardBtn.setOnMouseExited(event -> {
            fastForwardBtn.setOpacity(0.5);
        });
    }
}
