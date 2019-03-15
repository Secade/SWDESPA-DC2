package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongInPlaylistService {
    private Database db;

    public SongInPlaylistService(Database db){
        this.db=db;
    }

    public boolean add(SongInPlaylist s){
        // ADD CONTACT

        String query = "INSERT INTO " + SongInPlaylist.TABLE_NAME + " VALUE (?, ?, ?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,s.getSongInPlaylistID());
            statement.setInt(2,s.getSongID());
            statement.setInt(3,s.getPlaylistID());
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
        Connection connection = db.getConnection();
        List<SongInPlaylist> songinplaylists = new ArrayList<>();

        String query = "SELECT * FROM " + SongInPlaylist.TABLE_NAME;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                SongInPlaylist sip = new SongInPlaylist();
                sip.setSongInPlaylistID(rs.getInt(SongInPlaylist.COL_SONGINPLAYLIST));
                sip.setSongID(rs.getInt(SongInPlaylist.COL_SONGID));
                sip.setPlaylistID(rs.getInt(SongInPlaylist.COL_PLAYLISTID));
                sip.setUserID(rs.getInt(SongInPlaylist.COL_USERID));
                songinplaylists.add(sip);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return songinplaylists;
    }

    public List<Song> getSongsInPlaylist(int playlistid){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<Song> songinplaylists = new ArrayList<>();

        String query = "SELECT " +Song.TABLE_NAME +"."+Song.COL_SONGID+ " , " +Song.TABLE_NAME +"."+ Song.COL_SONGTITLE+ " , " +Song.TABLE_NAME +"."+Song.COL_GENRE+ " , " +Song.TABLE_NAME +"."+ Song.COL_ALBUM +" , "
                        +Song.TABLE_NAME +"."+ Song.COL_ARTIST +" , " + Song.TABLE_NAME +"."+Song.COL_YEAR +" , " +Song.TABLE_NAME +"."+ Song.COL_DURATION + " , " +Song.TABLE_NAME +"."+ Song.COL_FILENAME
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
                s.setAlbum(rs.getString(Song.COL_ARTIST));
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
}
