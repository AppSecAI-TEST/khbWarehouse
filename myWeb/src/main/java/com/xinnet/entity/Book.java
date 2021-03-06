package com.xinnet.entity;

import java.io.Serializable;

public class Book implements Serializable {  
	private int bookId;  
	private String name;  
	private String author;  
	private float price;  
	private String isbn;  
	private String pubName;  
	
	
	public Book(int bookId, String name, String author)  
	{  
		this.bookId = bookId;  
		this.name = name;  
		this.author = author;  
		this.price = price;  
		this.isbn = isbn;  
		this.pubName = pubName;  
		 
	}  
	
	public int getBookId()  
	{  
		return bookId;  
	}  
	
	public void setBookId(int bookId)  
	{  
		this.bookId = bookId;  
	}  
	
	public String getName()  
	{  
		return name;  
	}  
	
	public void setName(String name)  
	{  
		this.name = name;  
	}  
	
	public String getAuthor()  
	{  
		return author;  
	}  
	
	public void setAuthor(String author)  
	{  
		this.author = author;  
	}  
	
	public float getPrice()  
	{  
		return price;  
	}  
	
	public void setPrice(float price)  
	{  
		this.price = price;  
	}  
	
	public String getIsbn()  
	{  
		return isbn;  
	}  
	
	public void setIsbn(String isbn)  
	{  
		this.isbn = isbn;  
	}  
	
	public String getPubName()  
	{  
		return pubName;  
	}  
	
	public void setPubName(String pubName)  
	{  
		this.pubName = pubName;  
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author="
				+ author + ", price=" + price + ", isbn=" + isbn + ", pubName="
				+ pubName + "]";
	}  
	
	
}