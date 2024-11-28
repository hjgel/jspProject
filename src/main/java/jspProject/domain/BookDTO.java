package jspProject.domain;

public class BookDTO {
	private int book_info_id;
    private String titleUrl; // 책 이미지 URL
    private String title;    // 책 제목
    private String author;   // 저자
    private int pages;       // 페이지 수
    

    // Getter와 Setter
    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

	public int getBook_info_id() {
		return book_info_id;
	}

	public void setBook_info_id(int book_info_id) {
		this.book_info_id = book_info_id;
	}
}