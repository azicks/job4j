package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private final Map<User, List<Account>> users = new HashMap<>();

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
        if (account != null && accounts != null) {
            accounts.removeAll(
                    accounts.stream().filter(acc -> acc.getRequisites().equals(account.getRequisites()))
                    .collect(Collectors.toList()));
        }
    }

    public List<Account> getUserAccounts(String passport) {
       Optional<List<Account>> accounts = this.users.entrySet().stream()
               .filter(user -> user.getKey().getPassport().equals(passport))
               .map(Map.Entry::getValue).findFirst();
        return accounts.orElse(null);
    }

    private Account getAccount(String passport, String requisites) {
        return getAccount(getUserAccounts(passport), requisites);
    }

    private Account getAccount(List<Account> accounts, String requisites) {
        Optional<Account> account = accounts.stream()
                .filter(acc -> acc.getRequisites().equals(requisites)).findFirst();
        return account.orElse(null);
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
