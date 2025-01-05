package utils;

import dao.CreateConnection;
import dao.user_dao.UsersSqlQueriesFilePaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseUtil {
    public static int getUserID(String userEmail){
        Logger logger = LoggerFactory.getLogger(UserDatabaseUtil.class);
        String maskEmail = StringUtility.maskEmail(userEmail);
        int userID = 0;
        String sqlQuery = null;
        String sqlScriptFilePath = UsersSqlQueriesFilePaths.GET_USER_ID.getPath();
        logger.debug("Retrieving user ID with with email - {}", maskEmail);
        try {
            logger.debug("fetching get user id sql query from {}", sqlScriptFilePath);
            sqlQuery = GetSqlQueryUtil.buildSqlQuery(sqlScriptFilePath);
//            System.out.println(sqlQuery);
        } catch (IOException e){
            logger.error("Failed to fetch sql query for get user by id from {}", sqlScriptFilePath, e);
        }

        logger.debug("creating a database connection");
        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            preparedStatement.setString(1, userEmail);
            logger.debug("Executing query to find user ID with email {}", maskEmail);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    userID = resultSet.getInt("UserID");
                    logger.debug("Successfully fetched userID with email {} from database", maskEmail);
                }
                else {
                    logger.warn("User ID with the email {} not found in database", maskEmail);
                }
            }
        } catch (SQLException e) {
            logger.error("Database error while fetching user ID with email - {}", maskEmail);
        }

        return userID;
    }
}
