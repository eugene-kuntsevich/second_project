package hello.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hello.entity.User;
import hello.repo.IUserRepository;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

/**
 * Class for test's configuration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ParentTestClass {

    @Autowired
    protected IUserRepository userRepository;

    protected static final Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    protected static final String urlPrefix = "/user";
    protected static final User USER1 = new User();
    protected static final User USER2 = new User();
    protected static final String USER_NAME_1 = "name";
    protected static final String USER_NAME_2 = "name2";
    protected static final String USER_NAME_CREATE_AND_UPDATE_METHOD_TEST = "new_name";
    protected static final Integer USER_AGE_1 = 99;
    protected static final Integer USER_AGE_2 = 10;
    protected static final String USER_PASSWORD = "12345";

    @LocalServerPort
    private int port;

    @PostConstruct
    protected void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    protected <T> T getResponseDataAsObject(Class<T> clazz, String responseStr) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy")
                .create();
        return gson.fromJson(responseStr, clazz);
    }

    /**
     * methods for executing any operations before started everyone test
     */
    @Before
    public void init() {

        USER1.setName(USER_NAME_1);
        USER1.setPassword(USER_PASSWORD);
        USER1.setAge(USER_AGE_1);

        User userFromDb1 = userRepository.save(USER1);

        USER1.setId(userFromDb1.getId());

        USER2.setName(USER_NAME_2);
        USER2.setPassword(USER_PASSWORD);
        USER2.setAge(USER_AGE_2);

        User userFromDb2 = userRepository.save(USER2);

        USER2.setId(userFromDb2.getId());
    }

    /**
     * methods for executing any operations after stopped everyone test
     */
    @After
    public void destroy() {
        userRepository.deleteAll();
    }
}
