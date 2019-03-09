package model;

public class UserWithSong {
    public  static final  String TABLE_NAME = "userwithsong";

    private int userID;
    private int songID;

    public static final String COL_USERID = "userID";
    public static final String COL_SONGID = "songID";

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}