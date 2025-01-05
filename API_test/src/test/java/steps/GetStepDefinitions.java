package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class GetStepDefinitions {

    private Response response;

    @Given("I have a pet with id {int}")
    public void iHaveAPetWithId(Integer petId) {
        RestAssured.basePath = "/pet/{id}";
        response = RestAssured.given()
                .pathParam("id", petId)
                .header("accept", "application/xml")
                .when()
                .get();
    }

    @When("I ask for the pet details")
    public void iAskForThePetDetails() {
        // No extra action needed, request is made in the Given step
    }

    @Then("I should receive a confirmation that the request was successful")
    public void iShouldReceiveAConfirmationThatTheRequestWasSuccessful() {
        assertEquals(200, response.getStatusCode(), "The request should be successful");
    }

    @Then("I should see the pet's id in the response")
    public void iShouldSeeThePetsIdInTheResponse() {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("<id>10</id>"), "The pet id should be in the response");
    }
}
