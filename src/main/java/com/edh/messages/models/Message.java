package com.edh.messages.models;

import java.sql.Timestamp;

public class Message {
    int idMessage;
    String title;
    String body;
    String author;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
    
    public Message() {}

    public Message(String title, String body, String author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", title=" + title + ", body=" + body + ", author=" + author
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
}
