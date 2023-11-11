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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class AddAnimal {

    public static void add() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        File file = new File("E:/ПСП/DOM.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Console.log("Enter type : ");
        String type = Scan.stringScan();
        Console.log("Enter subspecies : ");
        String subspecies = Scan.stringScan();
        Console.log("Enter kind : ");
        String kind = Scan.stringScan();
        Console.log("Enter price : ");
        double price = Scan.doubleScan();
        Console.log("Enter description : ");
        String description = Scan.stringScan();
        List<String> list = Arrays.stream(description.split(" ")).toList();
        int i = 0;
        description="";
        for(String word: list){
            if(i==7) {description+=word+"` "; i=0;}
            else description+=word+" ";
            i++;
        }

        Console.log(description);
        Entity animal = new Entity();
        animal.setType(type);
        animal.setSubspecies(subspecies);
        animal.setKind(kind);
        animal.setPrice(price);
        animal.setDescription(description);
        animal.setId(UUID.randomUUID().toString());
        Element element = getAnimalNode(animal, document);
        document.getElementsByTagName("animals").item(0).appendChild(element);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult sResult = new StreamResult(file);
        transformer.transform(source, sResult);
        Console.log("Animal has been added successfully.");


    }

    private static Element getAnimalNode(Entity animal, Document document) {

        Element element = document.createElement("animal");
        NodeList list=document.getElementsByTagName("animal");
        element.setAttribute("id", String.valueOf(animal.getId()));

        Element type = getPropertyNode("type", document, animal.getType());
        element.appendChild(type);
        Element subspecies = getPropertyNode("subspecies", document, animal.getSubspecies());
        element.appendChild(subspecies);
        Element kind = getPropertyNode("kind", document, String.valueOf(animal.getKind()));
        element.appendChild(kind);
        Element price = getPropertyNode("price", document, String.valueOf(animal.getPrice()));
        element.appendChild(price);
        Element description = getPropertyNode("description", document, animal.getDescription());
        element.appendChild(description);
        return element;
    }

    private static Element getPropertyNode(String property, Document document, String value) {

        Element element = document.createElement(property);
        element.setTextContent(value);
        return element;
    }

}
