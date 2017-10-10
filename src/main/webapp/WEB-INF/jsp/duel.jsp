<%@ page import="java.util.Date" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<% Date start = new Date(); %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="resources/css/index.css">

    <title>Ожидание</title>
</head>
<body>
<div id="body_block">
    <table align="center" width="60%" border="1px">
        <tr>
            <td colspan="5" align="center">
                <form action="search" method="post">
                    <input type="submit" value="Поиск соперника">
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="2" width="15%">
                ${yourLogin}
            </td>
            <td  width="30%" rowspan="4" align="center">
                <label>
                    <textarea readonly="readonly" style="width: 98%; height: 100%">${textAreaValue}</textarea>
                </label>
            </td>
            <td colspan="2" width="15%">
                ${enemyLogin}
            </td>
        </tr>
        <tr>
            <td colspan="2" width="15%">
                <progress name="yourHealthValue" value="${yourHealth}" max="${yourHealthMax}"></progress> ${yourHealth}hp
            </td>
            <td colspan="2" width="15%">
                <progress name="yourHealthValue" value="${enemyHealth}" max="${enemyHealthMax}"></progress> ${enemyHealth}hp
            </td>
        </tr>
        <tr>
            <td colspan="2">
                Ваш урон: ${yourDamage}
            </td>
            <td colspan="2">
                Урон соперника: ${enemyDamage}
            </td>
        </tr>
        <tr>
            <td colspan="2" width="15%"></td>
            <td colspan="2" width="15%">
                <form action="attack">
                    <input type="submit" value="Атаковать">
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="5"  width="30%" align="center">${labelWaitValue}</td>
        </tr>
        <tr>
            <td colspan="5" width="30%" align="center">${timerValue}</td>
        </tr>
    </table>
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