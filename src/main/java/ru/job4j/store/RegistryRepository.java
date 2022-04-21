package ru.job4j.store;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class RegistryRepository {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    public static RegistryRepository getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final RegistryRepository INSTANCE = new RegistryRepository();
    }

    public static StandardServiceRegistry instOf() {
        return RegistryRepository.getInstance().registry;
    }
}