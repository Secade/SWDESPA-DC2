package view;

import javafx.scene.control.Alert;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class PlaylistController {

    private int playlistIndex;

    //FR Jeremy: these are all the list of songs that work for me
    public PlaylistController(){
        playlistIndex = 0;
        MediaController.mp.getSonglist().add(new File("04 Haiku"));
        MediaController.mp.getSonglist().add(new File("13 Mercury"));
        MediaController.mp.getSonglist().add(new File("15 Mira"));
        MediaController.mp.getSonglist().add(new File("AllIAsk"));
        MediaController.mp.getSonglist().add(new File("Maroon 5 - This Love"));
        MediaController.mp.getSonglist().add(new File("Meghan Trainor - Me Too"));
        MediaController.mp.getSonglist().add(new File("Nelly Furtado - Say It Right"));
        MediaController.mp.getSonglist().add(new File("The Apex"));
        MediaController.mp.getSonglist().add(new File("The Deep"));
        MediaController.mp.getSonglist().add(new File("The Greatest Showman - Never Enough"));
        MediaController.mp.getSonglist().add(new File("The Greatest Showman - This Is Me"));
    }

    //USES THE ARRAY LIST
    public int shuffle(){
        Random index = new Random();
        int random = index.nextInt(MediaController.mp.getSonglist().size() - 1);
        return random;
    }

    //called ONLY if repeat is TRUE
    public void repeatIndex(){
        if (checkSize() == 1){ //for next songs
            playlistIndex = 0;
        }
        else if (checkSize() == -1){ // for previous songs
            playlistIndex = MediaController.mp.getSonglist().size()-1;
        }
    }

    public int checkSize(){
        if (playlistIndex+1 >= MediaController.mp.getSonglist().size()){ //at end of list
            return 1;
        }
        else if(playlistIndex <= 0){ //at start of list
            return -1;
        }
        else //w/in range
            return 0;
    }

    public void nextSongInPlaylist(){
        // if set on repeat
        if (MediaController.mp.isOnRepeat()){
            repeatIndex();
        }

        // ---- //
        if (MediaController.mp.isShuffled()){
            MediaController.mp.pickSong(MediaController.mp.getSonglist().get(shuffle()));
        }

        else if (!MediaController.mp.isShuffled()){
            if (checkSize() == 1){ //END OF PLAYLIST
                MediaController.mp.getMediaPlayer().stop();
            }
            else{ //NOT AT END OF PLAYLIST
                MediaController.mp.pickSong(MediaController.mp.getSonglist().get(nextIndex()));
            }
        }
    }

    public void previousSongInPlaylist(){
        if (MediaController.mp.isOnRepeat()){
            repeatIndex();
        }

        if (MediaController.mp.isShuffled()){
            MediaController.mp.pickSong(MediaController.mp.getSonglist().get(shuffle()));
        }
        else{
            if (checkSize() == -1)
                MediaController.mp.getMediaPlayer().setCycleCount(-1);
            else
                MediaController.mp.pickSong(MediaController.mp.getSonglist().get(prevIndex()));
        }
    }

    public void addSongInPlaylist(File file){
        MediaController.mp.getSonglist().add(file);
    }

    public void removeSongInPlaylist(File file){
        if (MediaController.mp.getSonglist().contains(file)){
            MediaController.mp.getSonglist().remove(file);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File not found");
            alert.setHeaderText("Sorry. We didn't find the file you were looking for.");
            alert.showAndWait();
        }
    }

    public int nextIndex(){
        playlistIndex++;
        return playlistIndex;
    }

    public int prevIndex(){
        playlistIndex--;
        return playlistIndex;
    }
}
