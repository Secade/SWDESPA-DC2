package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.MainController.loggedin;

public class UserService {
    private Database db;

    public UserService(Database db){
        this.db=db;
    }

    public boolean add(User c){
        // ADD CONTACT

        String query = "INSERT INTO " + User.TABLE_NAME + " VALUE (?, ?, ?, ?, ?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,c.getId());
            statement.setString(2,c.getUsername());
            statement.setString(3,c.getPassword());
            statement.setInt(4, -1);
            statement.setInt(5, -1);
            statement.setInt(6, -1);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List <User> getAll(){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<User> contacts = new ArrayList<>();

        String query = "SELECT * FROM " + User.TABLE_NAME;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User c = new User();
                c.setId(rs.getInt(User.COL_ID));
                c.setUsername(rs.getString(User.COL_USERNAME));
                c.setPassword(rs.getString(User.COL_PASSWORD));
                c.setFavoritesong1(rs.getInt(User.COL_FAVESONG1));
                c.setFavoritesong2(rs.getInt(User.COL_FAVESONG2));
                c.setFavoritesong3(rs.getInt(User.COL_FAVESONG3));
                contacts.add(c);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return contacts;
    }

    public List <User> getPasswordList(){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<User> contacts = new ArrayList<>();

        String query = "SELECT password FROM " + User.TABLE_NAME;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User c = new User();
                c.setPassword(rs.getString(User.COL_PASSWORD));
                contacts.add(c);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return contacts;
    }

    public List <User> getUserIdList(){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<User> contacts = new ArrayList<>();

        String query = "SELECT userID, username, password FROM " + User.TABLE_NAME;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User c = new User();
                c.setId(rs.getInt(User.COL_ID));
                c.setUsername(rs.getString(User.COL_USERNAME));
                contacts.add(c);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return contacts;
    }
    public boolean updateFaveSong1(int song1ID){
        // ADD CONTACT

        String query = "UPDATE " + User.TABLE_NAME + " SET " + User.COL_FAVESONG1+ "=" + song1ID
                        + "WHERE " + User.COL_ID + "=" + loggedin.getId() ;
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFaveSong2(int song1ID){
        // ADD CONTACT

        String query = "UPDATE " + User.TABLE_NAME + " SET 1111111111111111111" + User.COL_FAVESONG2+ "=" + song1ID
                + "WHERE " + User.COL_ID + "=" + loggedin.getId() ;
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFaveSong3(int song1ID){
        // ADD CONTACT

        String query = "UPDATE " + User.TABLE_NAME + " SET " + User.COL_FAVESONG3+ "=" + song1ID
                + "WHERE " + User.COL_ID + "=" + loggedin.getId() ;
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFavePlaylist(int playlistID){
        // ADD CONTACT

        String query = "UPDATE " + User.TABLE_NAME + " SET " + User.COL_FAVEPLAYLIST+ "=" + playlistID
                + "WHERE " + User.COL_ID + "=" + loggedin.getId() ;
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }



}
