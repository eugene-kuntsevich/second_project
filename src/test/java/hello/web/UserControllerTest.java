package hello.web;

import hello.web.dto.IdDTO;
import hello.web.dto.UserDto;
import hello.web.request.UserFilterByAgeRequest;
import hello.web.request.UserFindByNameRequest;
import hello.web.request.UserRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@EnableAutoConfiguration
public class UserControllerTest extends ParentTestClass{

    @Test
    public void findById_FoundUser_Test() {
                Response response = given()
                .contentType(ContentType.JSON)
                .get(urlPrefix + "/{id}", USER1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        UserDto userDto = getResponseDataAsObject(UserDto.class, responseStr);

        assertEquals(USER1.getId(), userDto.getId());
        assertEquals(USER_NAME_1, userDto.getName());
        assertEquals((int) USER_AGE_1, userDto.getAge());

    }

    @Test
    public void findById_NotFoundUser_Test() {
                Response response = given()
                .contentType(ContentType.JSON)
                .get(urlPrefix + "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void findByName_FoundUser_Test() {
        UserFindByNameRequest req = new UserFindByNameRequest();
        req.setName(USER_NAME_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .get(urlPrefix + "/findByName");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        UserDto userDto = getResponseDataAsObject(UserDto.class, responseStr);

        assertEquals(USER1.getId(), userDto.getId());
        assertEquals(USER_NAME_1, userDto.getName());
        assertEquals((int) USER_AGE_1, userDto.getAge());
    }

    @Test
    public void findByName_NotFoundUser_Test() {
        UserFindByNameRequest req = new UserFindByNameRequest();
        req.setName("Fake_name");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .get(urlPrefix + "/findByName");

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void create_UserWasCreated_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD);
        req.setAge(USER_AGE_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .post(urlPrefix);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        String responseStr = response.asString();

        IdDTO idDTO = getResponseDataAsObject(IdDTO.class, responseStr);

        assertNotNull(idDTO.getId());
    }

    @Test
    public void create_UserWasNotCreated_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_1);
        req.setPassword(USER_PASSWORD);
        req.setAge(USER_AGE_1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .post(urlPrefix);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void update_UserWasUpdated_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD + "1");
        req.setAge(USER_AGE_1 + 1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .put(urlPrefix+ "/{id}", USER1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void update_UserWasNotFound_Test() {
        UserRequest req = new UserRequest();
        req.setName(USER_NAME_CREATE_AND_UPDATE_METHOD_TEST);
        req.setPassword(USER_PASSWORD + "1");
        req.setAge(USER_AGE_1 + 1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .put(urlPrefix+ "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void delete_UserWasDeleted_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(urlPrefix+ "/{id}", USER1.getId());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void delete_UserNotFound_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(urlPrefix+ "/{id}", -1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void findAll_Test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(urlPrefix+ "/getAll");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        List<UserDto> userDtos = getResponseDataAsObject(ArrayList.class, responseStr);

        assertEquals(2, userDtos.size());
    }

    @Test
    public void filterByAge_Test() {
        UserFilterByAgeRequest req = new UserFilterByAgeRequest();
        req.setMin(5);
        req.setMax(15);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(req))
                .get(urlPrefix+ "/filterByAge");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String responseStr = response.asString();

        List<UserDto> userDtos = getResponseDataAsObject(ArrayList.class, responseStr);

        assertEquals(1, userDtos.size());
    }
}