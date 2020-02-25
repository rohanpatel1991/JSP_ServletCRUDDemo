package com.ak.controller;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ak.dao.Book1DAO;
import com.ak.dao.Book1DAOImpl;
import com.ak.model.Book1;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Book1DAO book1dao = new Book1DAOImpl();   
    
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		List<Book1> list = book1dao.listAllBooks();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
        dispatcher.forward(request, response);
	}


	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
 
        Book1 book = new Book1(id, title, author, price);
        book1dao.updateBook(book);
        response.sendRedirect("list");
	}


	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Book1 e1 = book1dao.getBook1(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", e1);
        dispatcher.forward(request, response);
	}


	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		 
        Book1 book = new Book1(id);
        book1dao.deleteBook(book);
        response.sendRedirect("list");	
	}


	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String title = request.getParameter("title");
		String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
        
        Book1 newBook = new Book1(title, author, price);
        book1dao.insertBook(newBook);
        response.sendRedirect("list");
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
