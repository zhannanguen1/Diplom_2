import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginWithExistingUserTest {
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
    @Description("Вход юзера в систему")
    public void userCanLogin() {
        ValidatableResponse response = userClient.createUser(createUser);
        ValidatableResponse responseLogin = userClient.userLogin(UserAccount.from(createUser));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        boolean isSuccessInMessageTrueLogin = responseLogin.extract().path("success");
        String userData = responseLogin.extract().path("user").toString();
        accessToken = responseLogin.extract().path("accessToken");
        assertEquals(200, actualStatusCodeLogin);
        assertTrue(isSuccessInMessageTrueLogin);
        assertTrue(userData.contains("email"));
        assertTrue(userData.contains("name"));
        assertTrue(accessToken.contains("Bearer"));
    }

    @After
    @Step("Удаление пользователя")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
