package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String s) {
        this.title = s;
    }

    Document setTitle(String title) {
        this.title = title;
        return this;
    }
    Document setPhoto(String photoUrl, int height, int width) {
        this.photo = new Photo(photoUrl, height, width);
        return this;
    }

    Section addSection(String sectionTitle) {
        sections.add(new Section(sectionTitle));
        return sections.getLast();
    }
    Document addSection(Section section) {
        sections.add(section);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>%s</h1>", title);
        photo.writeHTML(out);
        for(Section s : sections)
            s.writeHTML(out);
        out.println("</body>");
        out.println("</html>");
    }

    String toJson() {
        RuntimeTypeAdapterFactory<Paragraph> adapter = RuntimeTypeAdapterFactory
                .of(Paragraph.class)
                .registerSubtype(Paragraph.class)
                .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapter)
                .setPrettyPrinting()
                .create();
        return gson.toJson(this);
    }

    static Document fromJson(String jsonString) {
        RuntimeTypeAdapterFactory<Paragraph> adapter = RuntimeTypeAdapterFactory
                .of(Paragraph.class)
                .registerSubtype(Paragraph.class)
                .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapter)
                .create();
        return gson.fromJson(jsonString, Document.class);
    }
}
