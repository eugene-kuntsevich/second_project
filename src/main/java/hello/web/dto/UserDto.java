package hello.web.dto;

import java.util.Date;

public class UserDto {

    private Long id;

    private String name;

    private int age;

    private Date creationDate;

    /**
     * constructor for {@link UserDto}
     */
    public UserDto() {
    }

    /**
     * @return {@link UserDto}'s id
     */
    public Long getId() {
        return id;
    }

    /**
     * set {@link UserDto}'s id
     *
     * @param id id for {@link UserDto}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return {@link UserDto}'s name
     */
    public String getName() {
        return name;
    }

    /**
     * set {@link UserDto}'s name
     *
     * @param name name for {@link UserDto}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link UserDto}'s age
     */
    public int getAge() {
        return age;
    }

    /**
     * set {@link UserDto}'s age
     *
     * @param age age for {@link UserDto}
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * set {@link UserDto}'s creationDate
     *
     * @param creationDate creationDate for {@link UserDto}
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return {@link UserDto}'s date of creation
     */
    public Date getCreationDate() {
        return creationDate;
    }
}
