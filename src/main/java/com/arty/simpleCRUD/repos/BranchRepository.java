package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long>
{

    @Query(value = "SELECT * FROM branches b JOIN companies c ON b.company_id = c.id WHERE c.name = :name",
    nativeQuery = true)
    List<Branch> findBranchByCompaniesName(@Param("name") String name);
}
