package org.example;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        Document cv = new Document("Jan Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/en/4/4d/Shrek_%28character%29.png", 326, 220);
        cv.addSection("Dane osobowe")
                .addParagraph("Imię: Jan Kowalski")
                .addParagraph("Data urodzenia: 30.02.2003 r.")
                .addParagraph("Adres: ul. Igołomska\n31-982 Kraków")
                .addParagraph("Telefon: 123-456-789")
                .addParagraph("");
        cv.addSection("Wykształcenie")
                .addParagraph("2019-2022 V Liceum im. Augusta Witkowskiego w Krakowie")
                .addParagraph("2022-obecnie Studia Inżynierskie na Akademii Górniczo-Hutniczej w Krakowie")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Przedsiębiorstwa")
                                .addListItem("Administracji")
                                .addListItem("Sztucznej inteligencji")
                                .addListItem("IT - poziom zaawansowany")
                                .addListItem("Prawa")
                                .addListItem("Psychologii")
                                .addListItem("Coachingu")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                                .addListItem("Python")
                );
        cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
        cv.writeHTML(System.out);

        String json = cv.toJson();
        Document deserializedCv = Document.fromJson(json);

        String jsonDeserialized = deserializedCv.toJson();

        System.out.println("Original JSON:");
        System.out.println(json);
        System.out.println("JSON after deserialization:");
        System.out.println(jsonDeserialized);
    }
}