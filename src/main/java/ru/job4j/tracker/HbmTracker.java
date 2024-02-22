package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = true;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", item.getCreated())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            rsl = false;
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = true;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            rsl = false;
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        return session.createQuery("from Item", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        return session.createQuery("from Item where name = :fKey", Item.class)
                .setParameter("fKey", "%" + key + "%")
                .getResultList();
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery("from Item as i where i.id = :fId", Item.class)
                .setParameter("fId", id);
        return query.uniqueResult();
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}