public class User {
    private String email;
    private String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
    public User() {}

    public static User from(CreateUser createUser) {
        return new User(createUser.getEmail(), createUser.getName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}