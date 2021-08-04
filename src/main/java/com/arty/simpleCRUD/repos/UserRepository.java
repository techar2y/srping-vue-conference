package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Page<User> findUserByLoginContaining(String login, Pageable paging);

    User getUserById(Long id);

    List<User> findUsersByLoginAndIdNot(String login, Long id);

    List<User> findUsersByEmailAndIdNot(String email, Long id);

}
