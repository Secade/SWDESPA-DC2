package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserWithSongService {
    private Database db;

    public UserWithSongService(Database db){
        this.db=db;
    }

    public boolean add(UserWithSong u){
        // ADD CONTACT

        String query = "INSERT INTO " + UserWithSong.TABLE_NAME + " VALUE (?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,u.getUserID());
            statement.setInt(2,u.getSongID());

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<UserWithSong> getAll(){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<UserWithSong> userwithsongs = new ArrayList<>();

        String query = "SELECT * FROM " + UserWithSong.TABLE_NAME ;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                UserWithSong uws = new UserWithSong();
                uws.setUserID(rs.getInt(UserWithSong.COL_USERID));
                uws.setSongID(rs.getInt(UserWithSong.COL_SONGID));
                userwithsongs.add(uws);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return userwithsongs;
    }

}
