package coin.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coin.exception.AlreadyHasAccountException;
import coin.exception.RecordNotFoundException;

import config.ServerInfo;

public class AcoountDAOImpl implements AccountDAO {
	private static AcoountDAOImpl dao = new AcoountDAOImpl();
	
	private AcoountDAOImpl() {}
	
	public static AcoountDAOImpl getInstance() {
		return dao;
	}
	
	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null) ps.close();
		if (conn != null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null) rs.close();
		closeAll(ps, conn);		
	}

	private boolean custExists(int custNo, Connection conn) throws SQLException {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM customer WHERE cust_no = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, custNo);

		rs = ps.executeQuery();

		return rs.next();
	}
	
	private boolean accountExists(String accountNo, Connection conn) throws SQLException {		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		String query = "SELECT * FROM account WHERE account_no = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, accountNo);

		rs = ps.executeQuery();
		
		return rs.next();
	}
	
	private boolean accountExistsByCustNo(int custNo, Connection conn) throws SQLException {		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		String query = "SELECT * FROM account WHERE cust_no = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, custNo);
		
		rs = ps.executeQuery();
		
		return rs.next();
	}
	
	private String makeAccount(Connection conn) throws SQLException {
		String account = "";

		// XXXX-XXXX-XXXX-XXXX 형태
		for (int i = 0; i < 4; i++) {
			String temp = (int)(Math.random()*10000) + "";
			while (temp.length() != 4) {
				temp = (int)(Math.random()*10000) + "";
			}
			account += temp;
			// 마지막엔 - 가 들어가면 안됨
			if (i == 3) break;
			
			account += "-";
		}
		return account;
	}
	
	@Override
	public void openAccount(int custNo) throws SQLException, RecordNotFoundException, AlreadyHasAccountException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			// 1. 회원 검증
			if (custExists(custNo, conn)) {	
			// 2. 계좌번호 존재 여부 체크
			if (accountExistsByCustNo(custNo, conn)) throw new AlreadyHasAccountException("이미 계좌가 생성된 회원입니다. ");
			
			// 3. 계좌 개설
			String account = makeAccount(conn);
			String query = "INSERT INTO account (account_no, cust_no) VALUES (?, ?)";
			ps = conn.prepareStatement(query);

			ps.setString(1, account);
			ps.setInt(2, custNo);	
			
			System.out.println(ps.executeUpdate() + "건의 계좌개설을 완료하였습니다. ");
			} else {
				throw new RecordNotFoundException("존재하지 않는 회원입니다.");
			}
		} finally {
			closeAll(ps, conn);
		}		
	}

	@Override
	public int getAccountStatus(String accountNo) throws SQLException, RecordNotFoundException {
		// 0: 사용가능계좌 1: 사용중지계좌
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			String query = "SELECT * FROM account WHERE account_no = ?";
				
			ps = conn.prepareStatement(query);
			ps.setString(1, accountNo);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				rs.getInt("status");
			} else {
				throw new RecordNotFoundException("존재하지 않는 계좌입니다.");
			}
		} finally {
			closeAll(ps, conn);
		}		
		
		return 0;
	}

	@Override
	public int getAccountBalance(String accountNo) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();

			String query = "SELECT balance FROM account WHERE account_no = ?";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, accountNo);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				rs.getInt("balance");
			} else {
				throw new RecordNotFoundException("존재하지 않는 계좌입니다.");
			}
		} finally {
			closeAll(ps, conn);
		}	
		
		return 0;
	}
}
