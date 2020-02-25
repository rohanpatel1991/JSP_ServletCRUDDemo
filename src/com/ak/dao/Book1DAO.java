package com.ak.dao;

import java.util.List;

import com.ak.model.Book1;

public interface Book1DAO {
	public boolean insertBook(Book1 book);
	public boolean updateBook(Book1 book);
	public boolean deleteBook(Book1 book);
	public List<Book1> listAllBooks();
	public Book1 getBook1(int id);
}
