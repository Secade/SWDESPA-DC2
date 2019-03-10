package model;

public class Song {
    private int songID;
    private  String songTitle;
    private int genreID;
    private int albumID;
    private int year;
    private float duration;
    private String filename;

    public static final String TABLE_NAME = "song";
    public static final String COL_SONGID = "songID";
    public static final String COL_SONGTITLE = "songTitle";
    public static final String COL_GENREID = "genreID";
    public static final String COL_ALBUMID = "albumID";
    public static final String COL_YEAR = "Year";
    public static final String COL_DURATION = "Duration";
    public static final String COL_FILENAME = "Filename";

    public Song(){

    }

    public Song(String st){
        songTitle = st;
    }

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

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
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
}
