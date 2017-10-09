<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<% Date start = new Date(); %>
<html lang="ru">
<head>
    <title>Экран дуэлей</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="resources/css/index.css">
</head>
<body>
<table align="center">
    <tr>
        <td>
            Ваш логин:
        </td>
        <td>
            ${login}
        </td>
    </tr>
    <tr>
        <td>
            Уровень жизни:
        </td>
        <td>
            <progress name="progressBarDuelsScreen" value="${health}" max="${health}"></progress> ${health}hp
        </td>
    </tr>
    <tr>
        <td>
            Ваш урон:
        </td>
        <td>
            ${damage}
        </td>
    </tr>
    <tr>
        <td>
            Ваш рейтинг:
        </td>
        <td>
            ${rating}
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <form action="duel" method="post">
                <input type="submit" value="Начать дуэль">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <label name="labelWait">${labelWaitValue}</label>
        </td>
    </tr>
</table>
</body>
<div id="footer">
    <div>
        <label>Время генерации страницы:
            <% Date end = new Date(); %>
            <%= end.getTime() - start.getTime() %>ms
        </label>
    </div>
</div>
</html>