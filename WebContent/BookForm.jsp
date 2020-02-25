<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <h1>Book Entry Form</h1>

		<c:if test="${book != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${book == null}">
            <form action="insert" method="post">
        </c:if>

        <input type="hidden" name="id" value="${book.id}">
        
		<table border="1px">
			<tr><td>Title</td><td><input type="text" name="title" value="${book.title}"></td> </tr>
			<tr><td>Author</td><td><input type="text" name="author" value="${book.author}"></td> </tr>
			<tr><td>Price</td><td><input type="text" name="price" value="${book.price}"></td> </tr>
			<tr><td> </td><td><input type="submit" name="submit" value="Submit"></td> </tr>
		</table>
</form>


</body>
</html>