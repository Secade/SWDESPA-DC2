package view;

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
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
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
    private AnchorPane songInfoPane, userInfoPane, controlPane, mainPane, playlistPane, songListPane, editPane, songSettingPane,mainStage;
    @FXML
    private ScrollPane songScrollPane, playlistScrollPane;
    @FXML
    private VBox songList, playList;
    @FXML
    private Button playlistBtn, songsBtn, logoutBtn, editBtn, uploadBtn, adjustBackBtn, adjustConfirmBtn,createPlaylistBtn,addSongToPlaylistBtn,reset;
    @FXML
    private ComboBox songSortBox;
    @FXML
    private ImageView repeatBtn, playBackBtn, previousBtn, playBtn, forwardBtn, fastForwardBtn, shuffleBtn, songPic, expandBtn, shrinkBtn, logoutPic, sortBtn;
    @FXML
    private Label userNameLabel, backLbl, confirmLbl, adjustBackLbl, adjustConfirmLbl,mostPlayedSong;
    @FXML
    private Button backBtn, confirmBtn,minimize,closeScreen,searchBtn;
    @FXML
    private ChoiceBox albumSelect, song1Select, song2Select, song3Select;
    @FXML
    private Rectangle titleBar;
    @FXML
    private TextField searchBar,editTitle,editArtist,editAlbum,editGenre,editDate,editTime;

    private boolean isPlayingSong;
    private boolean songPaneOpen;
    private boolean playlistPaneOpen;
    private boolean isEditOpen;
    private boolean isSettingOpen;
    private boolean isSortOpen;
    private boolean favPlaylistLoaded;
    private boolean favSong1Loaded;
    private boolean favSong2Loaded;
    private boolean favSong3Loaded;
    private boolean playlistLoaded;
    private int previousSong;
    private int nextSong;
    private int previousPlaylist;
    private int nextPlaylist;

    private int selectedSongID;
    private int selectedPlaylistID;

    private String newestSong;

    private List<Song> songGetAll;
    private List<Song> sortByAlbum;
    private List<Song> sortByTitle;
    private List<Song> sortByArtist;
    private List<Song> sortByGenre;
    private List<Song> sortByYear;
    private List<Song> sortByDuration;

    private List<Song> songData;

    private double xOffset = 0;
    private double yOffset = 0;

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
        SongInPlaylistService songInPlaylistService = new SongInPlaylistService(DB);
        UserWithSongService userWithSongService = new UserWithSongService(DB);

