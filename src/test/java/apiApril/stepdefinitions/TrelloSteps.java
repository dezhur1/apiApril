package apiApril.stepdefinitions;

import apiApril.domain.Board;
import apiApril.domain.List;
import apiApril.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static apiApril.clients.TrelloClient.getBoardInfo;
import static apiApril.clients.TrelloClient.updateBoardInfo;
import static apiApril.clients.TrelloClient.createList;
import static apiApril.constants.ProjectConstants.BOARD_ID;
import static apiApril.constants.ProjectConstants.BOARD_NAME;

public class TrelloSteps {

    @Given("The board exists and contains the correct information")
    public void getBoardDataAndCheckInfo(){
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);

        Assertions.assertThat(board.getId())
                .as("We assert that the board ID is correct")
                .isEqualTo(BOARD_ID);

        Assertions.assertThat(board.getName())
                .as("We assert that the board Name is correct")
                .isEqualTo(BOARD_NAME);
    }

    @When("I change the board title to {string}")
    public void iChangeTheBoardTitleTo(String title) {
        Response response = updateBoardInfo(title, BOARD_ID);
        Board board = response.as(Board.class);
        TestCaseContext.setBoard(board);
    }

    @And("I check that the board name ws updated to {string}")
    public void iCheckThatTheBoardNameWsUpdatedTo(String title) {
        Assertions.assertThat(TestCaseContext.getBoard().getName())
                .as("We check that the board name was updated")
                .isEqualTo(title);
    }

    @Then("I add a list with a name {string} to the board")
    public void iAddAListWithANameToTheBoard(String listName) {
        Response response = createList(listName, TestCaseContext.getBoard().getId());
        List list = response.as(List.class);
        TestCaseContext.setList(list);

        Assertions.assertThat(list.getName())
                .as("We check that the list was created with correct name)")
                .isEqualTo(listName);

        TestCaseContext.getScenario().log(list.getId());
    }
}
