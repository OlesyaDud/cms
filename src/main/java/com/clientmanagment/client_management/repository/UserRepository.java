package com.clientmanagment.client_management.repository;

import com.clientmanagment.client_management.model.User;

import java.util.Collection;

public interface UserRepository<T extends User> {
    T create(T data);
    Collection<T> List(int page, int pageSize);
    T get(Long id);
    T update(T data);
//    returns true or false if successfull or not
    Boolean delete(Long data);
 }
