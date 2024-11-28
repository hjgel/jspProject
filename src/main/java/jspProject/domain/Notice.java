package jspProject.domain;

public class Notice {
    private int notice_id;
    private String title;
    private String content;
    private String created_at;
    private int userId;

    // Getters and Setters
    public int getNotice_id() {
        return notice_id;
    }
    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}