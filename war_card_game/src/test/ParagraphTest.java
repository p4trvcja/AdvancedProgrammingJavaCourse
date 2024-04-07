package org.example;

import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ParagraphTest {

    @org.junit.Test
    public void testWriteHTML() {
        Paragraph paragraph = new Paragraph("paragraph test");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        paragraph.writeHTML(ps);
        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }

        Assert.assertTrue(result.contains("<p>"));
        Assert.assertTrue(result.contains("</p>"));

    }
}