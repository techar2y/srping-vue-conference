package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Long>
{

}
