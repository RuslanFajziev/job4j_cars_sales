package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import ru.job4j.model.User;

import java.util.function.Function;

public class UserRepository {
    private SessionFactory sf;

    public UserRepository() {
        StandardServiceRegistry registry = RegistryRepository.instOf();
        this.sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final UserRepository INST = new UserRepository();
    }

    public static UserRepository instOf() {
        return UserRepository.Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void save(User user) {
        tx(session -> session.save(user));
    }

    public void update(User user) {
        tx(session -> {
            session.update(user);
            return true;
        });
    }

    public User find(String email) {
       return tx(session -> {
            User result = (User) session.createQuery("from ru.job4j.model.User where email = :emailP")
                .setParameter("emailP", email)
                .uniqueResult();
            return result;
        });
    }
}