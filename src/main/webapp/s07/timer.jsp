<%@ page import="java.time.LocalTime"%> <!-- mix tra html e java; import libreria Java  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hello JSP</title>
</head>
<body>
    <h1>
        <% //codice JSP. i simbolini permettono di inserire codice Java in un doc HTML
            out.print(LocalTime.now()); //response
        %>
    </h1>
</body>
</html>