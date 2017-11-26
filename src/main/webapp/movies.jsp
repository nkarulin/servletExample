<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
    </style>
    <head>
        <title>Movies</title>
    </head>
    <body>
        <h2>Hello, <%= session.getAttribute("login") %></h2>
        <table>
            <c:forEach items="${movies}" var="movie" varStatus="status1">
                <tr><td>
                <c:out value="${movie.title} (${movie.year})"/>
                </tr></td>
            </c:forEach>
        </table>
    </body>
</html>