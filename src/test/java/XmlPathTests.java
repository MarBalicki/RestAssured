import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import org.testng.annotations.Test;

import java.util.List;

public class XmlPathTests {

    @Test
    public void testXmlPath() {
        String xml = "<filmy>\n" +
                "   <film gatunek=\"komedia\">\n" +
                "      <id>1</id>\n" +
                "      <nazwa>Forrest Gump</nazwa>\n" +
                "      <ocena>8</ocena>\n" +
                "   </film>\n" +
                "   <film gatunek=\"komedia\">\n" +
                "      <id>2</id>\n" +
                "      <nazwa>American Pie</nazwa>\n" +
                "      <ocena>7</ocena>\n" +
                "   </film>\n" +
                "   <film gatunek=\"dramat\">\n" +
                "      <id>3</id>\n" +
                "      <nazwa>Zielona mila</nazwa>\n" +
                "      <ocena>9</ocena>\n" +
                "   </film>\n" +
                "</filmy>";

        String firstFilmName = XmlPath.from(xml).get("filmy.film.nazwa[0]");
        List<String> names = XmlPath.from(xml).getList("filmy.film.nazwa");
        String genre = XmlPath.from(xml).get("filmy.film[0].@gatunek");
        List<Node> films = XmlPath.from(xml).get("filmy.film.findAll{element -> return element}");
        String filmName = films.get(2).get("nazwa").toString();
        List<Node> comedy = XmlPath.from(xml).get("filmy.film.findAll{film -> film.@gatunek=='komedia'}");
        Node filmWithNote9 = XmlPath.from(xml).get("filmy.film.find{film -> def ocena = film.ocena; ocena=='9'}");
        List<Node> filmsWithNoteMoreThan7 = XmlPath.from(xml).get("filmy.film.findAll{film -> film.ocena.toFloat() >= 7}");
        List<String> allNames = XmlPath.from(xml).get("filmy.film.collect{it.nazwa}");

    }
}
