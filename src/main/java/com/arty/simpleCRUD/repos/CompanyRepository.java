package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends JpaRepository<Company, Long>
{
}
