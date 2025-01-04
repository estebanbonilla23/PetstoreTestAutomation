package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pets.Dog;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostStepDefinitions {

    private Dog dog;
    private Response response;

    @Given("There is a new pet to add with id {int}")
    public void iHaveTheFollowingPetDetails(int id) {
        dog = Dog.generateRandomDog(id);
    }

    @When("I submit the pet creation")
    public void iSendARequestToCreateThePet() {

        response = given()
                .baseUri("https://petstore3.swagger.io/api/v3")
                .header("accept", "application/xml")
                .header("Content-Type", "application/json")
                .body(dog.toJson())
                .when()
                .post("/pet");
    }

    @Then("I should receive a successful response")
    public void iShouldReceiveASuccessfulResponse() {
        assertEquals(200, response.statusCode());
    }

    @Then("the response should include the correct pet information")
    public void theResponseShouldContainThePetDetails() {

        response.body().print();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        String petId = response.xmlPath().getString("pet.id");
        assertEquals(String.valueOf(dog.getId()), petId);
        assertEquals(dog.getName(), response.xmlPath().getString("pet.name"));
        assertEquals(String.valueOf(dog.getCategory().getId()), response.xmlPath().getString("pet.category.id"));
        assertEquals(dog.getCategory().getName(), response.xmlPath().getString("pet.category.name"));
        assertEquals(dog.getStatus(), response.xmlPath().getString("pet.status"));
    }
}
