package com.edh.messages.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.edh.messages.models.Message;

public interface MessageInterface {
    public Message add(Message message) throws SQLException;
    public Message show(int id) throws SQLException;
    public Message update(Message message) throws SQLException;
    public int delete(int id) throws SQLException;
    public List<Message> all() throws SQLException;

}
