package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета
 * @author Artur Stepanyan
 * @version 1.0
 */
public class Account {
    /**
     * Поле описывает реквизиты (номер) счёта типа String
     */
    private String requisite;
    /**
     * Поле описывает баланс счёта типа double
     */
    private double balance;

    /**
     * Конструктор для создания объекта счёта с инициализацией баланса и реквизитов
     * @param requisite - реквизиты (номер) счёта типа String
     * @param balance - баланс счёта типа double
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод переопределяет equals()
     * @param o - объект для сравнения (поле реквизиты)
     * @return возвращает true, если этот объект совпадает с аргументом,
     * false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Метод переопределяет hashCode()
     * @return возвращает значение хеш-кода по полю реквизиты
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}