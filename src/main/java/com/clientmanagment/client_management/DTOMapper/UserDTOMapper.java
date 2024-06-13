package com.clientmanagment.client_management.DTOMapper;

import com.clientmanagment.client_management.dto.UserDTO;
import com.clientmanagment.client_management.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

//    convert to DTO  -- return user dto
    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

//    convert to Model User  -- return user User model
    public static User toUser(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
