package com.mensagens.Messager.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mensagens.Messager.exception.DataNotFoundException;
import com.mensagens.Messager.factory.ConnectionFactory;
import com.mensagens.Messager.model.Message;

public class MessageDao {
	// a conexão com o banco de dados

	private Connection connection;

	public MessageDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Message adiciona(Message message) {
		String sql = "insert into message values (?, ?, ?, ?)";
		try {
			// prepared statement para inserção
			PreparedStatement stm = connection.prepareStatement(sql);

			// seta os valores
			stm.setInt(1, message.getId());
			stm.setString(2, message.getMessage());
			stm.setTimestamp(3, Timestamp.valueOf(message.getCreated()));
			stm.setInt(4, message.getAuthor());

			// executa
			stm.execute();
			stm.close();
			return message;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Message> getMessages() {
		try {
			List<Message> messages = new ArrayList<Message>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from Message");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id_message"));
				message.setMessage(rs.getString("message"));
				message.setCreated(rs.getTimestamp("created").toLocalDateTime());
				message.setAuthor(rs.getInt("id_profile"));

				messages.add(message);
			}
			rs.close();
			stmt.close();
			return messages;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Message> getMessages(int year) {
		try {
			List<Message> messages = new ArrayList<Message>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from Message where YEAR(created) >= ?");
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id_message"));
				message.setMessage(rs.getString("message"));
				message.setCreated(rs.getTimestamp("created").toLocalDateTime());
				message.setAuthor(rs.getInt("id_profile"));

				messages.add(message);
			}
			rs.close();
			stmt.close();
			return messages;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public List<Message> getMessages(int offset, int range) {
		try {
			List<Message> messages = new ArrayList<Message>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from Message ORDER BY id_message DESC LIMIT ?, ?;");
			stmt.setInt(1, offset);
			stmt.setInt(2, range);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id_message"));
				message.setMessage(rs.getString("message"));
				message.setCreated(rs.getTimestamp("created").toLocalDateTime());
				message.setAuthor(rs.getInt("id_profile"));

				messages.add(message);
			}
			rs.close();
			stmt.close();
			return messages;
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	
	public Message getMessage(int id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from Message where id_message = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				throw new DataNotFoundException("Nenhum dado foi encontrado para a mensagem com id " + id);
			} 
			
			Message message = new Message();

			while (rs.next()) {
				message.setId(rs.getInt("id_message"));
				message.setMessage(rs.getString("message"));
				message.setCreated(rs.getTimestamp("created").toLocalDateTime());
				message.setAuthor(rs.getInt("id_profile"));
			}
			
			
			
			rs.close();
			stmt.close();
			
			return message;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Message altera(Message message) {
		String sql = "update Message set message=? where id_message = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, message.getMessage());
			stmt.setInt(2, message.getId());

			stmt.execute();
			stmt.close();
			return message;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(int messageId) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from Message where id_message=?");
			stmt.setInt(1, messageId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



}