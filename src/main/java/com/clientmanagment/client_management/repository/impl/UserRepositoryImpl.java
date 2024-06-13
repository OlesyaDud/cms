package com.clientmanagment.client_management.repository.impl;

import com.clientmanagment.client_management.exceptions.ApiException;
import com.clientmanagment.client_management.model.Role;
import com.clientmanagment.client_management.model.User;
import com.clientmanagment.client_management.repository.RoleRepository;
import com.clientmanagment.client_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.clientmanagment.client_management.enums.RoleTypesEnum.*;
import static com.clientmanagment.client_management.enums.VerifsicationTypeEnum.ACCOUNT;
import static com.clientmanagment.client_management.query.UserQuery.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
//        check email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use.");
        }
//        save new user
        try{
//get user's id
            KeyHolder holder = new GeneratedKeyHolder();

            SqlParameterSource parameters = getSqlParameterSource(user);

//            insert new user into db
            jdbcTemplate.update(INSERT_USER_QUERY, parameters, holder);

//            get id
            user.setId(Objects.requireNonNull(holder.getKey().longValue()));

//        add role to the user
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());


//        send varification url
            String varificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            
//        save url and varification table
            jdbcTemplate.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, Map.of("userId", user.getId(), "url", varificationUrl));

//        send email to user with varification url
//            emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), varificationUrl, ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked((true));

//        return the new user
            return user;
//        if there are errors - throw exception with proper message
            
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
    public Collection List(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long data) {
        return null;
    }


    private Integer getEmailCount(String email) {
        return jdbcTemplate.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private String getVerificationUrl(String key, String type){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key ).toUriString();
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

}
