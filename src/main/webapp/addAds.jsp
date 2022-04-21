<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="ru.job4j.store.AdsRepository" %>
<%@ page import="ru.job4j.model.Ads" %>
<%@ page import="ru.job4j.model.Car" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Объявления по продаже автомобилей</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function senderReg() {
        $('#my_form').submit();
    }

    function senderPut() {
        const form = document.getElementById('my_form');
        let descriptionDate = 'description=' + form.querySelector('[name="description"]').value;
        let soldStateDate = 'soldState=' + form.querySelector('[name="soldState"]').value;
        let brandDate = 'brand=' + form.querySelector('[name="brand"]').value;
        let modelDate = 'model=' + form.querySelector('[name="model"]').value;
        let bodyTypeDate = 'bodyType=' + form.querySelector('[name="bodyType"]').value;
        let gearboxDate = 'gearbox=' + form.querySelector('[name="gearbox"]').value;
        let carDriveDate = 'carDrive=' + form.querySelector('[name="carDrive"]').value;
        let carEngineDate = 'carEngine=' + form.querySelector('[name="carEngine"]').value;
        let idDate = 'id=' + form.querySelector('[name="id"]').value;
        let urlN = '<%=request.getContextPath()%>/addAds.do?';
        let urlDate = urlN + descriptionDate + '&'
            + soldStateDate + '&'
            + brandDate + '&'
            + modelDate + '&'
            + bodyTypeDate + '&'
            + gearboxDate + '&'
            + carDriveDate + '&'
            + carEngineDate + '&'
            + idDate;

        let urlNew = '<%=request.getContextPath()%>/ads.jsp?nameBrand=' + form.querySelector('[name="brand"]').value;

        $.ajax({
            type: 'PUT',
            url: urlDate,
            dataType: 'html',
            success: function (data) {
                window.location.href = urlNew;
            }
        });
    }
</script>
<body>
<%
    String id = request.getParameter("id");
    Car car = Car.of("", "", "", "", "", 0);
    Ads ads = Ads.of("", false, car);
    if (id != null) {
        AdsRepository adsRepository = AdsRepository.instOf();
        ads = adsRepository.find(Integer.valueOf(id));
        car = ads.getCar();
        String description = ads.getDescription();
    }
%>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="row">
        <div class="card" style="width: 100%">
            <% if (id == null) { %>
            <form id="my_form" action="<%=request.getContextPath()%>/addAds.do" method="post">
                    <% } else { %>
                <form id="my_form">
                    <% } %>
                    <div class="form-group" style="width: 50%">
                        <label for="description">description</label>
                        <input required type="text" class="form-control" id="description" name="description"
                               placeholder="description" value="<%=ads.getDescription()%>">
                        <label for="soldState">Статус объявления</label>
                        <select class="form-control" id="soldState" name="soldState">
                            <option>false</option>
                            <option>true</option>
                        </select>
                        <input type="hidden" name="id" value="<%=id%>">
                    </div>
                    <div class="form-group" style="width: 50%">
                        <label for="brand">brand</label>
                        <input required type="text" class="form-control" id="brand" name="brand" placeholder="brand"
                               value="<%=car.getBrand()%>">

                        <label for="model">model</label>
                        <input required type="text" class="form-control" id="model" name="model" placeholder="model"
                               value="<%=car.getModel()%>">

                        <label for="bodyType">bodyType</label>
                        <input required type="text" class="form-control" id="bodyType" name="bodyType"
                               placeholder="bodyType" value="<%=car.getBodyType()%>">

                        <label for="gearbox">gearbox</label>
                        <input required type="text" class="form-control" id="gearbox" name="gearbox"
                               placeholder="gearbox" value="<%=car.getGearbox()%>">

                        <label for="carDrive">carDrive</label>
                        <input required type="text" class="form-control" id="carDrive" name="carDrive"
                               placeholder="carDrive" value="<%=car.getCarDrive()%>">

                        <label for="carDrive">carEngine</label>
                        <input required type="number" class="form-control" id="carEngine" name="carEngine"
                               placeholder="carEngine" value="<%=car.getCarEngine()%>">
                    </div>
                    <% if (id == null) { %>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <% } else { %>
                    <button type="button" class="btn btn-primary" onclick="senderPut();">Сохранить</button>
                    <% } %>
                </form>
                    <% if (id != null) { %>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Загрузка фото авто</th>
                        <th scope="col">Фото авто</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="card-body">
                                <form action="<%=request.getContextPath()%>/download?name=<%=ads.getId()%>&id=<%=ads.getId()%>"
                                      method="post"
                                      enctype="multipart/form-data">
                                    <div class="checkbox">
                                        <input type="file" name="file">
                                    </div>
                                    <button type="submit" class="btn btn-default">Сохранить</button>
                                </form>
                            </div>
                        </td>
                        <td><img src="<%=request.getContextPath()%>/download?name=<%=ads.getId()%>.png" width="100px"
                                 height="100px"></td>
                    </tr>
                    </tbody>
                </table>
                    <% } %>
        </div>
    </div>
</div>
</body>
</html>