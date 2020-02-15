package ru.job4j.magnit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MagnitTest {

    @Test
    public void whenGenerate10EntriesThenSumIs() {
        int result = 0;
        ConvertXSQT c = new ConvertXSQT();
        StoreSQL s = new StoreSQL(new Config());
        StoreXML x = new StoreXML();
        s.init();
        s.generate(10);
        x.save(s.load(), ConvertXSQT.XMLSRC);
        c.convert(ConvertXSQT.XMLSRC, ConvertXSQT.XMLDST, ConvertXSQT.XSL);
        try {
            result = c.parse(ConvertXSQT.XMLDST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(55, result);
    }
}
