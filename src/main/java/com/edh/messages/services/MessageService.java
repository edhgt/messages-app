package com.edh.messages.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.edh.messages.models.Message;
import com.edh.messages.dao.MessageDao;

public class MessageService {
    MessageDao messageDao = null;
    Scanner input = null;
    
    public MessageService(MessageDao messageDao, Scanner input) {
        this.messageDao = messageDao;
        this.input = input;
    }

    public Message add() throws SQLException {
    	Message message = new Message();
        System.out.println("Creando un nuevo mensaje...");
        System.out.println("Ingrese el título: ");
        message.setTitle(this.input.next());
        
        System.out.println("Ingrese el contenido: ");
        message.setBody(this.input.next());
        
        System.out.println("Ingrese el author: ");
        message.setAuthor(this.input.next());

        message = this.messageDao.add(message);
        System.out.println("Mensaje creado... " + message);
        return message;
    }
    
    public Message show() throws SQLException {
    	 System.out.println("Ingrese el ID: ");
         return this.messageDao.show(this.input.nextInt());
    }
    
    public List<Message> all() throws SQLException {
        return this.messageDao.all();
    }
    
    public int delete() throws SQLException {
    	System.out.println("Ingrese el ID: ");
    	return this.messageDao.delete(this.input.nextInt());
    }
    
    public Message update() throws SQLException {
    	Message message = new Message();
        System.out.println("Actualizando mensaje...");
        System.out.println("Ingrese ID...");
        message.setIdMessage(this.input.nextInt());
        System.out.println("Ingrese el título: ");
        message.setTitle(this.input.next());
        
        System.out.println("Ingrese el contenido: ");
        message.setBody(this.input.next());
        
        System.out.println("Ingrese el author: ");
        message.setAuthor(this.input.next());

        message = this.messageDao.update(message);
        System.out.println("Mensaje creado... " + message);
        return message;
    }
    
}
