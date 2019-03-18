package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongInPlaylistService {
    private Database db;
    private Connection connection;

    public SongInPlaylistService(Database db){
        this.db=db;
        this.connection=db.getConnection();
    }

    public boolean add(SongInPlaylist s){
        // ADD CONTACT
        String query = "INSERT INTO " + SongInPlaylist.TABLE_NAME + " VALUE (?, ?, ?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,s.getSongInPlaylistID());
            statement.setInt(2,s.getPlaylistID());
            statement.setInt(3,s.getSongID());
            statement.setInt(4,s.getUserID());

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<SongInPlaylist> getAll(){
        //GET CONTACTS
        List<SongInPlaylist> songinplaylists = new ArrayList<>();

        String query = "SELECT * FROM " + SongInPlaylist.TABLE_NAME;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                SongInPlaylist sip = new SongInPlaylist();
                sip.setSongID(rs.getInt(SongInPlaylist.COL_SONGID));
                sip.setPlaylistID(rs.getInt(SongInPlaylist.COL_PLAYLISTID));
                songinplaylists.add(sip);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songinplaylists;
    }

    public List<Song> getSongsInPlaylist(int playlistid){
        //GET CONTACTS
        List<Song> songinplaylists = new ArrayList<>();

        String query = "SELECT "+ Song.TABLE_NAME +"." +Song.COL_SONGID+ ","+ Song.TABLE_NAME +"." + Song.COL_SONGTITLE+ ","+ Song.TABLE_NAME +"." +Song.COL_GENRE+ "," + Song.TABLE_NAME +"."+ Song.COL_ALBUM +","+ Song.TABLE_NAME +"."
                + Song.COL_ARTIST +","+ Song.TABLE_NAME +"." + Song.COL_YEAR +","+ Song.TABLE_NAME +"." + Song.COL_DURATION + "," + Song.TABLE_NAME +"."+ Song.COL_FILENAME
                +" FROM " + SongInPlaylist.TABLE_NAME +" INNER JOIN " + Song.TABLE_NAME
                + " ON " + Song.TABLE_NAME +"."+Song.COL_SONGID +"=" + SongInPlaylist.TABLE_NAME+"."+SongInPlaylist.COL_SONGID
                + " WHERE "+SongInPlaylist.TABLE_NAME+"."+ SongInPlaylist.COL_PLAYLISTID + "=" + playlistid;

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
                songinplaylists.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return songinplaylists;
    }
  
    public List<Song> sort(int playlistID, String sortType) {
        List<Song> songs = new ArrayList<>();

        String query = "SELECT " +Song.TABLE_NAME + "." + Song.COL_SONGID +","+ Song.TABLE_NAME + "." + Song.COL_SONGTITLE +","+Song.TABLE_NAME + "." + Song.COL_GENRE+","+ Song.TABLE_NAME + "." + Song.COL_ALBUM +","+ Song.TABLE_NAME + "." + Song.COL_ARTIST+","+ Song.TABLE_NAME + "." + Song.COL_YEAR+","+ Song.TABLE_NAME + "." + Song.COL_DURATION+","+ Song.TABLE_NAME + "." + Song.COL_FILENAME+
                " FROM " + Song.TABLE_NAME +" right join " +SongInPlaylist.TABLE_NAME +
                " ON " +Song.TABLE_NAME + "."+Song.COL_SONGID +"=" +SongInPlaylist.TABLE_NAME+"."+ SongInPlaylist.COL_SONGID +
                " WHERE " +SongInPlaylist.TABLE_NAME+"."+SongInPlaylist.COL_PLAYLISTID +"="+ playlistID +
                " ORDER BY " + sortType;


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
