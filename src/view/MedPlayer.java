package view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;

public class MedPlayer {

    private String file;
    private Media hit;
    private MediaPlayer mediaPlayer;
    private MediaView music;

    public MedPlayer(File filename){
        final JFXPanel fxpanel = new JFXPanel();
        //file = "C:\\Users\\LENOVO\\GitHub Repository\\SWDESPA-DC2\\src\\audio\\" + filename + ".mp3";
        hit = new Media((getClass().getResource("/audio/"+filename+".mp3").toExternalForm()));
        mediaPlayer = new MediaPlayer(hit);
        music = new MediaView(mediaPlayer);
    }

    public void play(){
        mediaPlayer.play();
        System.out.println("played");
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Nelly Furtado - Say It Right");
        MedPlayer me = new MedPlayer(file);
        me.play();
    }
}
