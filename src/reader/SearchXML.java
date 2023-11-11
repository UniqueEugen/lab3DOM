package reader;

import animalsXML.Entity;
import myLibrary.console.Console;
import myLibrary.input.Scan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SearchXML {

    public static void search() throws ParserConfigurationException, SAXException, IOException {


        File file = new File("E:/ПСП/DOM.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Console.log("Enter animal id : ");
        String id = Scan.stringScan();
        Entity animal = getEmployee(document, id);
        if (animal == null) {
            Console.log("Employee not exist with id = " + id);
        } else {
            Console.log(animal);
        }
    }

    private static Entity getEmployee(Document document, String id) {

        NodeList list = document.getElementsByTagName("animal");
        int length = list.getLength();
        Entity animal=null;
        String type, subspecies, kind, description, anId;
        double price;
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (String.valueOf(id).equals(element.getAttribute("id"))) {
                    animal = new Entity();
                    anId = element.getAttribute("id");
                    animal.setId(anId);
                    type = element.getElementsByTagName("type").item(0).getTextContent();
                    animal.setType(type);
                    subspecies = element.getElementsByTagName("subspecies").item(0).getTextContent();
                    animal.setSubspecies(subspecies);
                    kind = element.getElementsByTagName("kind").item(0).getTextContent();
                    animal.setKind(kind);
                    price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
                    animal.setPrice(price);
                    description = element.getElementsByTagName("description").item(0).getTextContent();
                    animal.setDescription(description);
                }
            }
        }

        return animal;
    }

}
