package view;

import javafx.util.Duration;
import model.MedPlayer;
import java.io.File;
import java.util.Random;

public class MediaController {

    public MedPlayer mp = new MedPlayer();

    public MediaController(){
        mp.pickSong(new File("Nelly Furtado - Say It Right"));
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
        }
        else{
            //numerical value = -1
            mp.getMediaPlayer().setCycleCount(mp.getMediaPlayer().INDEFINITE);
        }
    }

    public void fastForward(){
        mp.getMediaPlayer().seek(mp.getMediaPlayer().getCurrentTime().add(Duration.millis(10*1000)));
    }

    //FIX TO IMPLEM'T QUEUE
    public void nextSong(){
        stop();
        if (mp.isShuffled())
            mp.pickSong(new File(String.valueOf(mp.getSonglist().get(shuffle()))));
        else
            mp.pickSong(new File("Tear of the Goddess")); //not generic; should get the next Song file inside the ArrayList

        play();
    }

    //USES THE ARRAY LIST
    public int shuffle(){
        mp.setShuffled(true);
        Random index = new Random();
        int random = index.nextInt(mp.getSonglist().size() - 1);
        return random +1;
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
            if (mp.isShuffled()){
                mp.pickSong(new File(String.valueOf(mp.getSonglist().get(shuffle()))));
            }
            else{
                mp.pickSong(new File("The Bloodthirster"));
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