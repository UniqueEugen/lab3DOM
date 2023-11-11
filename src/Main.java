import myLibrary.console.Console;
import myLibrary.input.Scan;
import org.xml.sax.SAXException;
import reader.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        int choise;
        boolean flag = true;
        while (flag) {
            try {
                Console.log("Что хотите сделать?\n1.Вывести данные\n2.Добавить\n3.Удалить\n4.Добавить " +
                        "новую группу\n" + "5.Найти животное\n6.Выйти");
                choise = Scan.intScan();
                switch (choise) {
                    case 1:
                        XMLReader reader = new XMLReader();
                        reader.readXML();
                        reader.toString();
                        break;
                    case 2:
                        AddAnimal.add();
                        break;
                    case 3:
                        DeleteAnimal.delete();
                        break;
                    case 4:
                        WriteXML.write();
                        break;
                    case 5:
                        SearchXML.search();
                        break;
                    case 6:
                        flag = false;
                        break;
                    default:
                        Console.log("Error input");
                        break;
                }
            } catch (Exception e) {
                Console.log(e);
            }
        }
    }
}