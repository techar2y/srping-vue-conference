package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>
{
}
