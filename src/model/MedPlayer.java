package model;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

public class MedPlayer {

    private ArrayList<File> songlist;
    private Media hit;
    private MediaPlayer mediaPlayer;
    private MediaView music;
    private boolean isShuffled;
    private boolean isFinished;
    private boolean isOnRepeat;

    public void setSonglist(ArrayList<File> songlist) {
        this.songlist = songlist;
    }

    public Media getHit() {
        return hit;
    }

    public void setHit(Media hit) {
        this.hit = hit;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public boolean isOnRepeat() {
        return isOnRepeat;
    }

    public void setOnRepeat(boolean onRepeat) {
        isOnRepeat = onRepeat;
    }

    public MedPlayer(){
        songlist = new ArrayList<>();
    }

    //picks the song
    public void pickSong(File filename){
        hit = new Media(getClass().getResource("/audio/" + filename + ".mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(hit);
        music = new MediaView(mediaPlayer);
    }
	
    public boolean isShuffled() {
        return isShuffled;
    }

    public void setShuffled(boolean shuffled) {
        isShuffled = shuffled;
    }

    public boolean isFinished(){
        return isFinished;
    }

    public void setFinished(boolean finished){
        isFinished = finished;
    }

    //returns the media being played
    public Media getSong(){
        return hit;
    }

    public Duration getStartTime(){
        return mediaPlayer.getStartTime();
    }

    public Duration getDuration(){
	    return hit.getDuration();
    }

    public Duration getEndTime(){
        return mediaPlayer.getStopTime();
    }

    public Duration getCurrentTime(){
        return mediaPlayer.getCurrentTime();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public MediaView getMusic() {
        return music;
    }

    public ArrayList<File> getSonglist() {
        return songlist;
    }

    public MediaPlayer.Status getMediaPlayerStatus() {
        return mediaPlayer.getStatus();
    }
}
