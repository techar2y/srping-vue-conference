package com.arty.simpleCRUD.domains;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private String date;

    private String subject;

    private String description;

    private Integer room;

    private String name;


    public Schedule ()
    {

    }

    public Schedule (String date, String subject, String description, Integer room, String name)
    {
        this.date = date;
        this.subject = subject;
        this.description = description;
        this.room = room;
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

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getSubject ()
    {
        return subject;
    }

    public void setSubject (String subject)
    {
        this.subject = subject;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public Integer getRoom ()
    {
        return room;
    }

    public void setRoom (Integer room)
    {
        this.room = room;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}
