package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BranchRepository extends JpaRepository<Branch, Long>
{
}
