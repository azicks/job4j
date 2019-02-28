package ru.job4j.bank;

import java.util.*;

public class Bank {

    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (user != null) {
            this.users.putIfAbsent(user, new ArrayList<>());
        }
    }

    public void deleteUser(User user) {
        if (user != null) {
            this.users.remove(user);
        }
    }

    public void addAccountToUser(String passport, Account account) {
        List<Account> accounts = getUserAccounts(passport);
        if (accounts != null) {
            if (getAccount(accounts, account.getRequisites()) == null) {
                accounts.add(account);
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        List<Account> accounts = getUserAccounts(passport);
        if (account != null) {
            for (int i = 0; i != accounts.size(); i++) {
                if (account.getRequisites().equals(accounts.get(i).getRequisites())) {
                    accounts.remove(i);
                    break;
                }
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        return this.users.get(new User(passport));
    }

    private Account getAccount(String passport, String requisites) {
        return getAccount(getUserAccounts(passport), requisites);
    }

    private Account getAccount(List<Account> accounts, String requisites) {
        Account result = null;
        for (int i = 0; i != accounts.size(); i++) {
            if (accounts.get(i).getRequisites().equals(requisites)) {
                result = accounts.get(i);
                break;
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccount(srcPassport, srcRequisite);
        if (srcAccount != null && srcAccount.getValue() >= amount) {
            Account dstAccount = getAccount(dstPassport, dstRequisite);
            if (dstAccount != null) {
                dstAccount.addValue(amount);
                srcAccount.subValue(amount);
                result = true;
            }
        }
        return result;
    }
}
