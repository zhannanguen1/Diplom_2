import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateUserTest {
    private UserClient userClient;
    private CreateUser createUser;
    private String accessToken;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
        createUser = UsersDataForTests.getNewValidUser();
    }

    @Test
    @Description("Проверка создания юзера")
    public void checkCreateUserTest() {
        ValidatableResponse response = userClient.createUser(createUser);
        int actualStatusCode = response.extract().statusCode();
        boolean isSuccessInMessageTrueCreate = response.extract().path("success");
        accessToken = response.extract().path("accessToken");
        assertEquals(200, actualStatusCode);
        assertTrue(isSuccessInMessageTrueCreate);
    }

    @After
    @Step("Удаление пользователя")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }

}
