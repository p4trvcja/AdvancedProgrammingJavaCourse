package org.example;

import java.io.PrintStream;

public class ListItem {
    String content;
    ListItem(String text) {
        this.content = text;
    }
    void setText(String t) {
        this.content = t;
    }
    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", content);
    }
}