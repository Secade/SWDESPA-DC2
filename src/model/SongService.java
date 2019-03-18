package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongService {
    private Database db;
    private Connection connection;

    public SongService(Database db){
        this.db = db;
        this.connection=db.getConnection();
    }

    public boolean add(Song s){
        String query ="INSERT INTO " + Song.TABLE_NAME + " VALUE (?, ?, ?, ?, ?, ?, ?, ?)";

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
    public List<Song> sort(String sortType) {
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM " + Song.TABLE_NAME + " ORDER BY " +sortType;

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


    public boolean updateSong(int songID, String songTitle, String genre, String album, String artist, int year, float duration){
        String query = "UPDATE " + Song.TABLE_NAME + " SET "
                + Song.COL_SONGTITLE + "='" +songTitle + "',"
                + Song.COL_GENRE + "='" + genre + "',"
                + Song.COL_ALBUM + "='" + album + "',"
                + Song.COL_ARTIST + "='" + artist + "',"
                + Song.COL_YEAR + "='" + year + "',"
                + Song.COL_DURATION + "='" + duration
                + "' WHERE " + Song.COL_SONGID + "=" + songID+"+1" ;

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Song> search(String search){
        List<Song> songs = new ArrayList<>();

        String query = "SELECT *  FROM " +Song.TABLE_NAME +
                " WHERE " + Song.COL_SONGTITLE+ " LIKE '%" +search+ "%' OR " +Song.COL_GENRE+ " LIKE '%" +search+ "%' OR " +Song.COL_ALBUM+ " LIKE '%" +search+ "%' OR " +Song.COL_ARTIST+ " LIKE '%" +search+ "%' OR " +Song.COL_YEAR+ " LIKE '%" +search+ "%' OR " +Song.COL_DURATION+ " LIKE '%" +search+ "%' OR " +Song.COL_FILENAME+ " LIKE '%" +search+ "%'";
        ;

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

}
