package model;

public class UserWithSong {
    public  static final  String TABLE_NAME = "userwithsong";

    private int userID;
    private int songID;
    private int playcount;

    public static final String COL_USERID = "userID";
    public static final String COL_SONGID = "songID";
    public static  final String COL_PLAYCOUNT = "playCount";

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

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }
}
