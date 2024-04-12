package org.example;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items = new ArrayList<>();

    void addItem(String text) {
        items.add(new ListItem(text));
    }
    void writeHTML(PrintStream out) {
        out.println("<ul>");
        for(ListItem i : items)
            i.writeHTML(out);
        out.println("</ul>");
    }
}
