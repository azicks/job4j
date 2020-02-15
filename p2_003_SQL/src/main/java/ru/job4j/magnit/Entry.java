package ru.job4j.magnit;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Entry")
public class Entry {

    @XmlElement
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private String description;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Entry() {

    }

    public Entry(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
