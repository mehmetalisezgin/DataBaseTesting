package dbStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.json.JsonOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sqlQueries.Queries.birdsTableFields;
import static sqlQueries.Queries.birdsTableQuery;
import static utilities.DBUtils.*;

public class BirdsTableStepDefinition {

    List<Object> lifeTimeData = new ArrayList<>() ;
    List<String> columnName = new ArrayList<>() ;
    List<Object> columnDataBirdTable = new ArrayList<>() ;






    @Given("Connect to the data base")
    public void connect_to_the_data_base() {


        createConnection();
    }
    @Then("List the {string}")
    public void list_the(String field) {
     lifeTimeData= getColumnData(birdsTableFields(field),field);
        System.out.println(lifeTimeData);
        // "SELECT lifeTime FROM birds"

    }
    @Then("Verify the {string}")
    public void verify_the(String field) {
        Assert.assertTrue(lifeTimeData.contains(field));
    }
    @Then("Close the connection of the database")
    public void close_the_connection_of_the_database() {

        closeConnection();
    }
    @Then("Execute the query")
    public void execute_the_query() {
        columnName = getColumnNames(birdsTableQuery()) ;
        System.out.println(columnName);
    }
    @Then("Verify the name of {string}")
    public void verify_the_name_of(String columnNames) {
       Assert.assertTrue(columnName.contains(columnNames));
    }

    @Then("Execute the query for the birds table")
    public void execute_the_query_for_the_birds_table() {
        columnDataBirdTable= Collections.singletonList(getQueryResultList(birdsTableFields("wing_length")));
        System.out.println(getQueryResultList(birdsTableFields("wing_length")));
    }

    @Then("Verify the data {string}")
    public void verify_the_data(String name) {
        Assert.assertTrue(columnDataBirdTable.toString().contains(name));
    }


}
