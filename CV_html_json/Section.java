package org.example;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();
    Section(String sectionTitle) {
        this.title = sectionTitle;
    }

    Section setTitle(String title) {
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText) {
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    Section addParagraph(Paragraph p) {
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<section>\n");
        out.printf("<h2>%s</h2>\n", title);
        for(Paragraph p : paragraphs)
            p.writeHTML(out);

        out.printf("</section>\n");
    }
}
