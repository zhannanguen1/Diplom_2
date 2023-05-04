import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserOrders {
    private static final String ORDERS_PATH = "api/orders";
//    private static final String ALL_ORDERS_PATH = "api/orders/all";
    @Step("Получение списка заказов конкретного юзера")
    public ValidatableResponse getUserOrders(String accessToken, Ingredients ingredients) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .and()
                .body(ingredients)
                .when()
                .get(ORDERS_PATH)
                .then();
    }
    @Step("Получение списка заказов конкретного юзера без предварительной авторизации")
    public ValidatableResponse getUserOrdersWithoutLogin(Ingredients ingredients) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(ingredients)
                .when()
                .get(ORDERS_PATH)
                .then();
    }
}
