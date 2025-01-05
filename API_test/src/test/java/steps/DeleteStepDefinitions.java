package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteStepDefinitions {

    private Response response;
    private int id;

    @Given("I have a pet with id {int} that I want to delete")
    public void iHaveAPetWithIdThatIWantToDelete(Integer petId) {
        RestAssured.basePath = "/pet/{id}";
        id = petId;
    }

    @When("I delete the pet")
    public void iDeleteThePetWithId() {
        response = RestAssured.given()
                .header("accept", "*/*")
                .header("api_key", "1")
                .pathParam("id", id)
                .when()
                .delete();
    }

    @Then("I should receive a confirmation that the pet was deleted")
    public void iShouldReceiveAConfirmationThatThePetWasDeleted() {
        assertEquals(200, response.getStatusCode(), "The request should be successful");
    }

    @Then("the pet should no longer exist in the store")
    public void thePetShouldNoLongerExistInTheStore() {
        Response getResponse = RestAssured.given()
                .header("accept", "application/xml")
                .pathParam("id", id)
                .when()
                .get();

        assertEquals(404, getResponse.getStatusCode(), "The pet should no longer exist");
    }
}
