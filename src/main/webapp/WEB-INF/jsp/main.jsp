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
    <table align="center">
        <tr>
            <td>
                <h1>Добро пожаловать, ${login}!</h1>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label name="labelResult">${labelResultValue}</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form action="info" method="post">
                    <input type="submit" value="Дуэли">
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form action="index" method="post">
                    <input type="submit" value="Выход">
                </form>
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