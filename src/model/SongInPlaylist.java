package model;

public class SongInPlaylist {
    public static final String TABLE_NAME = "songinplaylist";

    private int songInPlaylistID;
    private int songID;
    private int playlistID;
    private int userID;

    public static final String COL_SONGINPLAYLIST = "songinplaylistID";
    public static final String COL_SONGID = "songID";
    public static final String COL_PLAYLISTID = "playlistID";
    public static final String COL_USERID = "userID";

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getSongInPlaylistID() {
        return songInPlaylistID;
    }

    public void setSongInPlaylistID(int songInPlaylistID) {
        this.songInPlaylistID = songInPlaylistID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
