package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("artur-sar@yandex.ru", "Artur Stepanyan");
        map.put("ivanov@mail.ru", "Oleg Ivanov");
        map.put("parsentev@yandex.ru", "Arsen Paratov");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
