import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import groovy.util.ObjectGraphBuilder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient {
//    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String CREATE_USER_PATH = "api/auth/register";
    public static final String LOGIN_PATH = "api/auth/login";
    public static final String LOGOUT_PATH = "api/auth/logout";
    public static final String TOKEN_PATH = "api/auth/token";
    public static final String USER_PATH = "api/auth/user";
//    public UserClient() {
//        RestAssured.baseURI = MAIN_URL;
//    }
    @Step("Регистрация юзера")
    public ValidatableResponse createUser(CreateUser createUser){
        return given()
                .headers("Content-type", "application/json")
                .and()
                .body(createUser)
                .when()
                .post(CREATE_USER_PATH)
                .then();
    }
    @Step("Вход юзера в систему")
    public ValidatableResponse userLogin(UserAccount userAccount){
        return given()
                .headers("Content-type", "application/json")
                .and()
                .body(userAccount)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
    @Step("Выход юзера из системы")
    public ValidatableResponse userLogout(String token){
        Gson deleteUserGson = new GsonBuilder().setPrettyPrinting().create();
        UpdateToken updateToken = new UpdateToken(String.valueOf(token));
        String updateTokenJson = deleteUserGson.toJson(updateToken);
        return given()
                .headers("Content-type", "application/json")
                .and()
                .body(updateTokenJson)
                .when()
                .post(LOGOUT_PATH)
                .then();
    }
    @Step("Обновление токена")
    public ValidatableResponse updateToken(UserAccount userAccount){
        return given()
                .headers("Content-Type", "application/json")
                .and()
                .body(userAccount)
                .when()
                .post(TOKEN_PATH)
                .then();
    }

    @Step("Получение данных о пользователе")
    public ValidatableResponse getUserData(String accessToken){
        return given()
                .headers("Content-Type", "application/json")
                .headers("authorization", accessToken)
                .when()
                .get(USER_PATH)
                .then();
    }

//    @Step("Выход юзера из системы")
//    public ValidatableResponse logoutUser(User user, String accessToken){
//        return given()
//                .header("Content-type", "application/json")
//                .headers("authorization", accessToken)
//                .and()
//                .body(user)
//                .when()
//                .post(LOGOUT_PATH)
//                .then();
//    }

    @Step("Удаление юзера")
    public ValidatableResponse deleteUser(String accessToken){
        return given()
                .header("Content-type", "application/json")
                .headers("authorization", accessToken)
                .when()
                .delete(USER_PATH)
                .then();
    }

    @Step("Обновление данных пользователя")
    public ValidatableResponse updateDataOfUser(String accessToken, User user){
        return given()
                .header("Content-type", "application/json")
                .headers("authorization", accessToken)
                .and()
                .body(user)
                .when()
                .patch(USER_PATH)
                .then();
    }
    @Step("Обновление данных пользователя без предварительной авторизации")
    public ValidatableResponse updateDataOfUserWithoutAccess(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .patch(USER_PATH)
                .then();
    }
}
