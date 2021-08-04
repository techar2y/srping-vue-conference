package com.arty.simpleCRUD.domains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer number;

    @OneToMany(mappedBy = "room")
    private List<Presentation> presentations;

    public Room()
    {

    }

    public Room(Long id, String name, Integer number, List<Presentation> presentations) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.presentations = presentations;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public void setPresentations(List<Presentation> presentations)
    {
        this.presentations = presentations;
    }
}
