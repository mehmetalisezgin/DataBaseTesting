package utilities;

import com.fasterxml.jackson.databind.util.ObjectBuffer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static String url = "jdbc:mysql://localhost:3306/sys?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "ervanaz2012";

    // WE can call createConnection method whenever we want to connect to the database
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("The Connection Data Base is Fail : " + e.getMessage());
        }
    }

    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.out.println("The Statement is Fail " + e.getMessage());
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("The Query is Fail " + e.getMessage());
        }
    }

    public static List<Object> getColumnData(String field, String column) {
        executeQuery(field);
        List<Object> rowList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            System.out.println("Excecution of the Query is Fail " + e.getMessage());
        }
        return rowList;
    }


    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("The Close Method is Fail " + e.getMessage());
        }
    }


    // the name of columns returned in a list
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("The Columns Couldnt Find " + e.getMessage());
        }
        return columns;
    }

    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Data is not found " + e.getMessage());
        }
        return rowList;
    }

    // it return us number of row
    public static int getRowCount(){
        int rowCount = 0;
        try{
            resultSet.last();
            rowCount = resultSet.getRow();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return rowCount ;
    }

    /**
     * @return returns query result in a list of maps where the list represents
     *         collection of rows and a map represents represent a single row with
     *         key being the column name
     */

    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return statement;
    }

    //getResultset method Resultset object i olusturmak icin.
    public static ResultSet getResultset() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    public static synchronized void update(String query) {
        try{
            Statement st = connection.createStatement();
            int ok = st.executeUpdate(query);
            if (ok == -1) {
                throw new SQLException("DB problem with query: " + query);
            }
            st.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
