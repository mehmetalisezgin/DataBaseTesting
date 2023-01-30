Feature: US03

  Scenario Outline: TC01: Verify the lesson data in the schhol table
    Given Connect to the data base
    Then  List the "lesson" of the column
    And   Verify the lesson data "<lesson>"
    Then  Close the connection of the database
    Examples:
      | lesson  |
      | Maths   |
      | English |
      | Turkish |

  Scenario: TC02: Verify the number of row of the school table
    Given Connect to the data base
    And  Verify the number of row in the "school" table
    Then  Close the connection of the database


  Scenario: TC03: Verify the number of row of the salesman table
    Given Connect to the data base
    And  Verify the number of row in the "salesman" table
    Then  Close the connection of the database

  Scenario: TC04: Verify the nameOfTeacher
    Given Connect to the data base
    And   verify the Sevim teacher is visible
    Then  Close the connection of the database