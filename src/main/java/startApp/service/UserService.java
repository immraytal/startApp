/*
 * Developed by Antonio112009 on 16/06/19 18:16
 * Last Modified 15/06/19 18:51
 * Copyright (c) 2019. All rights reserved
 *
 */

package startApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startApp.entities.Role;
import startApp.entities.User;
import startApp.repository.RoleRepository;
import startApp.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void saveUser(User user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role userRole = roleRepository.findByRole("USER");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            user.setActive(true);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
            }
        logger.info("User " + user.getId() + " has been saved in DB");
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {return userRepository.findAll();}

    public void deleteByUsername(String username) {
        try {
            userRepository.deleteByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }
        logger.info("User has been deleted");
    }
}
