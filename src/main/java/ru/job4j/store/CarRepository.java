package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import ru.job4j.model.Car;

import java.util.Collection;
import java.util.function.Function;

public class CarRepository {
    private SessionFactory sf;

    public CarRepository() {
        StandardServiceRegistry registry = RegistryRepository.instOf();
        this.sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final CarRepository INST = new CarRepository();
    }

    public static CarRepository instOf() {
        return CarRepository.Lazy.INST;
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

    public void save(Car car) {
        tx(session -> session.save(car));
    }

    public void update(Car car) {
        tx(session -> {
            session.update(car);
            return true;
        });
    }

    public Car find(String model) {
        return tx(session -> {
            Car result = (Car) session.createQuery("from ru.job4j.model.Car where model = :modelP")
                    .setParameter("modelP", model)
                    .uniqueResult();
            return result;
        });
    }

    public Collection<String> findBrandAll() {
        return tx(session -> {
            Collection<String> result = session.createQuery("select DISTINCT brand from ru.job4j.model.Car").list();
            return result;
        });
    }
}