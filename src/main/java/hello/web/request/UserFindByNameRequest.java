package hello.web.request;

import hello.entity.User;

import javax.validation.constraints.NotBlank;

/**
 *Class for creating request for finding {@link User} by name
 */
public class UserFindByNameRequest {

    @NotBlank
    private String name;

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
}
