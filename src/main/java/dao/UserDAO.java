package dao;

import models.User;
import utils.EntityModelMapper;
import utils.GetSqlQueryUtil;
import utils.PreparedStatementUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements Dao<User> {
    private final String USER_SQL_QUERY_SCRIPT_DIR = "src/sql_queries/users_queries/";
    @Override
//    get a user from database by ID
    public Optional<User> get(int id) throws SQLExecutionException {
        String mysqlQuery;
        User user = null;

        try {
            mysqlQuery = GetSqlQueryUtil.buildSqlQuery(USER_SQL_QUERY_SCRIPT_DIR + "get_user_by_id.sql");
        } catch (IOException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                  user = EntityModelMapper.userMapper(resultSet);
                }
            }
        } catch (SQLException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        return Optional.ofNullable(user);
    }


//    get all registered user
    @Override
    public List<User> getAll() throws SQLExecutionException {
        List<User> users = new ArrayList<>();
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil.buildSqlQuery(USER_SQL_QUERY_SCRIPT_DIR + "get_all_users.sql");
        } catch (IOException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }


        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    User user = EntityModelMapper.userMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        return users;
    }

//    insert new user record
    @Override
    public int save(User user) throws SQLExecutionException {
        final String  SQL_SCRIPT_PATH = USER_SQL_QUERY_SCRIPT_DIR + "insert_user_record.sql";
        int rowInserted;
        String sqlQuery;

        try {
            sqlQuery = GetSqlQueryUtil.buildSqlQuery(SQL_SCRIPT_PATH);
        } catch (IOException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

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
        } catch (SQLException e) {
            throw new SQLExecutionException(e.getMessage(), e);
        }

        return rowInserted;
    }


//    update user email
    @Override
    public int update(Object... params) throws SQLExecutionException {
        int rowUpdated;
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil
                    .buildSqlQuery(USER_SQL_QUERY_SCRIPT_DIR + "update_user_email.sql");
        } catch (IOException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            PreparedStatementUtils.setParameters(preparedStatement, params[0], params[1]);

            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        return rowUpdated;
    }


//    delete user record
    @Override
    public int delete(int userID) throws SQLExecutionException{
        int deletedRow;
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil
                    .buildSqlQuery(USER_SQL_QUERY_SCRIPT_DIR + "delete_user.sql");
        } catch (IOException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){

            preparedStatement.setInt(1, userID);
            deletedRow = preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new SQLExecutionException(e.getMessage(), e);
        }

        return deletedRow;
    }
}
