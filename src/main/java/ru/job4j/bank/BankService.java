package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает модель банка
 * @author Artur Stepanyan
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных о клиентах осуществляется в коллекции типа Map(HashMap),
     * ключом является объект User (клиент банка),
     * List<Account> - список счетов клиента банка
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового пользователя в систему,
     * если такого пользователя еще нет в системе
     * @param user - пользователь, которого нужно добавить
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет удалить пользователя из системы
     * @param passport - паспорт пользователя, которого нужно удалить
     * @return возвращает true, если удаление прошло успешно,
     * или false в противном случае.
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод позволяет добавить новый счет к пользователю,
     * если такого счета у пользователя еще нет
     * @param passport - паспорт пользователя
     * @param account - новый счёт пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> acc = getAccounts(user);
            if (!(acc.contains(account))) {
                acc.add(account);
            }
        }
    }

    /**
     * Метод позволяет найти пользователя по номеру паспорта
     * @param passport - паспорт пользователя
     * @return возвращает объект пользователя
     * или null, если пользователь не найден
     */
    public User findByPassport(String passport) {
       return users.keySet()
                .stream()
                .filter(u -> passport.equals(u.getPassport()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод позволяет найти счёт пользователя по паспорту и реквизитам счёта
     * @param passport - паспорт пользователя
     * @param requisite - реквизиты счёта
     * @return возвращает счёт или null, если счёт или пользователь не были найдены
     */
    public Account findByRequisite(String passport, String requisite) {
        User u = findByPassport(passport);
        if (u != null) {
            return users.get(u)
                    .stream()
                    .filter(a -> requisite.equals(a.getRequisite()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод предназначен для перечисления денег с одного счёта на другой счёт
     * @param srcPassport - паспорт отправителя
     * @param srcRequisite - счёт отправителя
     * @param destPassport - паспорт получателя
     * @param destRequisite - счёт получателя
     * @param amount - сумма перевода
     * @return возвращает true, если перевод совершен успешно или false,
     * если любой из счетов не найден или не хватает денег на счёте srcAccount (с которого переводят)
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account src = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (src != null && dest != null && src.getBalance() >= amount) {
            src.setBalance(src.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод позволяет получить список счетов пользователя
     * @param user - пользователь
     * @return возвращает список всех счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
