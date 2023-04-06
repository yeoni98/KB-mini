package coin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coin.exception.RecordNotFoundException;
import coin.vo.Customer;
import config.ServerInfo;

public class CustomerDAOImpl implements CustomerDAO {

	private static CustomerDAOImpl dao = new CustomerDAOImpl();

	private CustomerDAOImpl() {

	}

	public static CustomerDAOImpl getInstance() {
		return dao;
	}

	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("DB Connection.....OK");
		return conn;
	}

	@Override
	public void closeAll(Connection conn, PreparedStatement ps) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(conn, ps);

	}

	private boolean custNoExist(int custNo, Connection conn) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT cust_no FROM customer WHERE cust_no= ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, custNo);
		rs = ps.executeQuery();
		return rs.next();

	}

	@Override
	public void addCustomer(Customer cust) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();

			String query = "INSERT INTO customer(cust_no, cust_name, cust_id, cust_pw, cust_ssn, cust_phone, cust_addr) "
					+ "VALUES (cust_seq_no.NEXTVAL,?,?,?,?,?,?)";

			ps = conn.prepareStatement(query);
			ps.setString(1, cust.getCustName());
			ps.setString(2, cust.getCustId());
			ps.setString(3, cust.getCustPw());
			ps.setString(4, cust.getCustSsn());
			ps.setString(5, cust.getCustPhone());
			ps.setString(6, cust.getCustAddr());

			System.out.println(ps.executeUpdate() + " 등록 완료");
		} finally {
			closeAll(conn, ps);
		}

	}

	@Override
	public void removeCustomer(int custNo) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			if (custNoExist(custNo, conn)) {
				String query = "DELETE customer WHERE cust_no = ?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, custNo);

				System.out.println(ps.executeUpdate() + " 삭제 완료");
			} else {
				throw new RecordNotFoundException("존재하지 않는 회원입니다.");
			}
		} finally {
			closeAll(conn, ps);
		}
	}

	@Override
	public void updateCustomer(Customer cust) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			if (custNoExist(cust.getCustNo(), conn)) {
				String query = "UPDATE customer SET cust_name =?, cust_id =?, cust_pw = ?, cust_ssn =?, cust_phone = ?, cust_addr =? WHERE cust_no = ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, cust.getCustName());
				ps.setString(2, cust.getCustId());
				ps.setString(3, cust.getCustPw());
				ps.setString(4, cust.getCustSsn());
				ps.setString(5, cust.getCustPhone());
				ps.setString(6, cust.getCustAddr());
				ps.setInt(7, cust.getCustNo());

				System.out.println(ps.executeUpdate() + " 수정 완료");

			} else {
				throw new RecordNotFoundException("수정할 대상이 없습니다.");
			}

		} finally {
			closeAll(conn, ps);

		}
	}

	@Override
	public Customer getCustomer(int custNo) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;

		try {
			conn = getConnect();
			String query = "SELECT cust_no, cust_name, cust_id, cust_pw, cust_ssn, cust_phone, cust_addr FROM customer WHERE cust_no =?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, custNo);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(custNo, rs.getString("cust_name"), rs.getString("cust_id"),
						rs.getString("cust_pw"), rs.getString("cust_ssn"), rs.getString("cust_phone"),
						rs.getString("cust_addr"));
			}

		} finally {
			closeAll(conn, ps, rs);
		}
		return customer;
	}

//	@Override
//	public ArrayList<Customer> getCustomer() throws SQLException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		ArrayList<Customer> list = new ArrayList<Customer>();
//		Customer customer = null;
//
//		try {
//			conn = getConnect();
//			String query = "SELECT cust_no, cust_name, cust_id, cust_pw, cust_ssn, cust_phone, cust_addr FROM customer;";
//			ps = conn.prepareStatement(query);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				list.add(new Customer(rs.getInt("custNo"), rs.getString("custName"), rs.getString("custId"),
//						rs.getString("custPw"), rs.getString("custSsn"), rs.getString("custPhone"),
//						rs.getString("custAddr")));
//			}
//
//		} finally {
//			closeAll(conn, ps, rs);
//		}
//
//		return list;
//	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<>();

		try {
			conn = getConnect();
			String query = "SELECT cust_no, cust_name, cust_id, cust_pw, cust_ssn, cust_phone, cust_addr FROM customer";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getInt("cust_no"), rs.getString("cust_name"), rs.getString("cust_id"),
						rs.getString("cust_pw"), rs.getString("cust_ssn"), rs.getString("cust_phone"),
						rs.getString("cust_addr")));
			}

		} finally {
			closeAll(conn, ps, rs);
		}

		return list;
	}

}
