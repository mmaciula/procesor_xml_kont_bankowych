package org.example.marmac;

import org.example.marmac.model.Accounts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DocumentParser {
    private JAXBContext jaxbContext;

    public DocumentParser() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Accounts.class);
    }

    public Accounts parseToJavaObject(File file) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Accounts) unmarshaller.unmarshal(file);
    }

    public void parseToXML(Accounts accounts, File file) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(accounts, file);
    }
}
