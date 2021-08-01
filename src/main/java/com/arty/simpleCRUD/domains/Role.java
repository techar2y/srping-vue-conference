package com.arty.simpleCRUD.domains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role ()
    {

    }

    public Role (String status, String name) {
        this.status = status;
        this.name = name;
    }

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsers (List<User> users)
    {
        this.users = users;
    }
}
