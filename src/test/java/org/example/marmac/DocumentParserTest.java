package org.example.marmac;

import org.example.marmac.model.Account;
import org.example.marmac.model.Accounts;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DocumentParserTest {
    private static DocumentParser parser;
    private static File outputFile;

    @BeforeClass
    public static void setup() throws JAXBException {
        parser = new DocumentParser();
    }

    @Test
    public void parseToJavaObjectTest() throws JAXBException {
        File file = new File("src/test/resources/testAccounts.xml");
        Accounts accounts = parser.parseToJavaObject(file);
        System.out.println(accounts);
    }

    @Test
    public void parseToXmlTest() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        List<Account> list = new ArrayList<>( List.of(new Account("PL61109010140000071219812875", "test", "PL", "15.00", "2034-01-01")));
        Accounts accounts = new Accounts();
        accounts.setAccounts(list);
        outputFile = new File("src/test/resources/testOutput.xml");
        parser.parseToXML(accounts, outputFile);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(outputFile);

        assertEquals("test", document.getElementsByTagName("name").item(0).getTextContent());
        assertEquals("PL", document.getElementsByTagName("currency").item(0).getTextContent());
        assertEquals("15.00", document.getElementsByTagName("balance").item(0).getTextContent());
        assertEquals("2034-01-01", document.getElementsByTagName("closingDate").item(0).getTextContent());
    }

    @AfterClass
    public static void cleanup() {
        if (outputFile != null) {
            outputFile.delete();
        }
    }
}
