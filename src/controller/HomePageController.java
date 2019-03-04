package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import view.MedPlayer;

import java.io.File;

public class HomePageController implements EventHandler<MouseEvent> {

    MedPlayer mediaPlayer = new MedPlayer(new File("Meghan Trainor - Me Too"));

    @FXML
    private MediaView homeVideo;
    @FXML
    private Label songNameLbl, artistNameLbl, albumNameLbl, genreTypeLbl, yearLbl, favPlaylistLbl, favSong1Lbl, favSong2Lbl, favSong3Lbl;
    @FXML
    private Label playlistLbl, songsLbl, songStartTime, songEndTime;
    @FXML
    private AnchorPane songInfoPane, userInfoPane, controlPane, mainPane;
    @FXML
    private Button playlistBtn, songsBtn;
    @FXML
    private ImageView repeatBtn, playBackBtn, previousBtn, playBtn, forwardBtn, fastForwardBtn, shuffleBtn, songPic, expandBtn, shrinkBtn;

    public void initialize(){
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/media/loginVideo.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        homeVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(()->{

        });

        playlistBtn.setOnMouseEntered(event -> {
            playlistLbl.setTextFill(Color.web( "#f7620e"));
        });

        playlistBtn.setOnMouseExited(event -> {
            playlistLbl.setTextFill(Color.web( "#FFFFFF"));
        });

        songsBtn.setOnMouseEntered(event -> {
            songsLbl.setTextFill(Color.web( "#f7620e"));
        });

        songsBtn.setOnMouseExited(event -> {
            songsLbl.setTextFill(Color.web( "#FFFFFF"));
        });

        shrinkBtn.setOnMouseClicked(event -> {
            userInfoPane.setVisible(false);
            userInfoPane.setDisable(true);
        });

        expandBtn.setOnMouseClicked(event -> {
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

        playBtn.setOnMouseClicked(this);

        playBtn.setOnMouseExited(event -> {
            playBtn.setOpacity(0.5);
            playBtn.setFitHeight(40);
            playBtn.setFitWidth(40);
            playBtn.setLayoutX(220);
            playBtn.setLayoutY(5);
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

    @Override
    public void handle(MouseEvent event) {

        if (!mediaPlayer.isPlaying()){
            mediaPlayer.play();
            songNameLbl.setText("Me Too");
            artistNameLbl.setText("Meghan Trainor");
            albumNameLbl.setText("Thank You");
            genreTypeLbl.setText("Pop");
            yearLbl.setText("2016");
            songStartTime.setText(String.valueOf(mediaPlayer.getStartTime().toMinutes()));
            songEndTime.setText(String.valueOf(mediaPlayer.getEndTime()));
        }
        else if (mediaPlayer.isPlaying()){
            System.out.println("paused from HomePageController");
            mediaPlayer.pause();
        }
    }
}
