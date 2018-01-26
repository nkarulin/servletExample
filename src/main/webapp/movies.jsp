<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <style>
        a, u {
            text-decoration: none;
        }
        a:link {
            color: white;
        }

        a:visited {
            color: white;
        }
        .movie {
            background-position: center;
            height: 250px;
            width: 200px;
            overflow: hidden;
            background-size: cover
        }
    	table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            margin: 0 auto;
        }
        td {
            text-align: center;
            padding: 4px;
        }

    </style>
    <head>
        <title>Movies</title>
    </head>
    <body>
        <h2>Hello, <%= session.getAttribute("login") %></h2>
        <table>
        <tr>
            <c:forEach items="${movies}" var="movie" varStatus="count">
                <td>
                <div class="movie" style="background-image:url('pics/${movie.image}')">
                    <p><span style="background-color: #000003; color:white">
                        <a href="movies/id/${count.index}">
                            <c:out value="${movie.title} (${movie.year})"/>
                        </a>
                    </span></p>
                </div></td>

                <c:if test = "${count.count % 3 == 0}">
                    </tr><tr>
                </c:if>
            </c:forEach>
            </tr>
        </table>

        <br><br>
        <form action="addMovie.jsp">
            <input type="submit" value="Add new Movie" />
        </form>
    </body>
</html>