package com.arty.simpleCRUD.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usrs")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String login;

    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "presenters")
    private List<Presentation> presentations;


    public User()
    {

    }

    public User(String fullName, String login, String email, Role role, List<Presentation> presentations)
    {
        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.role = role;
        this.presentations = presentations;
    }

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getFullName ()
    {
        return fullName;
    }

    public void setFullName (String fullName)
    {
        this.fullName = fullName;
    }

    public String getLogin ()
    {
        return login;
    }

    public void setLogin (String login)
    {
        this.login = login;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void setPresentations(List<Presentation> presentations)
    {
        this.presentations = presentations;
    }

    @JsonIgnore
    public List<Presentation> getPresentations ()
    {
        return presentations;
    }
}
