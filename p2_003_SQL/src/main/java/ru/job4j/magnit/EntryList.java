package ru.job4j.magnit;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "entries")
public class EntryList {

    @XmlElement(name = "entry")
    private List<Entry> entryList;

    public void set(List<Entry> list) {
        entryList = list;
    }

    public List<Entry> entryList() {
        return entryList;
    }
}
