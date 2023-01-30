Feature: US01

  Scenario Outline: TC01: Verify the lifeTime data of the birds table
    Given Connect to the data base
    Then  List the "lifeTime"
    And   Verify the "<lifeTime>"
    Then  Close the connection of the database
    Examples:
      | lifeTime |
      | 70       |
      | 20       |
      | 30       |
      | 120      |

  Scenario Outline: TC02: Verify the name data of the birds table
    Given Connect to the data base
    Then  List the "name"
    And   Verify the "<name>"
    Then  Close the connection of the database
    Examples:
      | name   |
      | eagle  |
      | parrot |
      | owl    |
      | crow   |

  Scenario Outline: TC03 : Verify the name of columns
    Given Connect to the data base
    Then  Execute the query
    And   Verify the name of "<column>"
    Then  Close the connection of the database
    Examples:
      | column      |
      | id          |
      | name        |
      | lifeTime    |
      | wing_length |


  Scenario Outline: TC04: Verify the wing_length data of the birds table
    Given Connect to the data base
    Then  Execute the query for the birds table
    And   Verify the data "<wing_length>"
    Then  Close the connection of the database
    Examples:
      | wing_length |
      | 3           |
      | 1           |
      | 2           |
      | 1           |




