import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LoginWithInvalidUserDataTest {
    private UserClient userClient;
    private User user;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient =  new UserClient();
    }
    @Test
    @Description("Попытка войти в систему с неверным email юзера")
    public void userCanNotLoginWithNotValidEmail(){
        CreateUser createUser = UsersDataForTests.getChangedEmailUser();
        ValidatableResponse response = userClient.userLogin(UserAccount.from(createUser));
        int actualStatusCodeCreate = response.extract().statusCode();
        boolean isSuccessInMessageFalse = response.extract().path("success");
        String message = response.extract().path("message");
        assertEquals(401, actualStatusCodeCreate);
        assertFalse(isSuccessInMessageFalse);
        assertEquals("email or password are incorrect", message);
    }
    @Test
    @Description("Попытка войти в систему с неверным password юзера")
    public void userCanNotLoginWithNotValidPassword(){
        CreateUser createUser = UsersDataForTests.getChangedPasswordUser();
        ValidatableResponse response = userClient.userLogin(UserAccount.from(createUser));
        int actualStatusCodeCreate = response.extract().statusCode();
        boolean isSuccessInMessageFalse = response.extract().path("success");
        String message = response.extract().path("message");
        assertEquals(401, actualStatusCodeCreate);
        assertFalse(isSuccessInMessageFalse);
        assertEquals("email or password are incorrect", message);
    }
}
