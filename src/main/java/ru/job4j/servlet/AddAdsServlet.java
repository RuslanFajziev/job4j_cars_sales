package ru.job4j.servlet;

import ru.job4j.model.Ads;
import ru.job4j.model.Car;
import ru.job4j.model.User;
import ru.job4j.store.UserRepository;
import ru.job4j.store.AdsRepository;
import ru.job4j.store.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAdsServlet extends HttpServlet {
    private String brandGlobal;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var description = req.getParameter("description");
        var sold = Boolean.valueOf(req.getParameter("soldState"));
        var brand = req.getParameter("brand");
        var model = req.getParameter("model");
        var bodyType = req.getParameter("bodyType");
        var gearbox = req.getParameter("gearbox");
        var carDrive = req.getParameter("carDrive");
        var carEngine = Integer.valueOf(req.getParameter("carEngine").toString());

        UserRepository userRepository = UserRepository.instOf();
        AdsRepository adsRepository = AdsRepository.instOf();
        CarRepository carRepository = CarRepository.instOf();

        Car car = Car.of(brand, model, bodyType, gearbox, carDrive, carEngine);
        carRepository.save(car);

        Ads ads = Ads.of(description, sold, car);

        HttpSession sc = req.getSession();
        String email = ((User) sc.getAttribute("user")).getEmail();
        User user = userRepository.find(email);
        user.addAds(ads);
        userRepository.update(user);
        var urlPuth = "/ads.jsp?nameBrand=" +  brand;
        resp.sendRedirect(req.getContextPath() + urlPuth);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var description = req.getParameter("description");
        var sold = Boolean.valueOf(req.getParameter("soldState"));
        var brand = req.getParameter("brand");
        var model = req.getParameter("model");
        var bodyType = req.getParameter("bodyType");
        var gearbox = req.getParameter("gearbox");
        var carDrive = req.getParameter("carDrive");
        var carEngine = Integer.valueOf(req.getParameter("carEngine").toString());

        AdsRepository adsRepository = AdsRepository.instOf();
        CarRepository carRepository = CarRepository.instOf();
        UserRepository userRepository = UserRepository.instOf();

        var idAds = Integer.valueOf(req.getParameter("id"));
        var idCar = adsRepository.find(idAds).getCar().getId();

        Car car = Car.of(brand, model, bodyType, gearbox, carDrive, carEngine);
        car.setId(idCar);
        carRepository.update(car);

        Ads ads = Ads.of(description, sold, car);
        ads.setId(idAds);
        adsRepository.update(ads);
    }
}