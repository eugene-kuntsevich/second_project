package hello.web.request;

import hello.entity.User;

import javax.validation.constraints.NotBlank;

/**
 *Entity for wiring with {@link User}
 */
public class UserFindByNameRequest {
    /**
     * name for {@link User}
     */
    @NotBlank
    private String name;

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
}
