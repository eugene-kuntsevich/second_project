package hello.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 *Entity for @User
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * id for @User
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * user's name
     */
    @Column(unique = true)
    private String name;

    /**
     * user's password
     */
    private String password;

    /**
     * date when user was created
     */
    @Column(updatable = false)
    private Date creationDate = new Date();


    /**
     * @return user's id
     */
    public Long getId() {
        return id;
    }

    /**
     * set user's id
     * @param id id for @User
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user's name
     */
    public String getName() {
        return name;
    }


    /**
     * set user's name
     * @param name name for @User
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return user's password
     */
    public String getPassword() {
        return password;
    }


    /**
     * set user's password
     * @param password password for @User
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
