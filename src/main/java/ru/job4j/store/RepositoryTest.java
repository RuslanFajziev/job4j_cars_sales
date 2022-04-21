package ru.job4j.store;

import ru.job4j.model.Ads;
import ru.job4j.model.Car;
import ru.job4j.model.User;

import java.util.HashSet;
import java.util.Set;

public class RepositoryTest {
    public static void main(String[] args) {
        var rsl = AdsRepository.instOf().findAdsUser(1);
        rsl.forEach(System.out::println);

//        var rsl = UserRepository.instOf().find("testmy@mail.ru");
//        System.out.println(rsl.getId());

//        AdsRepository adsRepository = AdsRepository.instOf();
//        var adsS = adsRepository.findAdsAll("Mazda");
//        adsS.forEach(s -> System.out.println(s.getCar()));

//        UserRepository userRepository = UserRepository.instOf();
//        AdsRepository adsRepository = AdsRepository.instOf();
//        CarRepository carRepository = CarRepository.instOf();
//        Car car = Car.of("BMW", "S40", "Sedan",
//                "Avtomat", "Disel", 400);
//        Car car2 = Car.of("Mazda", "Z48", "Sedan",
//                "Avtomat", "Benzin", 250);
//        Car car3 = Car.of("Mazda", "Z49", "Sedan",
//                "Avtomat", "Benzin", 300);
//        carRepository.save(car);
//        carRepository.save(car2);
//        carRepository.save(car3);
//        Ads ads = Ads.of("Prodaga", car);
//        Ads ads2 = Ads.of("Prodaga2", car2);
//        Ads ads3 = Ads.of("Prodaga2", car3);
//        adsRepository.save(ads);
//        adsRepository.save(ads2);
//        adsRepository.save(ads3);
//        Set<Ads> setAds = new HashSet<>();
//        setAds.add(ads);
//        setAds.add(ads2);
//        setAds.add(ads3);
//        User usr = User.of("Petr", "petr@mail.ru", "123321", setAds);
//        userRepository.save(usr);
    }
}