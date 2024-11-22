package jspProject.domain;

public class Book {
    private int bookId;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishDate;
    private String subject;
    private String introduction;
    private String titleUrl;


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}

	public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getTitleUrl() {
        return titleUrl;
    }
}
