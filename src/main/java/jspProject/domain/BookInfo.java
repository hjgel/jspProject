package jspProject.domain;

public class BookInfo {
	private int book_info_id;
	private int pages;
	private int diary_id;
	private String title;
	private String author;
	private String titleUrl;
	
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
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
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
	public String getTitleUrl() {
		return titleUrl;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
}
