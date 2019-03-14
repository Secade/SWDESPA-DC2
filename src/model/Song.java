package model;

public class Song {
    public static final String TABLE_NAME = "song";

    private int songID;
    private  String songTitle;
    private String genre;
    private String album;
    private String artist;
    private int year;
    private float duration;
    private String filename;

    public static final String COL_SONGID = "songID";
    public static final String COL_SONGTITLE = "songTitle";
    public static final String COL_GENRE = "genre";
    public static final String COL_ALBUM = "album";
    public static final String COL_ARTIST = "artist";
    public static final String COL_YEAR = "Year";
    public static final String COL_DURATION = "Duration";
    public static final String COL_FILENAME = "Filename";

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
