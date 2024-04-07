package org.example;

import junit.framework.TestCase;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class SectionTest extends TestCase {

    public void testWriteHTML() {
        Section section = new Section("this is section test");
        section.addParagraph("section paragraph test");
        section.addParagraph("2nd section paragraph test");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        section.writeHTML(ps);
        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }

        Assert.assertTrue(result.contains("<h2>"));
        Assert.assertTrue(result.contains("</h2>"));
        Assert.assertTrue(result.contains("<p>"));
        Assert.assertTrue(result.contains("</p>"));
    }
}