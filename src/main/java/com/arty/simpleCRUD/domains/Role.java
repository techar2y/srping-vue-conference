package com.arty.simpleCRUD.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role ()
    {

    }

    public Role (ERole name) {
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

    public ERole getName()
    {
        return name;
    }

    public void setName(ERole name)
    {
        this.name = name;
    }

    public void setUsers (List<User> users)
    {
        this.users = users;
    }

    @JsonIgnore
    public List<User> getUsers ()
    {
        return users;
    }
}
