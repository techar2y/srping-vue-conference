package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long>
{
    Presentation getById(Long id);

}
