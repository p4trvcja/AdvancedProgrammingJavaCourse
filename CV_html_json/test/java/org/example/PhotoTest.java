package org.example;

import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

public class PhotoTest {
    @org.junit.Test
    public void writeHTML() {
        String imageUrl = "photo-test.png";
        int height = 42, width = 42;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Photo(imageUrl, height, width).writeHTML(ps);
        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }

        Assert.assertTrue(result.contains("<img"));
        Assert.assertTrue(result.contains("/>"));
        Assert.assertTrue(result.contains("src="));
        Assert.assertTrue(result.contains(imageUrl));
    }
}