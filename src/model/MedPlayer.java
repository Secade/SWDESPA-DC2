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

	public MedPlayer(){
        songlist = new ArrayList<>();
        initSongs();
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

    public void initSongs(){
        songlist.add(new File("Maroon 5 - This Love"));
        songlist.add(new File("Meghan Trainor - Me Too"));
        songlist.add(new File("Nelly Furtado - Say It Right"));
        songlist.add(new File("The Greatest Showman - Never Enough"));
        songlist.add(new File("The Greatest Showman - This Is Me"));
    }

    public Duration getStartTime(){
        return mediaPlayer.getStartTime();
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
