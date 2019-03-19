package hello.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hello.entity.User;
import hello.repo.IUserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private IUserRepository userRepository;

    private static final Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    private static final String urlPrefix = "/user";
    private static final User USER = new User();
    private static final String USER_NAME = "name6";
    private static final Integer USER_AGE = 99;
    private static final String USER_PASSWORD = "12345";

    @PostConstruct
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Before
    public void init() {

        USER.setName(USER_NAME);
        USER.setPassword(USER_PASSWORD);
        USER.setAge(USER_AGE);

        User userFromDb = userRepository.save(USER);

        USER.setId(userFromDb.getId());
    }

    @Test
    public void findById() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(USER))
                .get(urlPrefix + "/{id}", USER.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void findByName() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void filterByAge() {
    }

    public void cleanDB() throws SQLException {
        SessionFactory factory = new Configuration().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("delete from Users");

            tx.commit();
        }

        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}