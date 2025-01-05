package dao.user_dao;

public enum UsersSqlQueriesFilePaths {
    INSERT_USER_RECORD(get_userQueriesDir() + "insert_user_record.sql"),
    GET_ALL_USERS(get_userQueriesDir() + "get_all_users.sql"),
    GET_USER_BY_ID(get_userQueriesDir() + "get_user_by_id.sql"),
    GET_USER_ID(get_userQueriesDir() + "get_user_id.sql"),
    UPDATE_USER_EMAIL(get_userQueriesDir() + "update_user_email.sql"),
    DELETE_USER(get_userQueriesDir() + "delete_user.sql");

    private static final String USERS_QUERIES_DIR = "src/sql_queries/users_queries/";
    private final String path;

    UsersSqlQueriesFilePaths(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }
    private static String get_userQueriesDir(){
        return USERS_QUERIES_DIR;
    }



}
