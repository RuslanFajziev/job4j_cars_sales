package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import ru.job4j.model.Ads;

import java.util.Collection;
import java.util.function.Function;

public class AdsRepository {
    private SessionFactory sf;

    public AdsRepository() {
        StandardServiceRegistry registry = RegistryRepository.instOf();
        this.sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final AdsRepository INST = new AdsRepository();
    }

    public static AdsRepository instOf() {
        return AdsRepository.Lazy.INST;
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

    public void save(Ads ads) {
        tx(session -> session.save(ads));
    }

    public void update(Ads ads) {
        tx(session -> {
            session.update(ads);
            return true;
        });
    }

    public Ads find(int id) {
        return tx(session -> {
            Ads result = session.get(Ads.class, id);
            return result;
        });
    }

    public Collection<Ads> findAdsAll(String brand) {
        return tx(session -> {
            Collection<Ads> result = session.createQuery("select ad from ru.job4j.model.Ads ad join fetch ad.car where ad.car.brand = :brandP")
                    .setParameter("brandP", brand)
                    .list();
            return result;
        });
    }

    public Collection<Ads> findAdsUser(int id) {
        return tx(session -> {
            Collection<Ads> result = session.createQuery("from ru.job4j.model.Ads where user_id = :idUser")
                    .setParameter("idUser", id)
                    .list();
            return result;
        });
    }
}