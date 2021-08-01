package com.arty.simpleCRUD.domains;

import javax.persistence.*;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id"/*, referencedColumnName = "id"*/)
    private Company company;


    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getBranchName ()
    {
        return branchName;
    }

    public void setBranchName (String branchName)
    {
        this.branchName = branchName;
    }

    public Company getCompany ()
    {
        return company;
    }

    public void setCompany (Company company)
    {
        this.company = company;
    }
}