//        songData = songService.getAll();
//        songGetAll = songService.getAll();
//        sortByTitle = songService.sortbyTitle();
//        sortByAlbum = songService.sortbyAlbum();
//        sortByArtist = songService.sortbyArtist();
//        sortByGenre = songService.sortbyGenre();
//        sortByYear = songService.sortbyYear();
//        sortByDuration = songService.sortbyDuration();



        selectedSongID = -1;
        isPlayingSong = false;
        songPaneOpen = false;
        playlistPaneOpen = false;
        isEditOpen = false;
        isSettingOpen = false;
        isSortOpen = false;
        favPlaylistLoaded=false;
        favSong1Loaded=false;
        favSong2Loaded=false;
        favSong3Loaded=false;
        playlistLoaded=false;
        previousSong = 0;
        nextSong = 0;
        previousPlaylist = 0;
        nextPlaylist = 0;

        ArrayList<StackPane> songStack = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        ArrayList<AnchorPane> anchors = new ArrayList<>();

        //setSongsView(songGetAll,songStack,rectangles,anchors);

        ArrayList<StackPane> playlistStack = new ArrayList<>();
        ArrayList<Rectangle> boxes = new ArrayList<>();
        ArrayList<AnchorPane> anchorPane = new ArrayList<>();

        mainStage.setOnMouseEntered(event -> {
            if(!playlistLoaded) {
                List<Playlist> playlistData = playlistSevice.getAll(0);
                playlistData.addAll(playlistSevice.getAll(user.getId()));
                setPlaylistView(playlistData, playlistStack, boxes, anchorPane, songStack, rectangles, anchors, songInPlaylistService, songService);
                playlistLoaded = true;
            }
        });


        reset.setOnAction(event -> {
            this.initialize();
        });

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

        songSortBox.getItems().addAll("songTitle", "artist", "album", "genre", "Year", "duration");

        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //move around here
        titleBar.setOnMouseDragged(event -> {
            Stage primaryStage = (Stage) titleBar.getScene().getWindow();
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        minimize.setOnAction(event -> {
            // get a handle to the stage
            Stage stage = (Stage) minimize.getScene().getWindow();
            // do what you have to do
            stage.setIconified(true);
        });

        closeScreen.setOnAction(event -> {
            Stage stage = (Stage) closeScreen.getScene().getWindow();
            // do what you have to do
            stage.close();
        });

        songSortBox.setOnAction(event -> {
            System.out.println(nextPlaylist);
            System.out.println(songSortBox.getValue().toString());
            if(selectedPlaylistID==0) {
                setSongsView(songService.sort(songSortBox.getValue().toString()),songStack,rectangles,anchors);
            }else {
                setSongsView(songInPlaylistService.sort(nextPlaylist, songSortBox.getValue().toString()), songStack, rectangles, anchors);
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
                    System.out.println(x.getName());
                    newestSong=x.getName();
                }
            } catch (NullPointerException e) {
                System.out.println("No File Selected!");
            } catch (Exception e) {
                System.out.println("Unknown Error detected!");
                e.printStackTrace();
            }

            try {
                Database DB = new Database();
                SongService service = new SongService(DB);

                File folder = new File("./src/audio");
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for(int y =0;y<listOfFiles.length;y++) {
                        if (listOfFiles[y].isFile()&&listOfFiles[y].getName().compareToIgnoreCase(newestSong)==0) {
                            InputStream input = new FileInputStream(new File("./src/audio/" + listOfFiles[y].getName()));
                            ContentHandler handler = new DefaultHandler();
                            Metadata metadata = new Metadata();
                            Parser parser = new Mp3Parser();
                            ParseContext parseCtx = new ParseContext();
                            parser.parse(input, handler, metadata, parseCtx);
                            input.close();

                            // List all metadata
                            String[] metadataNames = metadata.names();

                            for (String name : metadataNames) {
                                System.out.println(name + ": " + metadata.get(name));
                            }

                            System.out.println("File " + listOfFiles[y].getName());
                            try {
                                Song song = new Song();
                                song.setSongID(service.getAll().size() + 1);
                                song.setFilename(metadata.get("title") + ".mp3");
                                song.setSongTitle(metadata.get("title"));
                                song.setArtist(metadata.get("xmpDM:artist"));
                                song.setAlbum(metadata.get("xmpDM:album"));
                                song.setGenre(metadata.get("xmpDM:genre"));
                                song.setYear(Integer.parseInt(metadata.get("xmpDM:releaseDate")));
                                float songDuration = ((Float.parseFloat(metadata.get("xmpDM:duration"))/1000)/60);
                                System.out.println(songDuration);
                                song.setDuration(songDuration);
                                service.add(song);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            this.initialize();
                        }
                    }
                    }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TikaException e) {
                e.printStackTrace();
            }


        });

        shrinkBtn.setOnMouseClicked(event -> {
            userInfoPane.setVisible(false);
            userInfoPane.setDisable(true);
        });

        expandBtn.setOnMouseClicked(event -> {
            System.out.println(user.getUsername());
            welcomeLbl.setText(user.getUsername());

            if(user.getId()!=0) {
                mostPlayedSong.setText(userWithSongService.getMostPlayed(user.getId()).getSongTitle());
            }

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
                currentSong.play();

                songNameLbl.setText(songData.get(selectedSongID).getSongTitle());
                artistNameLbl.setText(songData.get(selectedSongID).getArtist());
                albumNameLbl.setText(songData.get(selectedSongID).getAlbum());
                genreTypeLbl.setText(songData.get(selectedSongID).getGenre());
                yearLbl.setText(songData.get(selectedSongID).getYear()+"");

                if(user.getId()!=0) {
                    userWithSongService.updatePlayCount(selectedSongID+1,user.getId());

                    mostPlayedSong.setText(userWithSongService.getMostPlayed(user.getId()).getSongTitle());
                }

            } else {
                isPlayingSong = false;
                playBtn.setImage(new Image(getClass().getResourceAsStream("/media/Play.png")));
                currentSong.pause();
            }
        });

        createPlaylistBtn.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Create Playlist");
            dialog.setHeaderText("Playlist");
            dialog.setContentText("Enter Playlist Name:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                System.out.println("Your name: " + result.get());
                Playlist createP = new Playlist();
                createP.setPlaylistName(result.get());
                createP.setPlaylistID(playlistSevice.getAll(user.getId()).size()+1);
                createP.setUserID(user.getId());
                playlistSevice.add(createP);
            }
            this.initialize();
        });

        addSongToPlaylistBtn.setOnAction(event -> {
            List<String> playlist = new ArrayList<>();
            for(int j=0;j<playlistSevice.getAll(user.getId()).size();j++)
                playlist.add(playlistSevice.getAll(user.getId()).get(j).getPlaylistName());
            List<String> songs = new ArrayList<>();
            for(int k=0;k<songService.getAll().size();k++)
                songs.add(songService.getAll().get(k).getSongTitle());

            ChoiceDialog<String> dialog = new ChoiceDialog<>("", playlist);
            dialog.setTitle("Add Song To Playlist");
            dialog.setHeaderText("Add Song");
            dialog.setContentText("Choose which Playlist");

            ChoiceDialog<String> song = new ChoiceDialog<>("", songs);
            song.setTitle("Add Song To Playlist");
            song.setHeaderText("Add Song");
            song.setContentText("Choose which Song");


// Traditional way to get the response value.
            Optional<String> result1 = dialog.showAndWait();
            if (result1.isPresent()){
                System.out.println("Your choice: " + result1.get());
            }
            Optional<String> result2 = song.showAndWait();
            if (result2.isPresent()){
                System.out.println("Your choice: " + result2.get());
                SongInPlaylist songInPlaylist = new SongInPlaylist();
                songInPlaylist.setUserID(user.getId());
                songInPlaylist.setSongInPlaylistID(songInPlaylistService.getAll().size()+1);
                for(int g=1;g<songService.getAll().size();g++) {
                    if (songService.getAll().get(g).getSongTitle().compareToIgnoreCase(result2.get()) == 0)
                        songInPlaylist.setSongID(g+1);
                }
                for(int h=0;h<playlistSevice.getComplete().size();h++) {
                    if (playlistSevice.getComplete().get(h).getPlaylistName().compareToIgnoreCase(result1.get()) == 0)
                        songInPlaylist.setPlaylistID(h);
                }
                songInPlaylistService.add(songInPlaylist);
            }
            this.initialize();
        });

        albumSelect.setOnMouseClicked(event -> {
            if(!favPlaylistLoaded) {
                for (int v = 0; v < playlistSevice.getAll(user.getId()).size(); v++)
                    albumSelect.getItems().add(playlistSevice.getAll(user.getId()).get(v).getPlaylistName());
                favPlaylistLoaded=true;
            }
        });

        song1Select.setOnMouseClicked(event -> {
            if(!favSong1Loaded) {
                for (int m = 0; m < songService.getAll().size(); m++)
                    song1Select.getItems().add(songService.getAll().get(m).getSongTitle());
                favSong1Loaded=true;
            }
        });

        song2Select.setOnMouseClicked(event -> {
            if(!favSong2Loaded) {
                for (int n = 0; n < songService.getAll().size(); n++)
                    song2Select.getItems().add(songService.getAll().get(n).getSongTitle());
                favSong2Loaded = true;
            }
        });

        song3Select.setOnMouseClicked(event -> {
            if(!favSong3Loaded) {
                for (int b = 0; b < songService.getAll().size(); b++)
                    song3Select.getItems().add(songService.getAll().get(b).getSongTitle());
                favSong3Loaded = true;
            }
        });
        confirmBtn.setOnAction(event -> {
            favPlaylistLbl.setText(albumSelect.getValue().toString());
            favSong1Lbl.setText(song1Select.getValue().toString());
            favSong2Lbl.setText(song2Select.getValue().toString());
            favSong3Lbl.setText(song3Select.getValue().toString());
            editPane.setVisible(false);
            editPane.setDisable(true);
            isEditOpen = false;
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

    private void setPlaylistView(List<Playlist>playlistData,ArrayList<StackPane> playlistStack,ArrayList<Rectangle> boxes,ArrayList<AnchorPane> anchorPane,
                                 ArrayList<StackPane> songStack,ArrayList<Rectangle> rectangles,ArrayList<AnchorPane> anchors,SongInPlaylistService songInPlaylistService,
                                 SongService songService){
        playList.getChildren().clear();
        playlistStack.clear();
        boxes.clear();
        anchorPane.clear();
        for (int i = 0; i < playlistData.size(); i++) {
            playlistStack.add(new StackPane());
            boxes.add(new Rectangle());
            boxes.get(i).setWidth(200);
            boxes.get(i).setHeight(50);
            boxes.get(i).setLayoutX(0);
            boxes.get(i).setLayoutY(0);
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
                        songList.getChildren().clear();
                    }
                }
                for (Node node : anchorPane.get(nextPlaylist).getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setTextFill(Color.web("#f7620e"));
                        if(((Label) node).getText().compareToIgnoreCase("Songs")==0) {
                            setSongsView(songService.getAll(), songStack, rectangles, anchors);
                            selectedPlaylistID=finalX;
                        }else {
                            setSongsView(songInPlaylistService.getSongsInPlaylist(nextPlaylist), songStack, rectangles, anchors);
                            selectedPlaylistID=finalX;
                        }
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
                int z=0,c=0;
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
                        z=1;
                    }
                }
                if(z==1){
                    selectedSongID=finalI;
                    currentSong=initializeSong(DB);
                    UserWithSongService userWithSongService = new UserWithSongService(DB);

                    for(int h=0;h<userWithSongService.getAll(user.getId()).size();h++) {
                        System.out.println(userWithSongService.getAll(user.getId()).get(h).getSongID());
                        System.out.println(selectedSongID);
                        if (userWithSongService.getAll(user.getId()).get(h).getSongID()==selectedSongID) {
                            c=1;
                        }
                    }
                    if(c==0){
                        UserWithSong userWithSong = new UserWithSong();
                        userWithSong.setUserwithsongID(userWithSongService.getComplete().size()+1);
                        userWithSong.setSongID(selectedSongID+1);
                        userWithSong.setUserID(user.getId());
                        userWithSong.setPlaycount(1);
                        userWithSongService.add(userWithSong);
                    }
                }

                editSong(songData,finalI);
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

    private MedPlayer initializeSong(Database DB){
        SongService songService = new SongService(DB);
        System.out.println("/audio/" + songService.getAll().get(selectedSongID).getSongTitle() + ".mp3");
        currentSong=new MedPlayer(songService.getAll().get(selectedSongID).getFilename());

        return currentSong;
    }

    private void editSong(List<Song> songData,int i){

        SongService songService = new SongService(DB);

        editTitle.setText(songData.get(i).getSongTitle());
        editArtist.setText(songData.get(i).getArtist());
        editAlbum.setText(songData.get(i).getAlbum());
        editGenre.setText(songData.get(i).getGenre());
        editDate.setText(songData.get(i).getYear()+"");
        editTime.setText(songData.get(i).getDuration()+"");

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
            songService.updateSong(i,editTitle.getText(),editGenre.getText(),editAlbum.getText(),editArtist.getText(),Integer.parseInt(editDate.getText()),Float.parseFloat(editTime.getText()));

            songSettingPane.setVisible(false);
            songSettingPane.setDisable(true);
            isSettingOpen=false;
        });
    }
}

