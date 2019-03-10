package model;

public class User {
    public static final String TABLE_NAME = "user";

    private int id;
    private String username;
    private String password;
    private int favoritesong1;
    private int favoritesong2;
    private int favoritesong3;
    private int favoriteplaylist;


    public static final String COL_ID = "userID";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_FAVESONG1 = "favoritesong1";
    public static final String COL_FAVESONG2 = "favoritesong2";
    public static final String COL_FAVESONG3 = "favoritesong3";
    public static final String COL_FAVEPLAYLIST = "favoriteplaylist";

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

    public int getFavoriteplaylist() {
        return favoriteplaylist;
    }

    public void setFavoriteplaylist(int favoriteplaylist) {
        this.favoriteplaylist = favoriteplaylist;
    }
}
