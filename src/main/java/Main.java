import controllers.UserController;
import dao.user_dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.IUserServices;
import services.UserServices;
import utils.UserDatabaseUtil;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Starting Application");

        logger.info("creating UserDAO object");
        UserDAO userDAO = new UserDAO();
        logger.info("creating UserService object");
        IUserServices userServices = new UserServices(userDAO);
        logger.info("creating UserController object");
        UserController controller = new UserController(userServices);
        logger.info("Application is fully running");

        System.out.println();
//        create a new user and save to DB
        logger.info("calling controller.registerUser()");
        controller.registerUser();
        logger.info("Exiting controller.registerUser()");

//        Get all registered users from DB
        System.out.println();
        System.out.println("Registered Users:");
        logger.info("Calling controller.printAllUsers()");
        controller.printAllUsers();
        logger.info("Exiting controller.printAllUsers()");

//        Update user email
        System.out.println();
        System.out.println("Update user email:");
        logger.info("Calling  controller.changeUserEmail()");
        controller.changeUserEmail();
        logger.info("Exiting  controller.changeUserEmail()");

//        get a user by ID
        System.out.println();
        System.out.println("User:");
        logger.info("Calling controller.fetchUser()");
        controller.fetchUser();
        logger.info("Exiting controller.fetchUser()");


//        Delete a user from DB
        System.out.println();
        System.out.println("Delete user:");
        logger.info("Calling controller.removeUser()");
        controller.removeUser();
        logger.info("Exiting controller.removeUser()");


//        show all users after CRUD on the DB
        System.out.println();
        System.out.println("Updated Registered user list: ");
        logger.info("calling controller.printAllUsers()");
        controller.printAllUsers();
        logger.info("Exiting controller.printAllUsers()");
    }
}
