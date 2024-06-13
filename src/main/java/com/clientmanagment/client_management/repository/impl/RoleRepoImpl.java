package com.clientmanagment.client_management.repository.impl;

import com.clientmanagment.client_management.exceptions.ApiException;
import com.clientmanagment.client_management.model.Role;
import com.clientmanagment.client_management.repository.RoleRepository;
import com.clientmanagment.client_management.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static com.clientmanagment.client_management.enums.RoleTypesEnum.ROLE_USER;
import static com.clientmanagment.client_management.query.RoleQuery.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepoImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection List(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long data) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role {} to user id: {}", roleName, userId);

        try{
            Role role = jdbcTemplate.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("name", roleName),
                    new RoleRowMapper());
            jdbcTemplate.update(INSERT_ROLE_TO_USER_QUERY, Map.of("userId", userId, "roleId", role.getId()));
        }catch(
                EmptyResultDataAccessException exception
        ) {
            log.error(exception.getMessage());
            throw new ApiException("No role found by name: " + ROLE_USER.name());
        }catch( Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again later.");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
