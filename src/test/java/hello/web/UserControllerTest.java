package hello.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hello.entity.User;
import hello.repo.IUserRepository;
import hello.web.dto.IdDTO;
import hello.web.dto.UserDto;
import hello.web.request.UserFilterByAgeRequest;
import hello.web.request.UserFindByNameRequest;
import hello.web.request.UserRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Class for testing methods from {@link UserController}
 */
@EnableAutoConfiguration
public class UserControllerTest extends ParentTestClass {

    @Autowired
    private IUserRepository userRepository;

    private static final Gson GSON = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    private static final String URL_PREFIX = "/user";
    private static final User USER_1 = new User();
    private static final User USER_2 = new User();
    private static final String USER_NAME_1 = "name";
    private static final String USER_NAME_2 = "name2";
    private static final String USER_NAME_CREATE_AND_UPDATE_METHOD_TEST = "new_name";
    private static final Integer USER_AGE_1 = 99;
    private static final Integer USER_AGE_2 = 10;
    private static final String USER_PASSWORD = "12345";

    /**
     * Method for test finding one {@link User} by id when {@link User} exist
     */
    @Test
    public void findById_FoundUser_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(URL_PREFIX + "/{id}", USER_1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        UserDto userDto = getResponseDataAsObject(UserDto.class, responseStr);

        assertEquals(USER_1.getId(), userDto.getId());
        assertEquals(USER_NAME_1, userDto.getName());
        assertEquals((int) USER_AGE_1, userDto.getAge());

    }

    /**
     * Method for test finding one {@link User} by id when {@link User} not exist
     */
    @Test
    public void findById_NotFoundUser_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(URL_PREFIX + "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * Method for test finding one {@link User} by name when {@link User} exist
     */
    @Test
    public void findByName_FoundUser_Test() {
        UserFindByNameRequest req = new UserFindByNameRequest();
        req.setName(USER_NAME_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .get(URL_PREFIX + "/findByName");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        UserDto userDto = getResponseDataAsObject(UserDto.class, responseStr);

        assertEquals(USER_1.getId(), userDto.getId());
        assertEquals(USER_NAME_1, userDto.getName());
        assertEquals((int) USER_AGE_1, userDto.getAge());
    }

    /**
     * Method for test finding one {@link User} by name when {@link User} not exist
     */
    @Test
    public void findByName_NotFoundUser_Test() {
        UserFindByNameRequest req = new UserFindByNameRequest();
        req.setName("Fake_name");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .get(URL_PREFIX + "/findByName");

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * Method for test successful creating one {@link User}
     */
    @Test
    public void create_UserWasCreated_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD);
        req.setAge(USER_AGE_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .post(URL_PREFIX);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        String responseStr = response.asString();

        IdDTO idDTO = getResponseDataAsObject(IdDTO.class, responseStr);

        assertNotNull(idDTO.getId());
    }

    /**
     * Method for test unsuccessful creating one {@link User}
     */
    @Test
    public void create_UserWasNotCreated_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_1);
        req.setPassword(USER_PASSWORD);
        req.setAge(USER_AGE_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .post(URL_PREFIX);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    /**
     * Method for test successful updating one {@link User}
     */
    @Test
    public void update_UserExist_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD + "1");
        req.setAge(USER_AGE_1 + 1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .put(URL_PREFIX + "/{id}", USER_1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    /**
     * Method for test unsuccessful updating one {@link User}
     */
    @Test
    public void update_UserWasNotFound_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD + "1");
        req.setAge(USER_AGE_1 + 1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .put(URL_PREFIX + "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * Method for test successful deleting one {@link User}
     */
    @Test
    public void delete_UserExist_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(URL_PREFIX + "/{id}", USER_1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    /**
     * Method for test unsuccessful deleting one {@link User}
     */
    @Test
    public void delete_UserNotFound_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(URL_PREFIX + "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * Method for test finding all {@link User}s
     */
    @Test
    public void findAll_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(URL_PREFIX + "/getAll");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        List<UserDto> userDtos = getResponseDataAsObject(ArrayList.class, responseStr);

        assertEquals(2, userDtos.size());
    }

    /**
     * Method for test filtering {@link User}s by age
     */
    @Test
    public void filterByAge_Test() {
        UserFilterByAgeRequest req = new UserFilterByAgeRequest();
        req.setMin(5);
        req.setMax(15);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(req))
                .get(URL_PREFIX + "/filterByAge");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        List<UserDto> userDtos = getResponseDataAsObject(ArrayList.class, responseStr);

        assertEquals(1, userDtos.size());
    }

    /**
     * methods for executing any operations before started everyone test
     */
    @Before
    public void init() {

        USER_1.setName(USER_NAME_1);
        USER_1.setPassword(USER_PASSWORD);
        USER_1.setAge(USER_AGE_1);

        User userFromDb1 = userRepository.save(USER_1);

        USER_1.setId(userFromDb1.getId());

        USER_2.setName(USER_NAME_2);
        USER_2.setPassword(USER_PASSWORD);
        USER_2.setAge(USER_AGE_2);

        User userFromDb2 = userRepository.save(USER_2);

        USER_2.setId(userFromDb2.getId());
    }

    /**
     * methods for executing any operations after stopped everyone test
     */
    @After
    public void destroy() {
        userRepository.deleteAll();
    }
}