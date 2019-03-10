package controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.script.Bindings;

public class HomePageController implements EventHandler<MouseEvent> {

    //plays music; replaced the MedPlayer
    MediaController musicControl = new MediaController();

    @FXML
    private Slider mediaSlider, volumeSlider;

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
    private ImageView repeatBtn, playBackBtn, previousBtn, playBtn, forwardBtn, fastForwardBtn, shuffleBtn, songPic, expandBtn, shrinkBtn, volumeImg;



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

        playBtn.setOnMouseExited(event -> {
            playBtn.setOpacity(0.5);
            playBtn.setFitHeight(40);
            playBtn.setFitWidth(40);
            playBtn.setLayoutX(220);
            playBtn.setLayoutY(5);
        });

        //play/pause song (dependent on media status)
        playBtn.setOnMouseClicked(this);

        //jump forward 10 seconds
        fastForwardBtn.setOnMouseClicked(e -> {
            musicControl.fastForward();
        });

        //repeat current song
        playBackBtn.setOnMouseClicked(event -> {
            musicControl.playback();
        });

        //set on repeat
        repeatBtn.setOnMouseClicked(e -> {
            musicControl.repeat();
        });

        //next song
        forwardBtn.setOnMouseClicked(e -> {
            musicControl.nextSong();
        });

        //previous song
        previousBtn.setOnMouseClicked(e -> {
            musicControl.previousSong();
        });

        //shuffle button
        shuffleBtn.setOnMouseClicked(e -> {
            if (musicControl.mp.isShuffled()){
                musicControl.mp.setShuffled(false);
            }
            else{
                musicControl.mp.setShuffled(true);
                musicControl.shuffle();
            }
        });

        // volumeslider
        volumeSlider.setValue(musicControl.mp.getMediaPlayer().getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                musicControl.mp.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
            }
        });

        volumeImg.setOnMouseClicked(e -> {
            if (musicControl.mp.getMediaPlayer().isMute()){
                musicControl.mp.getMediaPlayer().setMute(false);
            }
            else{
                musicControl.mp.getMediaPlayer().setMute(true);
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

    @Override
    public void handle(MouseEvent event) {

        if (musicControl.mp.getMediaPlayerStatus() == musicControl.mp.getMediaPlayerStatus().PLAYING){
            System.out.println("paused from HomePageController");
            musicControl.pause();
        }
        else{
            System.out.println("play");
            musicControl.play();
            songNameLbl.setText("change");
            artistNameLbl.setText("change");
            albumNameLbl.setText("change");
            genreTypeLbl.setText("change");
            yearLbl.setText("change");
        }
    }
}
