package com.senla.finalTask.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.senla.finalTask.model.Message;
import com.senla.finalTask.model.Views;

import java.util.List;

@JsonView(Views.FullMessage.class)
public class MessagePageDto {
    private List<Message> messages;
    private int currentPage;
    private int totolPages;

    public MessagePageDto(List<Message> messages, int currenPage, int totolPages) {
        this.messages = messages;
        this.currentPage = currenPage;
        this.totolPages = totolPages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getCurrenPage() {
        return currentPage;
    }

    public void setCurrenPage(int currenPage) {
        this.currentPage = currenPage;
    }

    public int getTotolPages() {
        return totolPages;
    }

    public void setTotolPages(int totolPages) {
        this.totolPages = totolPages;
    }
}
