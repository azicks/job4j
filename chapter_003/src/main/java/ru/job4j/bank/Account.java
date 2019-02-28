package ru.job4j.bank;

import java.util.Objects;

public class Account {

    private double value;
    private String requisites;

    public Account(String requisites) {
        this.requisites = requisites;
    }

    public Account(String requisites, double amount) {
        this.requisites = requisites;
        this.value = amount;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void addValue(double value) {
        this.value += value;
    }

    public void subValue(double value) {
        this.value += value;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null) {
            if (this.getClass() == o.getClass()) {
                Account a = (Account) o;
                result = this.requisites.equals(a.getRequisites());
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
