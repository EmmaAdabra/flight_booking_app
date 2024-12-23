import controllers.UserController;
import dao.UserDAO;
import services.IUserServices;
import services.UserServices;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        IUserServices userServices = new UserServices(userDAO);
        UserController controller = new UserController(userServices);

//        create a new user and save to DB
        controller.createUser();

//        Get all registered users from DB
        System.out.println();
        System.out.println("Registered Users:");
        controller.printAllUsers();

//        Update user email
        System.out.println();
        System.out.println("Update user email:");
        controller.changeUserEmail();

//        get a user by ID
        System.out.println();
        System.out.println("User:");
        controller.fetchUser();


//        Delete a user from DB
        System.out.println();
        System.out.println("Delete user:");
        controller.removeUser();


//        show all users after CRUD on the DB
        System.out.println();
        System.out.println("Updated Registered user list: ");
        controller.printAllUsers();
    }
}
