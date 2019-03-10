package view;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomePageController {
    @FXML
    private MediaView homeVideo, songPlayer;
    @FXML
    private Label songNameLbl, artistNameLbl, albumNameLbl, genreTypeLbl, yearLbl, favPlaylistLbl, favSong1Lbl, favSong2Lbl, favSong3Lbl, welcomLbl;
    @FXML
    private Label playlistLbl, songsLbl, editLbl, welcomeLbl, uploadLbl;
    @FXML
    private AnchorPane songInfoPane, userInfoPane, controlPane, mainPane, playlistPane, songListPane, editPane, songSettingPane;
    @FXML
    private ScrollPane songScrollPane, playlistScrollPane;
    @FXML
    private VBox songList, playList;
    @FXML
    private Button playlistBtn, songsBtn, logoutBtn, editBtn, uploadBtn, adjustBackBtn, adjustConfirmBtn;
    @FXML
    private ComboBox songSortBox;
    @FXML
    private ImageView repeatBtn, playBackBtn, previousBtn, playBtn, forwardBtn, fastForwardBtn, shuffleBtn, songPic, expandBtn, shrinkBtn, logoutPic, sortBtn;
    @FXML
    private ComboBox<?> albumSelect, song1Select, song2Select, song3Select;
    @FXML
    private Label userNameLabel, backLbl, confirmLbl, adjustBackLbl, adjustConfirmLbl;
    @FXML
    private Button backBtn, confirmBtn;

    private boolean isPlayingSong;
    private boolean songPaneOpen;
    private boolean playlistPaneOpen;
    private boolean isEditOpen;
    private boolean isSettingOpen;
    private boolean isSortOpen;
    private int previousSong;
    private int nextSong;
    private int previousPlaylist;
    private int nextPlaylist;

    private int selectedSongID;

    private List<Song> songGetAll;
    private List<Song> sortByAlbum;
    private List<Song> sortByTitle;
    private List<Song> sortByArtist;
    private List<Song> sortByGenre;
    private List<Song> sortByYear;
    private List<Song> sortByDuration;

    private List<Song> songData;


    private MedPlayer currentSong;

    private Database DB;
    private User user;

    public void setDatabase(Database DB) {
        this.DB = DB;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        DB = new Database();

        SongService songService = new SongService(DB);
        PlaylistService playlistSevice = new PlaylistService(DB);

        songData = songService.getAll();
        songGetAll = songService.getAll();
        sortByTitle = songService.sortbyTitle();
        sortByAlbum = songService.sortbyAlbum();
        sortByArtist = songService.sortbyArtist();
        sortByGenre = songService.sortbyGenre();
        sortByYear = songService.sortbyYear();
        sortByDuration = songService.sortbyDuration();
        

        List<Playlist> playlistData = playlistSevice.getAll();

        selectedSongID = -1;
        isPlayingSong = false;
        songPaneOpen = false;
        playlistPaneOpen = false;
        isEditOpen = false;
        isSettingOpen = false;
        isSortOpen = false;
        previousSong = 0;
        nextSong = 0;
        previousPlaylist = 0;
        nextPlaylist = 0;

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/media/loginVideo.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        homeVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> {

        });

        ArrayList<StackPane> songStack = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        ArrayList<AnchorPane> anchors = new ArrayList<>();

        setSongsView(songGetAll,songStack,rectangles,anchors);


        ArrayList<StackPane> playlistStack = new ArrayList<>();
        ArrayList<Rectangle> boxes = new ArrayList<>();
        ArrayList<AnchorPane> anchorPane = new ArrayList<>();

        for (int i = 0; i < playlistData.size(); i++) {
            playlistStack.add(new StackPane());
            boxes.add(new Rectangle());
            boxes.get(i).setWidth(220);
            boxes.get(i).setHeight(50);
            boxes.get(i).setFill(Color.web("#202020"));
            playlistStack.get(i).getChildren().add(boxes.get(i));
            anchorPane.add(new AnchorPane());
            Label title = new Label(playlistData.get(i).getPlaylistName());
            title.setMaxWidth(150);
            title.setTextFill(Color.web("#FFFFFF"));
            title.setFont(new Font("Raleway", 20));
            title.setLayoutX(10);
            title.setLayoutY(10);
            anchorPane.get(i).getChildren().addAll(title);
            int finalX = i;
            anchorPane.get(i).setOnMouseClicked(event -> {
                previousPlaylist = nextPlaylist;
                nextPlaylist = finalX;
                for (Node node : anchorPane.get(previousPlaylist).getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setTextFill(Color.web("#FFFFFF"));
                    }
                }
                for (Node node : anchorPane.get(nextPlaylist).getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setTextFill(Color.web("#f7620e"));
                    }
                }
            });

            anchorPane.get(i).setOnMouseEntered(event -> {
                boxes.get(finalX).setFill(Color.web("#323232"));
            });
            anchorPane.get(i).setOnMouseExited(event -> {
                boxes.get(finalX).setFill(Color.web("#202020"));
            });
            playlistStack.get(i).getChildren().addAll(anchorPane.get(i));
            playList.getChildren().add(playlistStack.get(i));
        }

        sortBtn.setOnMouseEntered(event -> {
            sortBtn.setOpacity(1.0);
        });

        sortBtn.setOnMouseExited(event -> {
            sortBtn.setOpacity(0.5);
        });

        sortBtn.setOnMouseClicked(event -> {
            if (!isSortOpen) {
                songSortBox.setDisable(false);
                songSortBox.setVisible(true);
                isSortOpen = true;
            } else {
                songSortBox.setDisable(true);
                songSortBox.setVisible(false);
                isSortOpen = false;
            }
        });

        songSortBox.getItems().addAll("Title", "Artist", "Album", "Genre", "Year", "Duration");

        songSortBox.setOnAction(event -> {
            if (songSortBox.getValue() == "Title") {
                setSongsView(sortByTitle,songStack,rectangles,anchors);
            }
            else if(songSortBox.getValue() == "Artist"){
                setSongsView(sortByArtist,songStack,rectangles,anchors);
            }
            else if(songSortBox.getValue() == "Album"){
                setSongsView(sortByAlbum,songStack,rectangles,anchors);
            }
            else if(songSortBox.getValue() == "Genre"){
                setSongsView(sortByGenre,songStack,rectangles,anchors);
            }
            else if(songSortBox.getValue() == "Year"){
                setSongsView(sortByYear,songStack,rectangles,anchors);
            }
            else if(songSortBox.getValue() == "Duration"){
                setSongsView(sortByDuration,songStack,rectangles,anchors);
            }
        });

        backBtn.setOnMouseEntered(event -> {
            backLbl.setTextFill(Color.web("#323232"));
        });

        backBtn.setOnMouseExited(event -> {
            backLbl.setTextFill(Color.web("#FFFFFF"));
        });

        backBtn.setOnAction(event -> {
            editPane.setVisible(false);
            editPane.setDisable(true);
            isEditOpen = false;
        });

        confirmBtn.setOnMouseEntered(event -> {
            confirmLbl.setTextFill(Color.web("#323232"));
        });

        confirmBtn.setOnMouseExited(event -> {
            confirmLbl.setTextFill(Color.web("#FFFFFF"));
        });

        confirmBtn.setOnAction(event -> {
            editPane.setVisible(false);
            editPane.setDisable(true);
            isEditOpen = false;
        });

        editBtn.setOnMouseEntered(event -> {
            editLbl.setTextFill(Color.web("#f7620e"));
        });

        editBtn.setOnMouseExited(event -> {
            editLbl.setTextFill(Color.web("#FFFFFF"));
        });

        editBtn.setOnAction(event -> {
            if (user.getId() != 0) {
                if (!isEditOpen) {
                    editPane.setVisible(true);
                    editPane.setDisable(false);
                    userNameLabel.setText("Username: " + user.getUsername());
                    isEditOpen = true;
                } else {
                    editPane.setVisible(false);
                    editPane.setDisable(true);
                    isEditOpen = false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Guests cannot Edit their profile!");
                alert.showAndWait();
            }
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
            if (result.get() == buttonTypeOne) {
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

        uploadBtn.setOnMouseClicked(event -> {
            /* Sources:
             * https://stackoverflow.com/questions/16433915/how-to-copy-file-from-one-location-to-another-location
             * https://www.youtube.com/watch?v=hNz8Xf4tMI4
             * */
            FileChooser fc = new FileChooser();
            List<File> selectedFiles;
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("WAV", "*.wav"));
//            fc.setInitialDirectory(new File(""));//set start directory
            String path = "./src/audio/";
            try {
                selectedFiles = fc.showOpenMultipleDialog(null);
                for (File x : selectedFiles) {
//                    System.out.println(x.getName()); bug testing stuffs
//                    System.out.println(x.getName().split("\\.")[0]);
                    Files.copy(x.toPath(), (new File(path + x.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (NullPointerException e) {
                System.out.println("No File Selected!");
            } catch (Exception e) {
                System.out.println("Unknown Error detected!");
                e.printStackTrace();
            }
        });

        playlistBtn.setOnMouseEntered(event -> {
            playlistLbl.setTextFill(Color.web("#323232"));
        });

        playlistBtn.setOnMouseExited(event -> {
            playlistLbl.setTextFill(Color.web("#FFFFFF"));
        });

        playlistBtn.setOnAction(event -> {
            if (playlistPaneOpen) {
                playlistPane.setDisable(true);
                playlistPane.setVisible(false);
                playlistPaneOpen = false;
            } else {
                playlistPane.setDisable(false);
                playlistPane.setVisible(true);
                playlistPaneOpen = true;
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
            if (!isPlayingSong) {
                isPlayingSong = true;
                playBtn.setImage(new Image(getClass().getResourceAsStream("/media/Pause.png")));

                playSong(DB);

            } else {
                isPlayingSong = false;
                playBtn.setImage(new Image(getClass().getResourceAsStream("/media/Play.png")));
                pauseSong(currentSong);
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

    private void setSongsView(List<Song> songData,ArrayList<StackPane> songStack,ArrayList<Rectangle> rectangles,ArrayList<AnchorPane> anchors) {

        int numSongs = songData.size();


        songList.getChildren().clear();
        songStack.clear();
        rectangles.clear();
        anchors.clear();

        for(int i=0;i<numSongs;i++){

            songStack.add(new StackPane());
            rectangles.add(new Rectangle());
            rectangles.get(i).setWidth(775);
            rectangles.get(i).setHeight(50);
            rectangles.get(i).setFill(Color.web("#202020"));
            songStack.get(i).getChildren().add(rectangles.get(i));
            anchors.add(new AnchorPane());
            ImageView gear = new ImageView(new Image(getClass().getResourceAsStream("/media/gearWhite.png")));
            gear.setFitWidth(30);
            gear.setFitHeight(25);
            gear.setLayoutX(0);
            gear.setLayoutY(10);
            gear.setOpacity(0.0);
            gear.setDisable(true);
            gear.setOnMouseEntered(event -> {
                gear.setImage(new Image(getClass().getResourceAsStream("/media/gearOrange.png")));
            });
            gear.setOnMouseExited(event -> {
                gear.setImage(new Image(getClass().getResourceAsStream("/media/gearWhite.png")));
            });
            gear.setOnMouseClicked(event -> {
                if(!isSettingOpen) {
                    songSettingPane.setVisible(true);
                    songSettingPane.setDisable(false);
                    isSettingOpen = true;
                }else {
                    songSettingPane.setVisible(false);
                    songSettingPane.setDisable(true);
                    isSettingOpen = false;
                }
            });

            adjustBackBtn.setOnMouseEntered(event -> {
                adjustBackLbl.setTextFill(Color.web( "#323232"));
            });

            adjustBackBtn.setOnMouseExited(event -> {
                adjustBackLbl.setTextFill(Color.web( "#FFFFFF"));
            });

            adjustBackBtn.setOnAction(event -> {
                songSettingPane.setVisible(false);
                songSettingPane.setDisable(true);
                isSettingOpen=false;
            });

            adjustConfirmBtn.setOnMouseEntered(event -> {
                adjustConfirmLbl.setTextFill(Color.web( "#323232"));
            });

            adjustConfirmBtn.setOnMouseExited(event -> {
                adjustConfirmLbl.setTextFill(Color.web( "#FFFFFF"));
            });

            adjustConfirmBtn.setOnAction(event -> {
                songSettingPane.setVisible(false);
                songSettingPane.setDisable(true);
                isSettingOpen=false;
            });

            Label title = new Label(songData.get(i).getSongTitle());
            title.setMaxWidth(180);
            title.setTextFill(Color.web("#FFFFFF"));
            title.setFont(new Font("Raleway",20));
            title.setLayoutX(25);
            title.setLayoutY(10);
            Label artist = new Label (songData.get(i).getArtist());
            artist.setMaxWidth(135);
            artist.setTextFill(Color.web("#FFFFFF"));
            artist.setFont(new Font("Raleway",20));
            artist.setLayoutX(220);
            artist.setLayoutY(10);
            Label album = new Label (songData.get(i).getAlbum());
            album.setMaxWidth(130);
            album.setTextFill(Color.web("#FFFFFF"));
            album.setFont(new Font("Raleway",20));
            album.setLayoutX(365);
            album.setLayoutY(10);
            Label genre = new Label (songData.get(i).getGenre());
            genre.setMaxWidth(110);
            genre.setTextFill(Color.web("#FFFFFF"));
            genre.setFont(new Font("Raleway",20));
            genre.setLayoutX(505);
            genre.setLayoutY(10);
            Label date = new Label (songData.get(i).getYear()+"");
            date.setMaxWidth(100);
            date.setTextFill(Color.web("#FFFFFF"));
            date.setFont(new Font("Raleway",20));
            date.setLayoutX(625);
            date.setLayoutY(10);
            Label time = new Label (songData.get(i).getDuration()+"");
            time.setMaxWidth(100);
            time.setTextFill(Color.web("#FFFFFF"));
            time.setFont(new Font("Raleway",20));
            time.setLayoutX(710);
            time.setLayoutY(10);
            anchors.get(i).getChildren().addAll(gear,title,artist,album,genre,date,time);
            int finalI = i;
            anchors.get(i).setOnMouseClicked(event -> {
                previousSong=nextSong;
                nextSong=finalI;
                for (Node node : anchors.get(previousSong).getChildren()) {
                    if (node instanceof Label) {
                        ((Label)node).setTextFill(Color.web("#FFFFFF"));
                    }
                }
                for (Node node : anchors.get(nextSong).getChildren()) {
                    if (node instanceof Label) {
                        ((Label)node).setTextFill(Color.web("#f7620e"));
                        selectedSongID=finalI;
                        songNameLbl.setText(songData.get(selectedSongID).getSongTitle());
                        artistNameLbl.setText(songData.get(selectedSongID).getArtist());
                        albumNameLbl.setText(songData.get(selectedSongID).getAlbum());
                        genreTypeLbl.setText(songData.get(selectedSongID).getGenre());
                        yearLbl.setText(songData.get(selectedSongID).getYear()+"");

                    }
                }
            });

            anchors.get(i).setOnMouseEntered(event -> {
                rectangles.get(finalI).setFill(Color.web("#323232"));
                gear.setDisable(false);
                gear.setOpacity(1.0);
            });
            anchors.get(i).setOnMouseExited(event -> {
                rectangles.get(finalI).setFill(Color.web("#202020"));
                gear.setDisable(true);
                gear.setOpacity(0.0);
            });
            songStack.get(i).getChildren().addAll(anchors.get(i));
            songList.getChildren().add(songStack.get(i));
        }
    }

    private void playSong(Database DB){
        SongService songService = new SongService(DB);
        System.out.println("/audio/" + songService.getAll().get(selectedSongID).getSongTitle() + ".mp3");
        currentSong=new MedPlayer(songService.getAll().get(selectedSongID).getSongTitle());

        currentSong.play();
    }

    private void pauseSong(MedPlayer currentSong){
        currentSong.pause();
    }
}

