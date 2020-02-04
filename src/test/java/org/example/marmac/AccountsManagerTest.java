package org.example.marmac;

import org.example.marmac.model.Account;
import org.example.marmac.model.Accounts;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountsManagerTest {
    private List<Account> accountsList;
    private AccountsManager accountsManager = new AccountsManager();
    private Accounts accounts = new Accounts();

    @Before
    public void setup() {
        accountsList = new ArrayList<>(
                List.of(new Account("PL61109010140000071219812875", "first", "PLN", "15.00", "2034-01-01"))
        );
        accounts.setAccounts(accountsList);
    }

    @Test
    public void wrongCurrencyTest() {
        Account wrongCurrency = new Account("PL61109010140000071219812875", "test", "PL", "15.00", "2034-01-01");
        accounts.addAccount(wrongCurrency);

        assertEquals(1, accountsManager.manage(accounts).getAccounts().size());
    }

    @Test
    public void negativeBalanceTest() {
        Account negativeBalance = new Account("PL61109010140000071219812875", "test", "PLN", "-5.00", "2034-01-01");
        Account zeroBalance = new Account("PL61109010140000071219812875", "test", "PLN", "0", "2034-01-01");
        accounts.addAccount(negativeBalance);
        accounts.addAccount(zeroBalance);

        assertEquals(2, accountsManager.manage(accounts).getAccounts().size());
    }

    @Test
    public void closingDateTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());

        Account earlierDate = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", "2020-01-01");
        Account theSameDate = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", currentDate);
        accounts.addAccount(earlierDate);
        accounts.addAccount(theSameDate);

        assertEquals(2, accountsManager.manage(accounts).getAccounts().size());
    }

    @Test
    public void incorrectIbanTest() {
        Account wrongIban = new Account("PLN61109010140000071219812875", "test", "PLN", "15.00", "2034-01-01");
        accounts.addAccount(wrongIban);

        assertEquals(1, accountsManager.manage(accounts).getAccounts().size());
    }

    @Test
    public void sortingTest() {
        Account testAccount = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", "2034-01-01");
        Account aaaAccount = new Account("PL61109010140000071219812875", "aaa", "PLN", "15.00", "2034-01-01");
        accounts.addAccount(testAccount);
        accounts.addAccount(aaaAccount);

        List<Account> actual = accountsManager.manage(accounts).getAccounts();

        assertEquals("aaa", actual.get(0).getName());
        assertEquals("first", actual.get(1).getName());
        assertEquals("test", actual.get(2).getName());
    }

    @Test
    public void onlyGoodAccountTest() {
        assertEquals(1, accountsManager.manage(accounts).getAccounts().size());
    }

    @Test
    public void onlyWrongAccountTest() {
        List<Account> wrongBalance = new ArrayList<>(
                List.of(new Account("PL61109010140000071219812875", "test", "PLN", "-1.00", "2034-01-01"),
                        new Account("PL61109010140000071219812875", "test", "PLN", "-99.00", "2034-01-01"))
        );
        accounts.setAccounts(wrongBalance);

        assertTrue(Optional.ofNullable(accountsManager.manage(accounts).getAccounts()).isPresent());
    }
}
