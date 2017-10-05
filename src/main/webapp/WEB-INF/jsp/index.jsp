<html>
    <head>
        <title>Spring boot</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body>
        <form method="post" action="save">
            <table align="center">
                <tr>
                    <td>
                        Enter the login:
                    </td>
                    <td>
                        <input type="text" placeholder="Login" maxlength="50" name="loginSpace" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Enter the password:
                    </td>
                    <td>
                        <input type="password" placeholder="Password" maxlength="50" name="passwordSpace" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Sudmit" maxlength="50">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <label name="label">${labelValue}</label>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>