package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private Database db;

    public PlaylistService(Database db){
        this.db=db;
    }

    public boolean add(Playlist p){
        // ADD CONTACT

        String query = "INSERT INTO " + Playlist.TABLE_NAME + " VALUE (?, ?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,p.getPlaylistID());
            statement.setString(2,p.getPlaylistName());
            statement.setInt(3,p.getUserID());

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Playlist> getAll(int userID){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM " + Playlist.TABLE_NAME
                +" WHERE " +Playlist.COL_USERID +"="+ userID;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Playlist pl = new Playlist();
                pl.setPlaylistID(rs.getInt(Playlist.COL_PLAYLISTID));
                pl.setPlaylistName(rs.getString(Playlist.COL_PLAYLISTNAME));
                pl.setUserID(rs.getInt(Playlist.COL_USERID));
                playlists.add(pl);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return playlists;
    }

}