package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BankTest {
    User user1 = new User("passport1");
    User user2 = new User("passport2");
    User user3 = new User("passport3");
    Bank bank = new Bank();

    @Before
    public void addUsersAndAccounts() {
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        bank.addAccountToUser("passport1", new Account("req11"));
        bank.addAccountToUser("passport1", new Account("req12"));
        bank.addAccountToUser("passport2", new Account("req21"));
        bank.addAccountToUser("passport2", new Account("req22"));
        bank.addAccountToUser("passport3", new Account("req31"));
        bank.addAccountToUser("passport3", new Account("req32"));
    }

    @Test
    public void addDuplicateAccountTest() {
        bank.addAccountToUser("passport3", new Account("req32"));
        assertEquals(bank.getUserAccounts("passport3"),
                List.of(new Account("req31"),
                        new Account("req32")));
    }

    @Test
    public void deleteUserTest() {
        bank.deleteUser(new User("passport2"));
        assertNull(bank.getUserAccounts("passport2"));
    }

    @Test
    public void deleteUserAccTest() {
        bank.deleteAccountFromUser("passport3", new Account("req32"));
        assertEquals(bank.getUserAccounts("passport3"), List.of(new Account("req31")));
    }

    @Test
    public void whenTransferMoneyOKThenTrue() {
        bank.addAccountToUser("passport1", new Account("req", 100));
        assertTrue(bank.transferMoney("passport1", "req", "passport2", "req21", 50));
    }

    @Test
    public void whenInsufficientFundsThenFalse() {
        bank.addAccountToUser("passport1", new Account("req", 100));
        assertFalse(bank.transferMoney("passport1", "req", "passport2", "req", 500));
    }

    @Test
    public void whenIncorrectAccountThenFalse() {
        bank.addAccountToUser("passport1", new Account("req", 100));
        assertFalse(bank.transferMoney("passport1", "req", "passport2", "incorrect", 5));
    }
}