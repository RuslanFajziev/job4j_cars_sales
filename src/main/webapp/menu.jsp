<%--
  Created by IntelliJ IDEA.
  User: FaizievRT
  Date: 003 03.01.2022
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">На главную</a>
        </li>
        <c:if test="${user != null}">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/addAds.jsp">Добавить объявление</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/adsUser.jsp">Мои объявление</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти из <c:out value="${user.name}"/></a>
            </li>
        </c:if>
        <c:if test="${user == null}">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
            </li>
        </c:if>
    </ul>
</div>
