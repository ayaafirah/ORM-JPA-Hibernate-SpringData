package com.aya.jee2.service;

import com.aya.jee2.entities.Role;
import com.aya.jee2.entities.User;
import com.aya.jee2.repository.RoleRepository;
import com.aya.jee2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user=this.findUserByUserName(userName);
        Role role=this.findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authentificate(String userName, String password) {
        User user=userRepository.findByUserName(userName);

        if(user==null)  throw new RuntimeException("Bad credentials");

        if(user.getPassword().equals(password))
            return user;

        throw new RuntimeException("Bad credentials");
    }
}
