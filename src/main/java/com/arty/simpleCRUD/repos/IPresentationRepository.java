package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Presentation;
import com.arty.simpleCRUD.domains.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPresentationRepository extends JpaRepository<Presentation, Long>
{
    Presentation getById(Long id);

    Page<Presentation> findPresentationByTitleContaining(String searchStr, Pageable paging);

    Page<Presentation> findPresentationsByRoom(Room room, Pageable paging);
}
