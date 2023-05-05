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
        return new CreateUser(null, "password1234", "Zhanna");
    }

    @Step("Данные для созданию юзера без password")
    public static CreateUser getUserWithoutPassword() {
        return new CreateUser("zhanna_nguen@test.com", null, "Zhanna");
    }

    @Step("Данные для созданию юзера без name")
    public static CreateUser getUserWithoutName() {
        return new CreateUser("zhanna_nguen@test.com", "password1234", null);
    }

    @Step("Данные для изменения email")
    public static CreateUser getChangedEmailUser() {
        return new CreateUser(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()) + "zhanna_nguen_17group@test.com", "password1234", "Zhanna");
    }

    @Step("Данные для изменения password")
    public static CreateUser getChangedPasswordUser() {
        return new CreateUser("zhanna_nguen@test.com", "password1234567" + DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()), "Zhanna");
    }

    @Step("Данные для изменения name")
    public static CreateUser getChangedNameUser() {
        return new CreateUser("zhanna_nguen@test.com", "password1234", "Zhanna123" + DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now()));
    }
}
