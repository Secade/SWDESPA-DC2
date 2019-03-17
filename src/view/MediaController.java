package view;

import javafx.util.Duration;
import model.MedPlayer;
import model.Song;

import java.io.File;
import java.util.Random;

public class MediaController {
    public static MedPlayer mp = new MedPlayer();
    public static PlaylistController pc = new PlaylistController();

    public MediaController(){
        mp.pickSong(new File("04 Haiku"));
    }

    public void play(){
        if (mp.getMediaPlayer().getCurrentTime() == mp.getEndTime()){
            stop();
        }
        else{
            mp.getMediaPlayer().play();
        }
    }

    public void pause(){
        mp.getMediaPlayer().pause();
    }

    public void stop(){
        mp.getMediaPlayer().stop();
    }

    public void repeat(){
        if (mp.getMediaPlayer().getCycleCount() == -1){
            //play once
            mp.getMediaPlayer().setCycleCount(1);
            mp.setOnRepeat(false);
        }
        else{
            //numerical value = -1
            mp.getMediaPlayer().setCycleCount(mp.getMediaPlayer().INDEFINITE);
            mp.setOnRepeat(true);
        }
    }

    public void fastForward(){
        mp.getMediaPlayer().seek(mp.getMediaPlayer().getCurrentTime().add(Duration.millis(10*1000)));
    }

    public void nextSong(){
        stop();
        pc.nextSongInPlaylist();
        play();
    }

    public boolean checkIfDone(){
        if (mp.getMediaPlayer().getCurrentTime().toMillis() >= mp.getMediaPlayer().getStopTime().toMillis()){
            return true;
        }
        else
            return false;
    }


    //PLS FIX
    public void previousSong(){
        if (mp.getMediaPlayer().getCurrentTime().toSeconds() >= 5){
            playback();
        }
        else{
            stop();
            pc.previousSongInPlaylist();
            play();
        }
    }

    public void playback(){
        mp.getMediaPlayer().seek(mp.getStartTime());
    }
}