<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<% Date start = new Date(); %>
<html lang="ru">
    <head>
        <title>Главное меню</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="resources/css/index.css">
    </head>
    <body>
        <h1>Welcome, ${login}!</h1>
        <h2>Your password is '${password}'</h2>
        <h2>Your health level is ${health}</h2>
        <h2>Your damage level is ${damage}</h2>
        <h2>Your rating is ${rating}</h2>
        <label name="labelResult">${labelResultValue}</label>
        <form action="index">
            <input type="submit" value="Выход">
        </form>
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