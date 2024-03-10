package api.petstore.testScripts;

import api.petstore.endpoints.UserEndPoints;
import api.petstore.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupUserData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void postCreateUser() {
        Response response = UserEndPoints.createUser(userPayload, "POST: Create a new User");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void login() {
        Response response = UserEndPoints.login(userPayload.getUsername(), userPayload.getPassword(), "GET: Login by Username and Password");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void getUserByUsername() {
        Response response = UserEndPoints.getUser(userPayload.getUsername(), "GET: Get User by Username");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void updateUserByUsername() {
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload, "PUT: Update User by Username");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        response = UserEndPoints.getUser(userPayload.getUsername(), "GET: Get User by Username");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void deleteUserByUsername() {
        Response response = UserEndPoints.deleteUser(userPayload.getUsername(), "DELETE: Delete User by Username");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
