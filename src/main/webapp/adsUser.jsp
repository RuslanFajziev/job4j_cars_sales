<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
    <title>Объявления продажи авто</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars_sales/adsUserJson.do',
            dataType: 'json'
        }).done(function (data) {
            let result = '';
            for (var ads of data) {
                var urlTeg = "<a href=/cars_sales/addAds.jsp?id=" + ads.id + ">";
                var description = ads.description;
                var sold = ads.sold;
                var soldNew = '';
                if (sold === false) {
                    soldNew = 'не продано';
                } else {
                    soldNew = 'ПРОДАНО!';
                }
                var car = ads.car;
                result += '<tr><td>' + urlTeg + description + '</a>'
                    + '</td><td>' + soldNew + '</td>'
                    + '</td><td>' + car.brand + '</td>'
                    + '</td><td>' + car.model + '</td>'
                    + '</td><td>' + car.bodyType + '</td>'
                    + '</td><td>' + car.gearbox + '</td>'
                    + '</td><td>' + car.carDrive + '</td>'
                    + '</td><td>' + car.carEngine + '</td>'
                    + '</td><td>'
                    + '<img src="/cars_sales/download?name=' + ads.id + '.png" width="100px" height="100px">'
                    + '</td></tr>';
            }
            $('#listBrand').html(result);

        }).fail(function (err) {
            console.log(err);
        });
    });
</script>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Объявления по продаже автомобилей
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Описание</th>
                        <th scope="col">Статус продажи</th>
                        <th scope="col">Бренд</th>
                        <th scope="col">Модель авто</th>
                        <th scope="col">Тип кузова</th>
                        <th scope="col">Трансмиссия</th>
                        <th scope="col">Тип привода</th>
                        <th scope="col">Мощность двигателя</th>
                        <th scope="col">Фото</th>
                    </tr>
                    </thead>
                    <tbody id="listBrand">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>