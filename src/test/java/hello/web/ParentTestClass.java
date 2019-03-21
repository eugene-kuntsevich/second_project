package hello.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import org.junit.runner.RunWith;
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
}
