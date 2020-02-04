package org.example.marmac;

import org.example.marmac.model.Accounts;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            DocumentParser parser = new DocumentParser();

            File inputFile = new File("input.xml");
            Accounts accounts = parser.parseToJavaObject(inputFile);

            if (Optional.ofNullable(accounts.getAccounts()).isPresent()) {
                AccountsManager accountsManager = new AccountsManager();
                accountsManager.manage(accounts);
            }

            File outputFile = new File("output.xml");
            parser.parseToXML(accounts, outputFile);
        } catch (JAXBException exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }
}
