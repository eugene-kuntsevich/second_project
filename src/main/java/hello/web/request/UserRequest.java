package hello.web.request;

import hello.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *Entity for wiring with {@link User}
 */
public class UserRequest {
    /**
     * name for {@link User}
     */
    @NotBlank
    private String name;

    /**
     * password for {@link User}
     */
    @NotBlank
    private String password;

    /**
     * age for {@link User}
     */
    private Integer age;

    /**
     * @return {@link User}'s name
     */
    public String getName() {
        return name;
    }

    /**
     * set {@link User}'s name
     * @param name name for {@link User}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link User}'s password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set {@link User}'s password
     * @param password password for {@link User}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return {@link User}'s age
     */
    public int getAge() {
        return age;
    }

    /**
     * set {@link User}'s age
     * @param age age for {@link User}
     */
    public void setAge(int age) {
        this.age = age;
    }
}
