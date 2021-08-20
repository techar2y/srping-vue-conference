package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.RefreshToken;
import com.arty.simpleCRUD.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    Integer deleteByUser(User user);

}
