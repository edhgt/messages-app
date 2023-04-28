package com.edh.messages.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
  
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    Dotenv dotenv = Dotenv.load();
    
	private DatabaseConnection() throws SQLException {
		String url = "jdbc:mysql://"+ dotenv.get("DATABASE_HOST") +":"+ dotenv.get("DATABASE_PORT") +"/" + dotenv.get("DATABASE_NAME");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, dotenv.get("DATABASE_USER"), dotenv.get("DATABASE_PASSWORD"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    
	public Connection getConnection() {
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
