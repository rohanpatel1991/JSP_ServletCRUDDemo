package com.ak.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ak.model.Book1;

public class Book1DAOImpl implements Book1DAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstore1?useTimezone=true&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private Connection conn;
	
	private void connect() throws SQLException{
		if(conn == null || conn.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
		}
	}

	@Override
	public boolean insertBook(Book1 book) {
		boolean row = false;
		try {
			String sql = "insert into book1(title,author,price) values(?,?,?)";
			connect();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,book.getTitle());
			st.setString(2, book.getAuthor());
	        st.setFloat(3, book.getPrice());
			row = st.executeUpdate() > 0;
			st.close();
			conn.close();
			return row;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public boolean updateBook(Book1 book) {
		boolean row = false;
		try {
			String sql = "update book1 set title=?, author=?,price=? where id=?";
			connect();
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, book.getTitle());
	        statement.setString(2, book.getAuthor());
	        statement.setFloat(3, book.getPrice());
	        statement.setInt(4, book.getId());
	         
	        row = statement.executeUpdate() > 0;
	        statement.close();
	        conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public boolean deleteBook(Book1 book) {
		boolean row = false;
		try {
			String sql = "DELETE FROM book1 where id = ?"; 
	        connect();
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setInt(1, book.getId());
	        row = st.executeUpdate() > 0;
	        st.close();
	        conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<Book1> listAllBooks() {
		// TODO Auto-generated method stub
		List<Book1> list = new ArrayList<Book1>();
  
		try {
			String sql = "SELECT * FROM book1"; 
			connect();
	        PreparedStatement st = conn.prepareStatement(sql);
	        ResultSet resultSet = st.executeQuery();
	        
	        while(resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	            System.out.println(title); 
	            Book1 book = new Book1(id, title, author, price);
	            list.add(book);
	        }
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book1 getBook1(int id) {
		Book1 book = null;
		try {
			String sql = "SELECT * FROM book1 WHERE id = ?";
			connect();
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	            book = new Book1(id, title, author, price);
	        }
	         
	        resultSet.close();
	        statement.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}
