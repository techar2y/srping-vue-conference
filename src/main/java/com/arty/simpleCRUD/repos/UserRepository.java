package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findUserByLoginContaining(String login);

    User getUserById(Long id);
}
