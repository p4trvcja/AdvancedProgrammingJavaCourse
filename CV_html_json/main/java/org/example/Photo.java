package org.example;
import java.io.PrintStream;

public class Photo {
    private String url;
    int width;
    int height;
    Photo(String url, int height, int width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\" alt=\"Photo\" height=\"%d\" width=\"%d\"/>\n", url, height, width);
    }
}
