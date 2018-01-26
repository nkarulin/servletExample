<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Log In</title>
    </head>
    <body>
        <form action="movies" method="post" enctype="multipart/form-data">
            <h2>Please enter your login:</h2>
            <input type="text" name="title" value="Title" />
            <input type="number" name="year" value="1972"/>
            <input type="file" name="image" />
            <input type="submit" id="say-hello-button" value="Add Movie" />
        </form>
    </body>
</html>