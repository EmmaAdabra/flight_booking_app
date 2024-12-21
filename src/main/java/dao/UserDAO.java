package dao;

import models.User;
import utils.EntityModelMapper;
import utils.PreparedStatementUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements Dao<User> {
    @Override
//    get a user from database by ID
    public Optional<User> get(int id) throws SQLException {
        String mysqlQuery = "SELECT * FROM users WHERE UserID = ?";
        User user = null;

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                  user = EntityModelMapper.userMapper(resultSet);
                }
            }
        }

        return Optional.ofNullable(user);
    }


//    get all registered user
    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String mysqlQuery = "SELECT * FROM users";

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    User user = EntityModelMapper.userMapper(resultSet);
                    users.add(user);
                }
            }
        }

        return users;
    }

//    insert new user record
    @Override
    public int save(User user) throws SQLException {
        int rowInserted;

        String sqlQuery = "INSERT INTO users (LastName, FirstName, Email, role) VALUES(?, ?, ?, ?)";
        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            PreparedStatementUtils.setParameters(
                preparedStatement,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name()
            );

            rowInserted = preparedStatement.executeUpdate();
        }

        return rowInserted;
    }


//    update user email
    @Override
    public int update(Object... params) throws SQLException {
        int rowUpdated;
        String mysqlQuery = "UPDATE users SET Email = ? WHERE UserID = ?";

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            PreparedStatementUtils.setParameters(preparedStatement, params[0], params[1]);

            rowUpdated = preparedStatement.executeUpdate();
        }

        return rowUpdated;
    }


//    delete user record
    @Override
    public int delete(int userID) throws SQLException {
        int deletedRow;
        String mysqlQuery = "DELETE FROM users WHERE UserID = ?";

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){

            preparedStatement.setInt(1, userID);
            deletedRow = preparedStatement.executeUpdate();

        }
        return deletedRow;
    }
}
