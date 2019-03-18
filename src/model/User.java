package model;

public class User {
    public static final String TABLE_NAME = "user";

    private int id;
    private String username;
    private String password;
    private int favoritesong1;
    private int favoritesong2;
    private int favoritesong3;
    private int favorteplaylist;


    public static final String COL_ID = "userID";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_FAVORITESONG1 = "favoritesong1";
    public static final String COL_FAVORITESONG2 = "favoritesong2";
    public static final String COL_FAVORITESONG3 = "favoritesong3";
    public static final String COL_FAVORITEPLAYLIST = "favoriteplaylist";

    public int getFavoritesong1() {
        return favoritesong1;
    }

    public void setFavoritesong1(int favoritesong1) {
        this.favoritesong1 = favoritesong1;
    }

    public int getFavoritesong2() {
        return favoritesong2;
    }

    public void setFavoritesong2(int favoritesong2) {
        this.favoritesong2 = favoritesong2;
    }

    public int getFavoritesong3() {
        return favoritesong3;
    }

    public void setFavoritesong3(int favoritesong3) {
        this.favoritesong3 = favoritesong3;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorteplaylist() {
        return favorteplaylist;
    }

    public void setFavorteplaylist(int favorteplaylist) {
        this.favorteplaylist = favorteplaylist;
    }
}
