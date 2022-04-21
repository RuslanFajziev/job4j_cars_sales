package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import ru.job4j.store.UserRepository;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserRepository userRepository = UserRepository.instOf();
        var rsl = userRepository.find(email);

        if (rsl != null && rsl.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", rsl);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        req.setAttribute("error", "Не верный email или пароль");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}