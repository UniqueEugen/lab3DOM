package reader;

import animalsXML.Entity;
import myLibrary.console.Console;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    private List<Entity> animals;
    public void readXML() {

        File file = new File("E:/ПСП/DOM.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            String rootNode = document.getDocumentElement().getNodeName();
            System.out.println("Root Element : " + rootNode);
            animals = getAnimalData(document);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private static List<Entity> getAnimalData(Document document) {
        List<Entity> entities = null;
        NodeList list = document.getElementsByTagName("animal");
        entities = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Entity animal = getAnimal(element);
                entities.add(animal);
            }
        }
        return entities;
    }

    private static Entity getAnimal(Element element) {

        String id=element.getAttribute("id");
        String type = getAnimalDetails(element, "type");
        String subspecies = getAnimalDetails(element, "subspecies");
        double price = Double.parseDouble(getAnimalDetails(element, "price"));
        String kind = getAnimalDetails(element, "kind");
        String description = getAnimalDetails(element, "description");
        Entity animal = new Entity();
        animal.setId(id);
        animal.setType(type);
        animal.setDescription(description);
        animal.setKind(kind);
        animal.setSubspecies(subspecies);
        animal.setPrice(price);
        return animal;
    }

    private static String getAnimalDetails(Element element, String property) {
        String value = element.getElementsByTagName(property).item(0).getTextContent();
        return value;
    }
    @Override
    public String toString(){
        animals.forEach(animal->animal.toString());
        return null;
    }

}
