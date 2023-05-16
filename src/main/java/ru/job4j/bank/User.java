package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банка
 * @author Artur Stepanyan
 * @version 1.0
 */
public class User {
    /**
     * Поле описывает номер паспорта типа String
     */
    private String passport;
    /**
     * Поле описывает имя пользователя типа String
     */
    private String username;

    /**
     * Конструктор для создания объекта пользователя с инициализацией паспорта и имени
     * @param passport - паспорт пользователя
     * @param username - имя пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод переопределяет equals()
     * @param o - объект для сравнения (номер паспорта)
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
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Метод переопределяет hashCode()
     * @return возвращает значение хеш-кода по номеру паспорта
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
