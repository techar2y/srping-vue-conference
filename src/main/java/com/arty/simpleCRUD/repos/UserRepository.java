package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    Page<User> findUserByLoginContaining(String login, Pageable paging);

    User getUserById(Long id);
}
