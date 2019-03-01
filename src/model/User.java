package model;

public class User {
    public static final String TABLE_NAME = "users";

    private int id;
    private String username;
    private String password;

  //  public static final String COL_ID = "userID";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

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
}
