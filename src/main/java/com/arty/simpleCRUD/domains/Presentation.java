package com.arty.simpleCRUD.domains;

import javax.persistence.*;

@Entity
@Table(name = "presentations")
public class Presentation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String description;

    private String title;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String lasts;

    public Presentation()
    {

    }

    public Presentation(String subject, String description, String title, Room room, String lasts) {
        this.subject = subject;
        this.description = description;
        this.title = title;
        this.room = room;
        this.lasts = lasts;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLasts ()
    {
        return lasts;
    }

    public void setLasts (String lasts)
    {
        this.lasts = lasts;
    }
}
