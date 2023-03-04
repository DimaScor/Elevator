package org.myproject.services.interfaces;

import org.myproject.entities.Role;
import org.myproject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    User findUserById(Long l);
    User findUserByUsername(String username);
    Page<User> getAllUsers(int pageNumber, int pageSize);

    Set<Role> roles(String username);

}
