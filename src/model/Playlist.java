package model;

public class Playlist {

    public static final String TABLE_NAME = "playlist";

    private int playlistID;
    private String playlistName;
    private int userID;

    public static final String COL_PLAYLISTID = "playlistID";
    public static final String COL_PLAYLISTNAME = "playlistName";
    public static final String COL_USERID = "userID";
    

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
