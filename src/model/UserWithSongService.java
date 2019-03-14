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

        String query = "INSERT INTO " + UserWithSong.TABLE_NAME + " VALUE (?, ?, ?)";
        Connection connection = db.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,u.getUserID());
            statement.setInt(2,u.getSongID());
            statement.setInt(3,u.getPlaycount());

            boolean added = statement.execute();
            return added;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<UserWithSong> getAll(int id){
        //GET CONTACTS
        Connection connection = db.getConnection();
        List<UserWithSong> userwithsongs = new ArrayList<>();

        String query = "SELECT * FROM " + UserWithSong.TABLE_NAME
                +" WHERE " +UserWithSong.COL_USERID+ "=" + id ;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                UserWithSong uws = new UserWithSong();
                uws.setUserID(rs.getInt(UserWithSong.COL_USERID));
                uws.setSongID(rs.getInt(UserWithSong.COL_SONGID));
                uws.setPlaycount(rs.getInt(UserWithSong.COL_PLAYCOUNT));
                userwithsongs.add(uws);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return userwithsongs;
    }
    public boolean updatePlayCount(int songID,int id){
        String query = "UPDATE " + UserWithSong.TABLE_NAME + " SET "
                + UserWithSong.COL_PLAYCOUNT +"="+ UserWithSong.COL_PLAYCOUNT+"+1"
                +" WHERE " + UserWithSong.COL_USERID + "=" + id +" AND "+ UserWithSong.COL_SONGID +"=" + songID  ;

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

    public Song getMostPlayed(int id){
        Connection connection = db.getConnection();
        Song s = new Song();

        String query = "SELECT " +Song.TABLE_NAME + "." + Song.COL_SONGID +","+ Song.TABLE_NAME + "." + Song.COL_SONGTITLE +","+Song.TABLE_NAME + "." + Song.COL_GENRE+","+ Song.TABLE_NAME + "." + Song.COL_ALBUM +","+ Song.TABLE_NAME + "." + Song.COL_ARTIST+","+ Song.TABLE_NAME + "." + Song.COL_YEAR+","+ Song.TABLE_NAME + "." + Song.COL_DURATION+","+ Song.TABLE_NAME + "." + Song.COL_FILENAME+
                " FROM " +Song.TABLE_NAME + " RIGHT JOIN "+ UserWithSong.TABLE_NAME +
                " ON " +Song.TABLE_NAME+"."+Song.COL_SONGID +"="+ UserWithSong.TABLE_NAME+"."+UserWithSong.COL_SONGID +
                " WHERE " +UserWithSong.TABLE_NAME+"."+UserWithSong.COL_USERID +"="+ id +" AND " +UserWithSong.TABLE_NAME+"."+UserWithSong.COL_PLAYCOUNT+"="+
                "(SELECT MAX(" +UserWithSong.TABLE_NAME+"."+UserWithSong.COL_PLAYCOUNT+ ") FROM " +UserWithSong.TABLE_NAME + " WHERE "+ UserWithSong.COL_USERID +"="+ id+")";


        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                s.setSongID(rs.getInt(Song.COL_SONGID));
                s.setSongTitle(rs.getString(Song.COL_SONGTITLE));
                s.setGenre(rs.getString(Song.COL_GENRE));
                s.setAlbum(rs.getString(Song.COL_ALBUM));
                s.setAlbum(rs.getString(Song.COL_ARTIST));
                s.setYear(rs.getInt(Song.COL_YEAR));
                s.setDuration(rs.getFloat(Song.COL_DURATION));
                s.setFilename(rs.getString(Song.COL_FILENAME));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

}

