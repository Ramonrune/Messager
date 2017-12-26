package com.mensagens.Messager.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.mensagens.Messager.factory.ConnectionFactory;
import com.mensagens.Messager.model.Profile;

/**
 * @author Ramon Lacava Gutierrez Gonçales
 * @version 1.0.0
 * @date 08/10/2016 21:02:24
 */
public class ProfileDao {
	// a conexão com o banco de dados

	private Connection connection;

	public ProfileDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Profile adiciona(Profile profile) {
		String sql = "insert into Profile values (?, ?, ?, ?, ?, ?)";
		try {
			// prepared statement para inserção
			PreparedStatement stm = connection.prepareStatement(sql);

			// seta os valores
			stm.setInt(1, profile.getId());
			stm.setString(2, profile.getProfileName());
			stm.setString(3, profile.getFirstName());
			stm.setString(4, profile.getLastName());
			stm.setString(5, profile.getEmail());
			stm.setTimestamp(6, Timestamp.valueOf(profile.getCreated()));

			// executa
			stm.execute();
			stm.close();
			
			return profile;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Profile> getProfiles() {
		try {
			List<Profile> profiles = new ArrayList<Profile>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from Profile");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id_profile"));
				profile.setProfileName(rs.getString("name"));
				profile.setFirstName(rs.getString("first_name"));
				profile.setLastName(rs.getString("last_name"));
				profile.setEmail(rs.getString("email"));
				profile.setCreated(rs.getTimestamp("created").toLocalDateTime());

				profiles.add(profile);
			}
			rs.close();
			stmt.close();
			return profiles;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Profile altera(Profile profile) {
		String sql = "update Profile set email=? where id_profile = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, profile.getEmail());
			stmt.setInt(2, profile.getId());

			stmt.execute();
			stmt.close();
			
			return profile;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(int idProfile) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from Profile where id_profile=?");
			stmt.setInt(1, idProfile);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public Profile getProfile(int idProfile) {
		try {
			Profile profile = new Profile();
			PreparedStatement stmt = this.connection.prepareStatement("select * from Profile where id_profile = ?");
			stmt.setInt(1, idProfile);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				profile.setId(rs.getInt("id_profile"));
				profile.setProfileName(rs.getString("name"));
				profile.setFirstName(rs.getString("first_name"));
				profile.setLastName(rs.getString("last_name"));
				profile.setEmail(rs.getString("email"));
				profile.setCreated(rs.getTimestamp("created").toLocalDateTime());

			}
			rs.close();
			stmt.close();
			return profile;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}