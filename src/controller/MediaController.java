package controller;

import javafx.util.Duration;
import view.MedPlayer;
import java.io.File;
import java.util.Random;

public class MediaController {

    protected MedPlayer mp = new MedPlayer();

    public MediaController(){
        mp.pickSong(new File("Meghan Trainor - Me Too"));
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
            mp.getMediaPlayer().setCycleCount(1);
        }
        else{
            mp.getMediaPlayer().setCycleCount(mp.getMediaPlayer().INDEFINITE);
        }
    }

    public void fastForward(){
            mp.getMediaPlayer().seek(mp.getMediaPlayer().getCurrentTime().add(Duration.millis(10*1000)));
    }

    public void nextSong(){
        stop();
        if (mp.isShuffled())
            mp.pickSong(new File(String.valueOf(mp.getSonglist().get(shuffle()))));
        else
            mp.pickSong(new File("Nelly Furtado - Say It Right")); //not generic; should get the next Song file inside the ArrayList

        play();

    }

    public int shuffle(){
        mp.setShuffled(true);
        Random index = new Random();
        int random = index.nextInt(mp.getSonglist().size() - 1);
        return random +1;
    }

    public void previousSong(){ //same as above
        if (mp.getMediaPlayer().getCurrentTime().toSeconds() >= 5){
            playback();
        }
        else{
            stop();
            if (mp.isShuffled()){
                mp.pickSong(new File(String.valueOf(mp.getSonglist().get(shuffle()))));
            }
            else{
                mp.pickSong(new File("Maroon 5 - This Love"));
            }
            play();
        }
    }

    public void playback(){
        mp.getMediaPlayer().seek(mp.getStartTime());
    }

    public void addSong(File filename){
        mp.getSonglist().add(filename);
    }

}