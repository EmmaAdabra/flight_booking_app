package dao.user_dao;

import dao.CreateConnection;
import dao.Dao;
import dao.exception.DAOException;
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
    @Override
//    get a user from database by ID
    public Optional<User> get(int id) throws DAOException {
        String mysqlQuery;
        User user = null;

        try {
            mysqlQuery = GetSqlQueryUtil.buildSqlQuery(UsersSqlQueriesFilePaths.GET_USER_BY_ID.getPath());
        } catch (IOException e){
            throw new DAOException("Failed to load sql query for getting user by ID:\n" + e.getMessage(), e);
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
            throw new DAOException("Error executing get user by ID query:\n" + e.getMessage(), e);
        }

        return Optional.ofNullable(user);
    }


//    get all registered user
    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil.buildSqlQuery(UsersSqlQueriesFilePaths.GET_ALL_USERS.getPath());
        } catch (IOException e){
            throw new DAOException("Failed to load sql query for getting all users:\n" + e.getMessage(), e);
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
            throw new DAOException("Error in executing get all users query:\n" + e.getMessage(), e);
        }

        return users;
    }

//    insert new user record
    @Override
    public int save(User user) throws DAOException {
        int rowInserted;
        String sqlQuery;

        try {
            sqlQuery = GetSqlQueryUtil.buildSqlQuery(UsersSqlQueriesFilePaths.INSERT_USER_RECORD.getPath());
        } catch (IOException e){
            throw new DAOException("Failed to load sql query for inserting user record:\n" + e.getMessage(), e);
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
            throw new DAOException("Failed to insert user record:\n" + e.getMessage(), e);
        }

        return rowInserted;
    }


//    update user email
    @Override
    public int update(Object... params) throws DAOException {
        int rowUpdated;
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil
                    .buildSqlQuery(UsersSqlQueriesFilePaths.UPDATE_USER_EMAIL.getPath());
        } catch (IOException e){
            throw new DAOException("Error in loading update user email query script:\n" + e.getMessage(), e);
        }

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){
            PreparedStatementUtils.setParameters(preparedStatement, params[0], params[1]);

            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e);
        }

        return rowUpdated;
    }


//    delete user record
    @Override
    public int delete(int userID) throws DAOException {
        int deletedRow;
        String mysqlQuery;

        try{
            mysqlQuery = GetSqlQueryUtil
                    .buildSqlQuery(UsersSqlQueriesFilePaths.DELETE_USER.getPath());
        } catch (IOException e){
            throw new DAOException(e.getMessage(), e);
        }

        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mysqlQuery)){

            preparedStatement.setInt(1, userID);
            deletedRow = preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e);
        }

        return deletedRow;
    }
}
