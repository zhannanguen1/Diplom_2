import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateAuthorizedUserDataTest {
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
    @Description("Изменение почты юзера")
    public void changeUsersEmail() {
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        CreateUser changeUsersParam = UsersDataForTests.getChangedEmailUser();
        String changedEmail = changeUsersParam.getEmail();
        ValidatableResponse response1 = userClient.updateDataOfUser(accessToken, User.from(changeUsersParam));
        int actualStatusCodeChange = response1.extract().statusCode();
        boolean isSuccessInMessageTrueChange = response1.extract().path("success");
        HashMap<String, String> changedUserData = response1.extract().path("user");
        String responseEmail = changedUserData.get("email");
        assertEquals(200, actualStatusCodeChange);
        assertTrue(isSuccessInMessageTrueChange);
        assertEquals(changedEmail, responseEmail);
    }

    @Test
    @Description("Изменение почты юзера")
    public void changeUsersPassword() {
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        CreateUser changeUsersParam = UsersDataForTests.getChangedPasswordUser();
        String changedEmail = changeUsersParam.getEmail();
        ValidatableResponse response1 = userClient.updateDataOfUser(accessToken, User.from(changeUsersParam));
        int actualStatusCodeChange = response1.extract().statusCode();
        boolean isSuccessInMessageTrueChange = response1.extract().path("success");
        HashMap<String, String> changedUserData = response1.extract().path("user");
        String responseEmail = changedUserData.get("email");
        assertEquals(200, actualStatusCodeChange);
        assertTrue(isSuccessInMessageTrueChange);
        assertEquals(changedEmail, responseEmail);
    }

    @Test
    @Description("Изменение почты юзера")
    public void changeUsersName() {
        ValidatableResponse response = userClient.createUser(createUser);
        accessToken = response.extract().path("accessToken");
        CreateUser changeUsersParam = UsersDataForTests.getChangedNameUser();
        String changedEmail = changeUsersParam.getEmail();
        ValidatableResponse response1 = userClient.updateDataOfUser(accessToken, User.from(changeUsersParam));
        int actualStatusCodeChange = response1.extract().statusCode();
        boolean isSuccessInMessageTrueChange = response1.extract().path("success");
        HashMap<String, String> changedUserData = response1.extract().path("user");
        String responseEmail = changedUserData.get("email");
        assertEquals(200, actualStatusCodeChange);
        assertTrue(isSuccessInMessageTrueChange);
        assertEquals(changedEmail, responseEmail);
    }

    @After
    @Step("Удаление пользователя")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
