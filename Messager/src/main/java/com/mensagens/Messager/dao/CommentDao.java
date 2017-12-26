package com.mensagens.Messager.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.mensagens.Messager.factory.ConnectionFactory;
import com.mensagens.Messager.model.Comment;


public class CommentDao {
	// a conexão com o banco de dados

	private Connection connection;

	public CommentDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Comment adiciona(Comment comment) {
		String sql = "insert into comment values (?, ?, ?, ?, ?)";
		try {
			// prepared statement para inserção
			PreparedStatement stm = connection.prepareStatement(sql);

			// seta os valores
			stm.setInt(1, comment.getId());
			stm.setString(2, comment.getMessage());
			stm.setTimestamp(3, Timestamp.valueOf(comment.getCreated()));
			stm.setInt(4, comment.getAuthorId());
			stm.setInt(5, comment.getMessageId());

			

			// executa
			stm.execute();
			stm.close();
			return comment;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Comment> getComments(int messageId) {
		try {
			List<Comment> comments = new ArrayList<Comment>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from comment where id_message= ?");
			stmt.setInt(1, messageId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id_comment"));
				comment.setMessage(rs.getString("message"));
				comment.setCreated(rs.getTimestamp("created").toLocalDateTime());
				comment.setAuthorId(rs.getInt("id_profile"));
				comment.setMessageId(rs.getInt("id_message"));

				comments.add(comment);
			}
			rs.close();
			stmt.close();
			return comments;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public Comment getComment(int idComment) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from Comment where id_comment = ?");
			stmt.setInt(1, idComment);

			ResultSet rs = stmt.executeQuery();
			
			/*if (!rs.isBeforeFirst() ) {    
				throw new WebApplicationException( Response.status(Status.NOT_FOUND)
						.entity(new ErrorMessage("Não encontrado", 404, "ID da mensagem nao encontrado"))
						.build());
						ou throw NotFoundException(response);
			} 
			*/
			Comment comment = new Comment();

			while (rs.next()) {
				comment.setId(rs.getInt("id_comment"));
				comment.setMessage(rs.getString("message"));
				comment.setCreated(rs.getTimestamp("created").toLocalDateTime());
				comment.setAuthorId(rs.getInt("id_profile"));
				comment.setMessageId(rs.getInt("id_message"));

			}
			rs.close();
			stmt.close();
			return comment;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Comment altera(Comment comment) {
		String sql = "update Comment set message=? where id_comment = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, comment.getMessage());
			stmt.setInt(2, comment.getId());

			stmt.execute();
			stmt.close();
			return comment;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(int commentId) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from Comment where id_comment=?");
			stmt.setInt(1, commentId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
