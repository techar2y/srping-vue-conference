package com.arty.simpleCRUD.domains;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String login;

    private String email;

    private String role;

    public User()
    {

    }

    public User(String fullName, String login, String email, String role)
    {
        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.role = role;
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

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }
}
