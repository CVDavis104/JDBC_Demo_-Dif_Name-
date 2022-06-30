import java.sql.Connection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

        Connection connection = ConnectionManager.connect();

        try{
            System.out.println(connection.getClientInfo().isEmpty());}
        catch(SQLException e){System.out.println(e.getMessage());}

    }
}
