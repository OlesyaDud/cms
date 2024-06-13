package com.clientmanagment.client_management.service;

import com.clientmanagment.client_management.dto.UserDTO;
import com.clientmanagment.client_management.model.User;

public interface UserService {
    UserDTO createUser(User user);

}
