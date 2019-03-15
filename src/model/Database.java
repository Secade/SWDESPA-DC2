package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class Database {

    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "compos mentis0220";
    private final static String DATABASE = "swdespa_dc2";

    public Connection getConnection(){
        try{
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(
                    URL +
                            DATABASE + "?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + TimeZone.getDefault().getID(),
                    USERNAME,
                    PASSWORD);

            System.out.println("[MYSQL] Connection Successful!");
            return connection;
        } catch(SQLException e){
            System.out.println("[MYSQL] Was not able to connect");
            e.printStackTrace();
            return null;
        } catch(ClassNotFoundException e){
            System.out.println("[MYSQL] Was not able to connect");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Database db = new Database();
        //UserInformationService service = new UserInformationService(db);
        //List<UserInformation> Information = service.getAll();

/*
        UserInformation c = new UserInformation();
        c.setId(1);
        c.setName("Jan Bertel Ngo");
        c.setUserInformation_detail("jan_bertel_ngo@dlsu.edu.ph");

        UserInformation d = new UserInformation();
        d.setId(2);
        d.setName("Jaime Pastor");
        d.setUserInformation_detail("jaime_pastor@dlsu.edu.ph");

        service.add(c);
        service.add(d);

        for (int i = 0; i < Information.size(); i++){
            System.out.println(Information.get(i).getName()+ " " + Information.get(i).getUserInformation_detail());
        }*/
    }
}
