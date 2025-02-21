package com.ti2cc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "exercicios";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "cz";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	

	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirCarro(Carro carro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO carro (codigo, modelo, marca, ano) "
					       + "VALUES ("+carro.getCodigo()+ ", '" + carro.getModelo() + "', '"  
					       + carro.getMarca() + "', '" + carro.getAno() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCarro(Carro carro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE carro SET modelo = '" + carro.getModelo() + "', marca = '"  
				       + carro.getMarca() + "', ano = '" + carro.getAno() + "'"
					   + " WHERE codigo = " + carro.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCarro(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM carro WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Carro[] getCarros() {
	    ArrayList<Carro> listaCarros = new ArrayList<>();
	    
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = st.executeQuery("SELECT * FROM carro");

	        // Verifica se há pelo menos um registro
	        while (rs.next()) {
	            // Cria o objeto Carro e adiciona à lista
	            Carro carro = new Carro(rs.getInt("codigo"), rs.getString("modelo"), 
	                                    rs.getString("marca"), rs.getString("ano").charAt(0));
	            listaCarros.add(carro);
	        }
	        
	        st.close();
	        
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }

	    // Convertendo a lista para um array de Carro
	    return listaCarros.toArray(new Carro[0]);
	}
	
	
}
