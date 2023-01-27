package dbStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CovidCasesStepDefinitions {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static String url = "jdbc:mysql://localhost:3306/sys?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "ervanaz2012";

    @Given("Users should connect to the database with their password,username")
    public void users_should_connect_to_the_database_with_their_password_username() throws SQLException {
        // connect to the database
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


    @Then("User will bring data {string} from the {string} table")
    public void user_will_bring_data_from_the_table(String field, String table) throws SQLException {
        // we can bring the data with the queries
        // SELECT NumberOfCases FROM covid_case
        String readQuery = "SELECT " + field + " FROM " + table;
        resultSet = statement.executeQuery(readQuery);
    }


    @Then("User read the data in the {string}")
    public void user_read_the_data_in_the(String field) throws SQLException {
        // resultSet works with iterator logic
        // if we would like to read the information with the resulSet, we must first send the iterator to where you want it.
        resultSet.first();
        // Once the iterator has moved to the desired location
        System.out.println(resultSet.getString(field));
        resultSet.next();// for the next data
        System.out.println(resultSet.getString(field));
        System.out.println(resultSet.next());// True

        resultSet.absolute(0);
        // to print all data
        System.out.println("********** All Data **************");
        while(resultSet.next()){
            System.out.println(resultSet.getString(field));
        }
        resultSet.last();
        // Lets find the number of Data in the NumberOfCases column
        System.out.println(resultSet.getRow());
        // to assert this data we should put in the list

        resultSet.absolute(0);
        List<Object> NumberOfCases = new ArrayList<>();
        while(resultSet.next()){
            NumberOfCases.add(resultSet.getInt(field));
        }
        System.out.println(NumberOfCases);
        Assert.assertTrue(NumberOfCases.contains(9854));
    }





}
