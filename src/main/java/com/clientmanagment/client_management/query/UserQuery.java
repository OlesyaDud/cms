package com.clientmanagment.client_management.query;

public class UserQuery {

//    look at    private SqlParameterSource getSqlParameterSource(User user) { in UserRepositoryImpl for query names parameres
    public static final String INSERT_USER_QUERY = "INSERT INTO Users (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :password)";
    public static final String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) FROM Users WHERE email = :email";

    public static final String INSERT_ACCOUNT_VERIFICATION_URL_QUERY = "INSERT INTO Account_Verifications (user_id, url) VALUES (:userId, :url)";
}
