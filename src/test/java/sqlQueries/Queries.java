package sqlQueries;

import java.sql.Statement;

public class Queries {

    public static String birdsTableFields(String field){
        return "SELECT "+field+" FROM birds" ;
    }

    public static String birdsTableQuery(){
        return "Select * From birds" ;
    }

    public static String numberOfRowsTable(String table){
        return "SELECT * FROM "+table ;
    }

}
