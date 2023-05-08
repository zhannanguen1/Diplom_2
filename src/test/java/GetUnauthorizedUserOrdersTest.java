import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetUnauthorizedUserOrdersTest {
    private UserOrders userOrders;
    private UserClient userClient;
    private CreateUser createUser;
    private UserIngredients userIngredients;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
        createUser = UsersDataForTests.getNewValidUser();
        userOrders = new UserOrders();
        userIngredients = new UserIngredients();
    }

    @Test
    @Description("Получение списка заказов неавторизованным пользователем")
    public void getOrdersUnauthorizedUser() {
        ValidatableResponse responseUserOrders = userOrders.getOrderUnauthorizedUser();
        int actualStatusCodeOrders = responseUserOrders.extract().statusCode();
        boolean isSuccessInMessageFalseOrders = responseUserOrders.extract().path("success");
        String responseMessage = responseUserOrders.extract().path("message");
        assertEquals(401, actualStatusCodeOrders);
        assertFalse(isSuccessInMessageFalseOrders);
        assertEquals("You should be authorised", responseMessage);
    }
}
