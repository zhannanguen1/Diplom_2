import io.qameta.allure.Step;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsersDataForTests {
    @Step("Валидные данные для создания юзера")
    public static CreateUser getNewValidUser() {
        return new CreateUser("zhanna_nguen@test.com", "password1234", "Zhanna");
    }

    @Step("Данные для созданию юзера без email")
    public static CreateUser getUserWithoutEmail() {
        return new CreateUser(null, "samurai4321", "samurai");
    }

    @Step("Данные для созданию юзера без password")
    public static CreateUser getUserWithoutPassword() {
        return new CreateUser("daivz88@gmail.com", null, "samurai");
    }

    @Step("Данные для созданию юзера без name")
    public static CreateUser getUserWithoutName() {
        return new CreateUser("daivz88@gmail.com", "samurai4321", null);
    }

//    @Step("User with changed email")
//    public static CreateUser getChangedEmailUser() {
//        return new CreateUser(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()) + "daivz88@gmail.com", "samurai4321", "samurai");
//    }
//
//    @Step("User with changed password")
//    public static CreateUser getChangedPasswordUser() {
//        return new CreateUser("daivz88@gmail.com", "samurai4321" + DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()), "samurai");
//    }
//
//    @Step("User with changed name")
//    public static CreateUser getChangedNameUser() {
//        return new CreateUser("daivz88@gmail.com", "samurai4321", "samurai" + DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()));
//    }
}
