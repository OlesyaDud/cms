package com.clientmanagment.client_management.query;

public class RoleQuery {

    public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO User_Roles (user_id, role_id) VALUES (:userId, :roleId)";
    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM Roles WHERE name = :name";
}
