package api.petstore.endpoints;

import api.petstore.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Log4J;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    /**
     * To Create a single User
     * @param payload
     * @return
     */
    public static Response createUser(User payload, String message) {
        Log4J.info(message);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_CreateUser_url);
        return response;
    }

    /**
     * To login using username and password
     * @param username
     * @param password
     * @return
     */
    public static Response login(String username, String password, String message) {
        Log4J.info(message);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .pathParam("password", password)
                .when()
                .get(Routes.get_LoginUser_url);
        return response;
    }

    /**
     * To fetch a User by Username
     * @param username
     * @return
     */
    public static Response getUser(String username, String message) {
        Log4J.info(message);

        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.get_User_url);
        return response;
    }

    /**
     * To update a User by Username
     * @param username
     * @param payload
     * @return
     */
    public static Response updateUser(String username, User payload, String message) {
        Log4J.info(message);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                .put(Routes.put_UpdateUser_url);
        return response;
    }

    /**
     * To delete a User by Username
     * @param username
     * @return
     */
    public static Response deleteUser(String username, String message) {
        Log4J.info(message);
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_User_url);
        return response;
    }
}
