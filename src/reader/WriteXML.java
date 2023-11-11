package reader;

import animalsXML.Entity;
import myLibrary.console.Console;
import myLibrary.input.Scan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class WriteXML {

    public static void write() throws  TransformerException {
        Console.log("Enter name of the file: ");
        String str = Scan.unoWordScan();
        String filePath = "E:/ПСП/XML" + str+".xml";
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = builder.newDocument();
        Element root = document.createElement(str);
        root.setAttribute("id", str);
        document.appendChild(root);
        Console.log("Enter how many animals you want to add? : ");
        int count = Scan.intScan();
        String type, subspecies, kind, description;
        double price;
        Element element = null;
        for (int i = 1; i <= count; i++) {
            Console.log("******* Animal : " + i + " ********");
            Console.log("Enter type : ");
            type = Scan.stringScan();
            System.out.println("Enter subspecies : ");
            subspecies = Scan.stringScan();
            Console.log("Enter price : ");
            price = Scan.doubleScan();
            Console.log("Enter kind : ");
            kind = Scan.stringScan();
            Console.log("Enter description : ");
            description = Scan.stringScan();
            Entity animal = new Entity();
            animal.setType(type);
            animal.setSubspecies(subspecies);
            animal.setKind(kind);
            animal.setPrice(price);
            animal.setDescription(description);
            element = getEmployeeNode(animal, UUID.randomUUID(), document);
            root.appendChild(element);
        }

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
        Console.log("Animals has been added successfully.");




}

    private static Element getEmployeeNode(Entity employee, UUID id,Document document) {

        Element element = document.createElement("animal");
        element.setAttribute("id", String.valueOf(id));
        Element type = getPropertyNode("type", document, employee.getType());
        element.appendChild(type);
        Element subspecies = getPropertyNode("subspecies", document,employee.getSubspecies());
        element.appendChild(subspecies);
        Element kind = getPropertyNode("kind", document,String.valueOf(employee.getKind()));
        element.appendChild(kind);
        Element price = getPropertyNode("price", document, String.valueOf(employee.getPrice()));
        element.appendChild(price);
        Element description = getPropertyNode("description", document, employee.getDescription());
        element.appendChild(description);
        return element;
    }

    private static Element getPropertyNode(String property, Document document,String value) {

        Element element = document.createElement(property);
        element.setTextContent(value);
        return element;
    }

}

