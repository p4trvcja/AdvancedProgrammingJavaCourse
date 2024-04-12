package org.example;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();
    ParagraphWithList() {
        super("");
    }
    ParagraphWithList setContent(String content) {
        this.content = content;
        return this;
    }

    ParagraphWithList addListItem(String txt) {
        list.addItem(txt);
        return this;
    }
    void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>\n", content);
        list.writeHTML(out);
    }

}
