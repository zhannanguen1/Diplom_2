import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateOrdersNegativeTest {
    private UserOrders userOrders;
    private UserIngredients userIngredients;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userOrders = new UserOrders();
        userIngredients = new UserIngredients();
    }

    @Test
    @Description("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {
        ValidatableResponse responseCreateOrder = userOrders.createOrderWithoutIngredients();
        int actualStatusCode = responseCreateOrder.extract().statusCode();
        boolean isSuccessInMessageFalseCreateOrder = responseCreateOrder.extract().path("success");
        assertFalse(isSuccessInMessageFalseCreateOrder);
        assertEquals(400, actualStatusCode);
    }

    @Test
    @Description("Создание заказа с неверных хэшем ингредиента")
    public void createOrderWithInvalidHashOfIngredient() {
        Ingredients ingredients = InvalidIngredients.getInvalidIngredientsHash();
        ValidatableResponse responseCreateOrder = userOrders.createOrderWithIngredients(ingredients);
        int actualStatusCode = responseCreateOrder.extract().statusCode();
        assertEquals(500, actualStatusCode);
    }
}
