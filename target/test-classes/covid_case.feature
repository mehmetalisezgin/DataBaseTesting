Feature: US01 : Creating covid_case table with four fields (id,hospital,city,numberOfCases)


  Scenario: TC01 : numberOfCases data should be appering
    Given Users should connect to the database with their password,username
    Then  User will bring data "NumberOfCases" from the "covid_case" table
    And   User read the data in the "NumberOfCases"

