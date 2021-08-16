package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.ERole;
import com.arty.simpleCRUD.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(ERole name);
}
