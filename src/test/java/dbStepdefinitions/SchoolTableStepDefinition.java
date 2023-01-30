package dbStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sqlQueries.Queries.numberOfRowsTable;
import static utilities.DBUtils.*;

public class SchoolTableStepDefinition {

    List<Object> schoolTableLesson = new ArrayList<>();



    @Then("List the {string} of the column")
    public void list_the_of_the_column(String lesson) {
        schoolTableLesson= Collections.singletonList(getQueryResultList("Select " + lesson + " from school"));
        System.out.println(getQueryResultList("Select "+lesson+" from school"));
    }

    @Then("Verify the lesson data {string}")
    public void verify_the_lesson_data(String lesson) {
        Assert.assertTrue(schoolTableLesson.toString().contains(lesson));
    }


    @Given("Verify the number of row in the {string} table")
    public void verify_the_number_of_row_in_the_table(String table) {
        getQueryResultList(numberOfRowsTable(table));
        System.out.println(getRowCount());
        Assert.assertTrue(getRowCount()==12);
    }
    @Given("verify the Sevim teacher is visible")
    public void verify_the_sevim_teacher_is_visible() {

    }


}