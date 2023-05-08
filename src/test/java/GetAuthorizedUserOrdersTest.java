import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GetAuthorizedUserOrdersTest {
    private UserOrders userOrders;
    private UserClient userClient;
    private CreateUser createUser;
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
    @Description("Получение списка заказов авторизованным пользователем")
    public void getOrdersAuthorizedUser() {
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        ValidatableResponse responseIngredients = userIngredients.getIngredients();
        ArrayList<HashMap<String, String>> responseData = responseIngredients.extract().path("data");
        Ingredients ingredients = InvalidIngredients.getValidIngredientsHash(responseData);
        ValidatableResponse responseCreateOrder = userOrders.createUserOrders(accessToken, ingredients);
        ValidatableResponse responseGetList = userOrders.getOrderAuthorizedUser(accessToken);
        int actualStatusCodeOrders = responseGetList.extract().statusCode();
        boolean isSuccessInMessageTrueOrders = responseGetList.extract().path("success");
        assertEquals(200, actualStatusCodeOrders);
        assertTrue(isSuccessInMessageTrueOrders);
    }
    @After
    @Step("Удаление пользователя")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
