package jspProject.domain;

public class BookInfo {
	private int book_info_id;
	private int pages;
	private int book_id;
	private int user_id;
	private String title;
	private String author;
	private String title_url;
	private Book book;
	
	public int getBook_info_id() {
		return book_info_id;
	}
	public void setBook_info_id(int book_info_id) {
		this.book_info_id = book_info_id;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getTitle_url() {
		return title_url;
	}
	public void setTitle_url(String title_url) {
		this.title_url = title_url;
	}
}
