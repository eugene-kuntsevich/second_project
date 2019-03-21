package hello.web.request;

import hello.entity.User;

import javax.validation.constraints.NotBlank;

/**
 *Class for creating request for creation {@link User}
 */
public class UserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private Integer age;

    /**
     * @return {@link User}'s name
     */
    public String getName() {
        return name;
    }

    /**
     * setting {@link User}'s name
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
     * setting {@link User}'s password
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
     * setting {@link User}'s age
     * @param age age for {@link User}
     */
    public void setAge(int age) {
        this.age = age;
    }
}
