

package br.com.cadastroacademico.CONEXAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "");
        }catch (ClassNotFoundException e){
            throw new SQLException(e.getMessage());
        }
    }
    
}
