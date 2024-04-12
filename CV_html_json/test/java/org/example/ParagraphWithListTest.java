package org.example;

import junit.framework.TestCase;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ParagraphWithListTest {

    @org.junit.Test
    public void testWriteHTML() {
        ParagraphWithList paragraph = new ParagraphWithList();
        paragraph.setContent("paragraph with list test");
        paragraph.addListItem("item test");
        paragraph.addListItem("2nd item test");

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
        Assert.assertTrue(result.contains("<ul>"));
        Assert.assertTrue(result.contains("</ul>"));
        Assert.assertTrue(result.contains("<li>"));
        Assert.assertTrue(result.contains("</li>"));


    }
}