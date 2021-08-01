package com.arty.simpleCRUD.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Set<Branch> branches = new HashSet<>();*/

    //@JsonIgnore
    @OneToMany(mappedBy = "company")
    private Set<Branch> branches;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getCompanyName ()
    {
        return companyName;
    }

    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }

    public void setBranches (Set<Branch> branches)
    {
        this.branches = branches;
    }

}
