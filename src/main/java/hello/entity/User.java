package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;


/**
 *Entity for {@link User}
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * id for {@link User}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * name for {@link User}
     */
    @Column(unique = true)
    private String name;

    /**
     * password for {@link User}
     */
    private String password;

    /**
     * age for {@link User}
     */
    private int age;

    /**
     * date when {@link User} was created
     */
    @Column(updatable = false)
    private Date creationDate = new Date();


    /**
     * @return {@link User}'s id
     */
    public Long getId() {
        return id;
    }

    /**
     * set {@link User}'s id
     * @param id id for {@link User}
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * @return {@link User}'s date of creation
     */
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, age);
    }
}
