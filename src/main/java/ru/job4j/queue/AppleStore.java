package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        int size = queue.size();
        for (int i = 1; i <= size; i++) {
            if (i == count) {
                break;
            }
            queue.poll();
        }
        return queue.poll().name();
    }

    public String getFirstUpsetCustomer() {
        int size = queue.size();
        for (int i = 1; i <= size; i++) {
            if (i > count) {
                break;
            }
            queue.poll();
        }
        return queue.poll().name();
    }
}
