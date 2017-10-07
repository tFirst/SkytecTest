<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<% Date start = new Date(); %>
<html lang="ru">
    <head>
        <title>Игра</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="resources/css/index.css">

        <div id="header">
            <div>
                Страница для авторизации
            </div>
        </div>
    </head>
    <body>
    <div id="body_block">
        <div>
            <form method="post" action="save">
                <table align="center">
                    <tr>
                        <td>
                            Введите имя пользователя:
                        </td>
                        <td>
                            <input type="text" placeholder="Имя пользователя" maxlength="50" name="login" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Введите пароль:
                        </td>
                        <td>
                            <input type="password" placeholder="Пароль" maxlength="50" name="password" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Войти" maxlength="50">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <label name="label">${labelValue}</label>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
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