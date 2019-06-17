/*
 * Developed by Antonio112009 on 16/06/19 18:16
 * Last Modified 15/06/19 18:51
 * Copyright (c) 2019. All rights reserved
 *
 */

package startApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import startApp.entities.Role;
import startApp.entities.User;
import startApp.repository.RoleRepository;
import startApp.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setActive(true);
        userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {return userRepository.findAll();}

    public void deleteByUsername(String username) {userRepository.deleteByUsername(username);}
}
