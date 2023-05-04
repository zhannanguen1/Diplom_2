import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserIngredients {
    private static final String INGREDIENTS_PATH = "api/ingredients";
    @Step("Получить список ингредиентов")
    public ValidatableResponse getIngredients() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(INGREDIENTS_PATH)
                .then();
    }

}
