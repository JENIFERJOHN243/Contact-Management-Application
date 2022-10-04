package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserDetails;



public class ContactManagementDAO {
	static Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public void getConnection() throws SQLException, ClassNotFoundException {
    	//Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/contactmanagementapplication", "root",
				"root");
	}

	public void addUserDetails(UserDetails user) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "INSERT INTO USER_DETAILS (first_name,last_name,mob_no,mail_id) VALUES(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getMobileNumber());
		preparedStatement.setString(4, user.getEmailId());
		preparedStatement.executeUpdate();
	}

	public Integer fetchTheUserId(UserDetails user) throws ClassNotFoundException, SQLException {
		getConnection();
		Integer userId = null;
		String sqlQuery = "SELECT user_id FROM USER_DETAILS WHERE first_name= ? AND last_name= ?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			userId = resultSet.getInt(1);
		}
		return userId;
	}

	public void updateUserDetails(UserDetails user) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "UPDATE USER_DETAILS SET first_name=?,last_name=?,mob_no=?,mail_id=? WHERE user_id=?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getMobileNumber());
		preparedStatement.setString(4, user.getEmailId());
		preparedStatement.setInt(5, user.getUserId());
		preparedStatement.executeUpdate();
	}

	public int deleteUserDetails(int id) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "DELETE FROM USER_DETAILS WHERE user_id=?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1,id);
		int result = preparedStatement.executeUpdate();
		return result;
	}

	public ArrayList<UserDetails> viewUserDetails() throws ClassNotFoundException, SQLException {
		getConnection();
		ArrayList<UserDetails> userList = new ArrayList<UserDetails>();
		String sqlQuery = "SELECT first_name,last_name,mob_no,mail_id FROM USER_DETAILS ORDER BY first_name ASC";
		preparedStatement = connection.prepareStatement(sqlQuery);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			UserDetails user = new UserDetails();
			user.setFirstName(resultSet.getString(1));
			user.setLastName(resultSet.getString(2));
			user.setMobileNumber(resultSet.getString(3));
			user.setEmailId(resultSet.getString(4));
			userList.add(user);
		}
		return userList;
	}

	public ArrayList<UserDetails> searchUserDetails(UserDetails user) throws ClassNotFoundException, SQLException {
		getConnection();
		ArrayList<UserDetails> userList = new ArrayList<UserDetails>();
		String sqlQuery ="SELECT first_name,last_name,mob_no,mail_id FROM USER_DETAILS WHERE first_name=?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.getFirstName());
        
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			user.setFirstName(resultSet.getString(1));
			user.setLastName(resultSet.getString(2));
			user.setMobileNumber(resultSet.getString(3));
			user.setEmailId(resultSet.getString(4));
			userList.add(user);
		}
		return userList;
	}
	
}
