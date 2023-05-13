import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateOrdersTest {
    private UserClient userClient;
    private CreateUser createUser;
    private UserOrders userOrders;
    private UserIngredients userIngredients;
    private String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
        createUser = UsersDataForTests.getNewValidUser();
        userOrders = new UserOrders();
        userIngredients = new UserIngredients();
    }

    @Test
    @Description("Создание заказа")
    public void createOrdersTest() {
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        ValidatableResponse responseIngredients = userIngredients.getIngredients();
        ArrayList<HashMap<String, String>> responseData = responseIngredients.extract().path("data");
        Ingredients ingredients = InvalidIngredients.getValidIngredientsHash(responseData);
        ValidatableResponse responseCreateOrder = userOrders.createUserOrders(accessToken, ingredients);
        int actualStatusCodeCreateOrder = responseCreateOrder.extract().statusCode();
        boolean isSuccessInMessageTrueCreateOrder = responseCreateOrder.extract().path("success");
        assertEquals(200, actualStatusCodeCreateOrder);
        assertTrue(isSuccessInMessageTrueCreateOrder);
    }

    @After
    @Step("Удаление пользователя")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
