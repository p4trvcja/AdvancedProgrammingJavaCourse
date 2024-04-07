package org.example;

import java.io.PrintStream;

public class Paragraph {
    String content;
    Paragraph(String text) {
        this.content = text;
    }
    Paragraph setContent(String text) {
        this.content = text;
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>\n", content);
    }

}
