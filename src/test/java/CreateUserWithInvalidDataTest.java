import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateUserWithInvalidDataTest {
    private UserClient userClient;
    private CreateUser createUser;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    @Description("Попытка создать юзера без email")
    public void createUserWithoutEmailParam() {
        userClient = new UserClient();
        createUser = UsersDataForTests.getUserWithoutEmail();
        ValidatableResponse response = userClient.createUser(createUser);
        int actualStatusCodeExistingUser = response.extract().statusCode();
        boolean isSuccessInMessageFalseExistingUser = response.extract().path("success");
        String responseMessage = response.extract().path("message");
        assertEquals(403, actualStatusCodeExistingUser);
        assertFalse(isSuccessInMessageFalseExistingUser);
        assertEquals("Email, password and name are required fields", responseMessage);
    }

    @Test
    @Description("Попытка создать юзера без Password")
    public void createUserWithoutPasswordParam() {
        userClient = new UserClient();
        createUser = UsersDataForTests.getUserWithoutPassword();
        ValidatableResponse response = userClient.createUser(createUser);
        int actualStatusCodeExistingUser = response.extract().statusCode();
        boolean isSuccessInMessageFalseExistingUser = response.extract().path("success");
        String responseMessage = response.extract().path("message");
        assertEquals(403, actualStatusCodeExistingUser);
        assertFalse(isSuccessInMessageFalseExistingUser);
        assertEquals("Email, password and name are required fields", responseMessage);
    }

    @Test
    @Description("Попытка создать юзера без name")
    public void createUserWithoutNameParam() {
        userClient = new UserClient();
        createUser = UsersDataForTests.getUserWithoutName();
        ValidatableResponse response = userClient.createUser(createUser);
        int actualStatusCodeExistingUser = response.extract().statusCode();
        boolean isSuccessInMessageFalseExistingUser = response.extract().path("success");
        String responseMessage = response.extract().path("message");
        assertEquals(403, actualStatusCodeExistingUser);
        assertFalse(isSuccessInMessageFalseExistingUser);
        assertEquals("Email, password and name are required fields", responseMessage);
    }
}
