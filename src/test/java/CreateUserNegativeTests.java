import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateUserNegativeTests {
    private UserClient userClient;
    private CreateUser createUser;
    private String accessToken;
    @Before
    @Step("Подготовка тестовых данных")
    public void setUp(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient =  new UserClient();
        createUser = UsersDataForTests.getNewValidUser();
    }

    @Test
    @Description("Попытка создать юзера с уже существующими данными")
    public void createUserWithExistingData(){
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        ValidatableResponse response1 = userClient.createUser(createUser);
        int actualStatusCodeExistingUser = response1.extract().statusCode();
        boolean isSuccessInMessageFalseExistingUser = response1.extract().path("success");
        String responseMessage = response1.extract().path("message");
        assertEquals(403, actualStatusCodeExistingUser);
        assertFalse(isSuccessInMessageFalseExistingUser);
        assertEquals("User already exists", responseMessage);
    }
    @After
    @Step("Удаление пользователя")
    public void cleanUp(){
        userClient.deleteUser(accessToken);
    }
}
