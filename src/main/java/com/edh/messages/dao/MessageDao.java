package com.edh.messages.dao;

import com.edh.messages.database.DatabaseConnection;
import com.edh.messages.interfaces.MessageInterface;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.edh.messages.models.Message;

public class MessageDao implements MessageInterface {
    DatabaseConnection databaseConnexion = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    public MessageDao(DatabaseConnection databaseConnection) {
        this.databaseConnexion = databaseConnection;
        this.connection = this.databaseConnexion.getConnection();
    }
    
    @Override
    public Message add(Message message) throws SQLException {
        try {
            String query = "INSERT INTO messages(title, body, author) values(?, ?, ?);";
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setString(1, message.getTitle());
            this.preparedStatement.setString(2, message.getBody());
            this.preparedStatement.setString(3, message.getAuthor());
            
            int inserted = this.preparedStatement.executeUpdate();
            System.out.println(inserted);
            if(inserted != 1) {
            	System.out.println("No se pudo insertar");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return message;
    }

    @Override
    public Message show(int id) throws SQLException {
    	String query = "SELECT * FROM messages WHERE id = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setInt(1, id);
        Message message = new Message();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
            	
            	message.setIdMessage(resultSet.getInt("id"));
            	message.setTitle(resultSet.getString("title"));
            	message.setBody(resultSet.getString("body"));
            	message.setAuthor(resultSet.getString("author"));
            	message.setCreatedAt(resultSet.getTimestamp("created_at"));
            	message.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            	message.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
        return message;
    }

    @Override
    public Message update(Message message) throws SQLException {
    	try {
            String query = "UPDATE messages SET title=?, body=?, author=?, updated_at=? WHERE id=?;";
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setString(1, message.getTitle());
            this.preparedStatement.setString(2, message.getBody());
            this.preparedStatement.setString(3, message.getAuthor());
            this.preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            this.preparedStatement.setInt(5, message.getIdMessage());
            
            int updated = this.preparedStatement.executeUpdate();
            System.out.println(updated);
            if(updated != 1) {
            	System.out.println("No se pudo actualizar");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return message;
    }

    @Override
    public int delete(int id) throws SQLException {
    	int deleted = 0;
    	try {
            String query = "UPDATE messages SET deleted_at=? WHERE id=?;";
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            this.preparedStatement.setInt(2, id);
            
            deleted = this.preparedStatement.executeUpdate();
            System.out.println(deleted);
            if(deleted != 1) {
            	System.out.println("No se pudo eliminar");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return deleted;
    }

    @Override
    public List<Message> all() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM messages;";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
            	Message message = new Message();
            	message.setIdMessage(resultSet.getInt("id"));
            	message.setTitle(resultSet.getString("title"));
            	message.setBody(resultSet.getString("body"));
            	message.setAuthor(resultSet.getString("author"));
            	message.setCreatedAt(resultSet.getTimestamp("created_at"));
            	message.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            	message.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            	messages.add(message);
            }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
        return messages;
    }
}
