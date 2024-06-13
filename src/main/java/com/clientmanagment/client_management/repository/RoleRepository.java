package com.clientmanagment.client_management.repository;

import com.clientmanagment.client_management.model.Role;

import java.util.Collection;

public interface RoleRepository<T extends Role> {
    T create(T data);
    Collection<T> List(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long data);

    void addRoleToUser(Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);
}
