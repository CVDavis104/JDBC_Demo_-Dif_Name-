import java.sql.Connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    /*This is a singleton - meaning when we call this
    instance, we are always referring to the same instance
    of this 'ConnectionManager' class */
    public static ConnectionManager connectionManager;

    //This is our connection instance
    public static Connection connection;

    //This is a private constructor method
    //Private methods have 'get' and 'set' methods
    private ConnectionManager(){

    }

    //This is a getter method
    private ConnectionManager getConnectionManager(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();}
            return connectionManager;
    /*ConnectionManager method scope ending */}

    public static Connection getConnection(){
        if(connection == null)
            connection = connect();
            return connection;}

    public static Connection connect(){

     try{
        /*This creates a new properties list
         with no values*/
        Properties props = new Properties();
        /*Here we are using the file reader to read the
         jdbc.properties file */
        FileReader fileReader = new FileReader("src/main/resources/jdbc.properties");
        /*By loading the contents of the file info into the properties list
         we can now access the values at the keys that we have set on the
         properties list */
        props.load(fileReader);

     /*The database URL is an address pointing to the database to be
     used -- also known as the database string. The format of this URL
     varies between database vendors or DBMS. For postgres, the URL we
      are creating consists of the following:
      */

        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:postgresql://");
        sb.append(props.get("hostname"));
        sb.append(":");
        sb.append(props.get("port"));
        sb.append("/");
        sb.append(props.get("database"));

        /*This returns the joined string to us
        that we built with our string builder.
         In order for us to use the string we
         have created we have to call the '.toString'
         method on the sb (StringBuilder) */
        String connectionURL = sb.toString();

        String username = String.valueOf(props.get("user"));
        String password = String.valueOf(props.get("password"));

        connection = DriverManager.getConnection(connectionURL,username,password);

        System.out.println(connectionURL.toString());
     }

        catch(IOException | SQLException e){
         System.out.println(e.getMessage());
        }
        return connection;
    /*connect method scope ending */}

/*ConnectionManager class scope ending */ }
