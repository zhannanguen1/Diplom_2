import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UpdateUnauthorizedUserDataTest {
    private UserClient userClient;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
    }

    @Test
    @Description("Изменение почты юзера")
    public void changeNotAuthorizedUsersEmail() {
        CreateUser changeUsersParam = UsersDataForTests.getChangedEmailUser();
        ValidatableResponse response = userClient.updateDataOfUserWithoutAccess(User.from(changeUsersParam));
        int actualStatusCodeChange = response.extract().statusCode();
        boolean isSuccessInMessageFalseChange = response.extract().path("success");
        String responseMessage = response.extract().path("message");
        assertEquals(401, actualStatusCodeChange);
        assertFalse(isSuccessInMessageFalseChange);
        assertEquals("You should be authorised", responseMessage);
    }

    @Test
    @Description("Изменение почты юзера")
    public void changeNotAuthorizedUsersName() {
        CreateUser changeUsersParam = UsersDataForTests.getChangedNameUser();
        ValidatableResponse response = userClient.updateDataOfUserWithoutAccess(User.from(changeUsersParam));
        int actualStatusCodeChange = response.extract().statusCode();
        boolean isSuccessInMessageFalseChange = response.extract().path("success");
        String responseMessage = response.extract().path("message");
        assertEquals(401, actualStatusCodeChange);
        assertFalse(isSuccessInMessageFalseChange);
        assertEquals("You should be authorised", responseMessage);
    }

}
