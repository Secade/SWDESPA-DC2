package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Database db;

    public UserService(Database db){
        this.db=db;
    }

    public boolean add(User c){
        // ADD CONTACT

        String query = "INSERT INTO " + User.TABLE_NAME + " VALUE (?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
           // statement.setInt(1,c.getId());
            statement.setString(1,c.getUsername());
            statement.setString(2,c.getPassword());

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
               // c.setId(rs.getInt(User.COL_ID));
                c.setUsername(rs.getString(User.COL_USERNAME));
                c.setPassword(rs.getString(User.COL_PASSWORD));
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

        String query = "SELECT username, password FROM " + User.TABLE_NAME;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User c = new User();
               // c.setId(rs.getInt(User.COL_ID));
                c.setUsername(rs.getString(User.COL_USERNAME));
                contacts.add(c);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return contacts;
    }



}
