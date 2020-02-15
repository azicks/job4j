package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {
    public static final String XMLSRC = "entries.xml";
    public static final String XSL = "xsl.xml";
    public static final String XMLDST = "converted.xml";
    private int sum;

    public static void main(String[] args) {
        ConvertXSQT c = new ConvertXSQT();
        StoreSQL s = new StoreSQL(new Config());
        StoreXML x = new StoreXML();
        s.init();
        s.generate(1_00_000);
        x.save(s.load(), XMLSRC);
        c.convert(XMLSRC, XMLDST, XSL);
        try {
            System.out.println(c.parse(XMLDST));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int parse(String src) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {

                if (qName.equals("entry")) {
                    String id = attributes.getValue("id");
                    ConvertXSQT.this.sum += Integer.parseInt(id);
                }
            }
        };
        parser.parse(src, handler);
        return ConvertXSQT.this.sum;
    }

    public void convert(String source, String dest, String scheme) {
        this.convert(new File(source), new File(dest), scheme);
    }

    public void convert(File source, File dest, String scheme) {
        try (InputStream xsl = this.getClass().getClassLoader().getResourceAsStream(scheme);
             FileInputStream xml = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(dest)) {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsl));
            transformer.transform(new StreamSource(xml), new StreamResult(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
