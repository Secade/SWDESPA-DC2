package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongService {
    private Database db;

    public SongService(Database db){
        this.db = db;
    }

    public boolean add(Song s){
        String query ="INSERT INTO " + Song.TABLE_NAME + " VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,s.getSongID());
            statement.setString(2,s.getSongTitle());
            statement.setString(3,s.getGenre());
            statement.setString(4,s.getAlbum());
            statement.setString(5,s.getArtist());
            statement.setInt(6,s.getYear());
            statement.setFloat(7,s.getDuration());
            statement.setString(8,s.getFilename());


            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Song> getAll(){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }
    public List<Song> sortbyAlbum(){
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY album";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }

    public List<Song> sortbyArtist(){
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY artist";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }

    public List<Song> sortbyGenre(){
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY genre";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }

    public List<Song> sortbyYear(){
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER by year";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }

    public List<Song> sortbyDuration() {
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY duration";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public List<Song> sortbyTitle(){
        Connection connection = db.getConnection();
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY songTitle";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Song s = new Song();
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setArtist(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
                songs.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songs;
    }

}
