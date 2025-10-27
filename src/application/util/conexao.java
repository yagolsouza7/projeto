package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
  //endereco do banco de dados
  private static final String URL="jdbc:mysql://localhost:3306/db";
  //usuario do banco de dados
  private static final String USER="root";
  //senha do banco de dados
  private static final String PASS="";

  public static Connection getConnection() throws SQLException {
		  return DriverManager.getConnection(URL,USER,PASS);
  }
  
}
