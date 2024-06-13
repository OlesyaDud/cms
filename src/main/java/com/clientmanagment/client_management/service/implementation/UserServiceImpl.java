package com.clientmanagment.client_management.service.implementation;

import com.clientmanagment.client_management.DTOMapper.UserDTOMapper;
import com.clientmanagment.client_management.dto.UserDTO;
import com.clientmanagment.client_management.model.User;
import com.clientmanagment.client_management.repository.UserRepository;
import com.clientmanagment.client_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
//    returns UserDTO which is why return is UserDTO
    public UserDTO createUser(User user) {
//        we need to map user to userDto
//        call repo - give user , and map it to dto after
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
