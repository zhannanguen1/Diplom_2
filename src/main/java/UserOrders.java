import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserOrders {
    private static final String ORDERS_PATH = "api/orders";

    @Step("Создание заказа")
    public ValidatableResponse createUserOrders(String accessToken, Ingredients ingredients) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .and()
                .body(ingredients)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Создание заказа без предварительной авторизации")
    public ValidatableResponse createUserOrdersWithoutLogin(Ingredients ingredients) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(ingredients)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Создание заказа с ингредиентами")
    public ValidatableResponse createOrderWithoutIngredients() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Создание заказа без ингредиентов")
    public ValidatableResponse createOrderWithIngredients(Ingredients ingredients) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(ingredients)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получение заказа авторизованного юзера")
    public ValidatableResponse getOrderAuthorizedUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .when()
                .get(ORDERS_PATH)
                .then();
    }

    @Step("Получение заказа неавторизованного юзера")
    public ValidatableResponse getOrderUnauthorizedUser() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_PATH)
                .then();
    }

}
