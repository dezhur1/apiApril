Feature: Trello API test feature

  Scenario: Add a new list to the test board after changing title
    Given The board exists and contains the correct information
    When I change the board title to "New Title"
    And I check that the board name ws updated to "New Title"
    Then I add a list with a name "Demo list title" to the board