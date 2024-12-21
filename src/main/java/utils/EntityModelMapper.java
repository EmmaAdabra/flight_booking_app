package utils;

import models.Role;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityModelMapper {
    static public User userMapper(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("UserID");
        String firstName = resultSet.getString("FirstName");
        String lastName = resultSet.getString("LastName");
        String email = resultSet.getString("Email");
        String role = resultSet.getString("role");
        Role userRole = Role.USER;

        if (role.equalsIgnoreCase("ADMIN")) {
            userRole = Role.ADMIN;
        }

        User user = new User(firstName, lastName, email, userRole);
        user.setId(userID);

        return user;
    }
}
