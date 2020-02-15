package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class StoreXML {

    public void save(List<Entry> list, String outFile) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(outFile))) {
            EntryList entryList = new EntryList();
            entryList.set(list);
            JAXBContext jaxbContext = JAXBContext.newInstance(EntryList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entryList, out);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
