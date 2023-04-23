package apiApril.stepdefinitions;

import static apiApril.clients.TrelloClient.deleteList;
import static apiApril.clients.TrelloClient.updateBoardInfo;
import static apiApril.constants.ProjectConstants.BOARD_NAME;

import apiApril.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before
    public void beforeHook(Scenario scenario){
        TestCaseContext.init();
        TestCaseContext.setScenario(scenario);
        System.out.println("THE SCENARIO HAS STARTED");
    }

    @After
    public void afterHook(){
        deleteList(TestCaseContext.getList().getId());
        updateBoardInfo(BOARD_NAME, TestCaseContext.getBoard().getId());
        System.out.println("THE SCENARIO HAS ENDED");
    }
}