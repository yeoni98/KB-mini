package coin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coin.exception.RecordNotFoundException;
import coin.vo.Customer;

public interface CustomerDAO {

	Connection getConnect() throws SQLException;

	void closeAll(Connection conn, PreparedStatement ps) throws SQLException;

	void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException;

	void addCustomer(Customer cust) throws SQLException;

	void removeCustomer(int custNo) throws SQLException, RecordNotFoundException;

	void updateCustomer(Customer cust) throws SQLException, RecordNotFoundException;
	
	Customer getCustomer(int custNo) throws SQLException;
		
	ArrayList<Customer> getAllCustomers() throws SQLException;
	

}
