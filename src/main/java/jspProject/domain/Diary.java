package jspProject.domain;

import java.sql.Timestamp;

public class Diary {
    private int diary_id;
    private int book_info_id;
    private String title;
    private String content;
    private Timestamp createdAt;


    public String getContent() {
        return content;
    }

    public int getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}

	public int getBook_info_id() {
		return book_info_id;
	}

	public void setBook_info_id(int book_info_id) {
		this.book_info_id = book_info_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}