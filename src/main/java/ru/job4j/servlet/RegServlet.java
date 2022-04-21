package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import ru.job4j.model.Ads;
import ru.job4j.model.User;
import ru.job4j.store.UserRepository;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserRepository userRepository = UserRepository.instOf();
        var rsl = userRepository.find(email);

        if (rsl != null) {
            req.setAttribute("error", "Уже есть зарегистрированный пользователь с email: " + email);
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }

        User user = User.of(name, email, password);
        userRepository.save(user);
        user = userRepository.find(email);

        HttpSession sc = req.getSession();
        sc.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}