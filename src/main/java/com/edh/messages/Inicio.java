package com.edh.messages;

import java.util.List;
import java.util.Scanner;
import com.edh.messages.dao.MessageDao;
import com.edh.messages.database.DatabaseConnection;
import com.edh.messages.services.MessageService;
import com.edh.messages.models.Message;

public class Inicio {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            int optionSelected;
            
            do {
                System.out.println("----------------------------");
                System.out.println(":::Aplicación de mensajes:::");
                System.out.println("1. Crear mensaje");
                System.out.println("2. Mostrar mensaje");
                System.out.println("3. Listar mensajes");
                System.out.println("4. Editar mensaje");
                System.out.println("5. Eliminar mensaje");
                System.out.println("6. Salir");
                optionSelected = input.nextInt();
                
                MessageService messageService = new MessageService(new MessageDao(DatabaseConnection.getInstance()), input);
                switch (optionSelected) {
                    case 1:
                        messageService.add();
                        break;
                    case 2:
                    	Message msg;
                    	msg= messageService.show();
                    	System.out.println(msg);
                        break;
                    case 3:
                    	List<Message> messages = messageService.all();
                    	for (Message message: messages) {
                    		System.out.println(message);
                    	} 
                        break;
                    case 4:
                    	System.out.println(messageService.update());
                        break;
                    case 5:
                    	messageService.delete();
                        break;
                    default:
                        throw new AssertionError();
                }
                
            } while (optionSelected != 6);
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        finally {
            input.close();
        }
    }
}
