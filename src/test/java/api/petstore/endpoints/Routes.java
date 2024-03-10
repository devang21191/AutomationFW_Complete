package api.petstore.endpoints;

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";
    public static String post_CreateUser_url = "/user";
    public static String post_CreateUsersWithArray_url = "/user/createWithArray";
    public static String post_CreateUsersWithList_url = "/user/createWithList";
    public static String get_User_url = "/user/{username}";
    public static String put_UpdateUser_url = "/user/{username}";
    public static String delete_User_url = "/user/{username}";
    public static String get_LoginUser_url = "/user/login";
    public static String get_LogoutUser_url = "/user/logout";
}
