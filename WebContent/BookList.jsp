<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <h1>Book Entry Form</h1>
     <a href="new"><h3>Add New Book</h3></a>
     <h3>List of Books</h3>
     
     <table border="1px">
        <tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${list}" var="book">
		    <tr>
		        <td>${book.id}</td>
		        <td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td> 
				<a href="edit?id=${book.id}">Update</a> 
				<a onclick="return confirm('Do you want to delete?')"  href="delete?id=${book.id}">Delete</a></th>
		    </tr>
		</c:forEach> 
     </table>
</body>
</html>