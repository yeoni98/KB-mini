package coin.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coin.coin.CoinDAOImpl;
import coin.exception.AlreadyHasAccountException;
import coin.exception.NotEfficientException;
import coin.exception.RecordNotFoundException;
import coin.vo.Account;
import coin.vo.Coin;
import coin.vo.Wallet;
import coin.wallet.WalletDAOImpl;
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
		System.out.println("DB연결");
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
	public int getAccountBalance(int custNo) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int balance = 0;
		try {
			conn = getConnect();

			String query = "SELECT balance FROM account WHERE cust_no = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, custNo);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				balance = rs.getInt("balance");
			} else {
				throw new RecordNotFoundException("존재하지 않는 회원입니다.");
			}
		} finally {
			closeAll(ps, conn);
		}	
		
		return balance;
	}
	
	@Override
	public Account getAccountInfo(int custNo) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account acc = null;
		try {
			conn = getConnect();
			System.out.println("getConnect....");
			String query = "SELECT account_no, cust_no, balance, status, c_date   FROM account WHERE cust_no = ?";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, custNo);
			
			rs = ps.executeQuery();
			System.out.println("rs..생성");
			if (rs.next()) {
//				System.out.println("if...진입");
//				System.out.println(rs.next());
				acc =  new Account(rs.getString("account_no"), rs.getInt("cust_no"), rs.getInt("balance"), rs.getString("status"), rs.getDate("c_date"));
				System.out.println(acc);
			} else {
				System.out.println("aaaa???");
				throw new RecordNotFoundException("존재하지 않는 회원입니다.");
			}
			return acc;
		} finally {
			closeAll(ps, conn);
		}		
	}

	@Override
	public void buyCoin(int custNo, String coinCd, int quentity) throws SQLException, RecordNotFoundException, NotEfficientException {
		System.out.println("???");
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();

//			conn.setAutoCommit(false);	
			
			// 잔액 조회
			Account accountInfo = getAccountInfo(custNo);

			// 해당 코인 가격 가져오기
			CoinDAOImpl coinDAOImpl = CoinDAOImpl.getInstance();
			Coin coinInfo = coinDAOImpl.getCoinByCoinCd(coinCd);
			int total = coinInfo.getcNowPrice() * quentity;
			
			// 잔액이 충분한지 체크
			if (accountInfo.getBalance() >= total) {
				int balance = accountInfo.getBalance() - total;
				String query = "UPDATE account SET balance = ? WHERE account_no = ?";
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, balance);
				ps.setInt(2, custNo);

				ps.executeUpdate();
				
				// TODO: insertWallet (다연이하는중)
				WalletDAOImpl dao = WalletDAOImpl.getInstance();				   
				dao.createWallet(new Wallet(coinCd, quentity, coinInfo.getcNowPrice(), "1", accountInfo.getAccountNo()));
				System.out.println(quentity + "개의 " + coinInfo.getcName() + "매수 완료!");

				conn.commit();
			} else {
				throw new NotEfficientException("잔고가 충분하지 않습니다. ");
			}
		} finally {
			closeAll(ps, conn);
		}
	}
	
	private int getCountCoin(String accountNo, String coinCd) throws SQLException {
		Connection conn = null;

		// 매수 코인
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		int buyCoinCnt = 0;

		// 매도 코인
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int sellCoinCnt = 0;
		
		try {
			conn = getConnect();

			String buyCoinQuery = "SELECT COUNT(*) FROM wallet WHERE account_no = ? and c_code = ? and c_dealtype = 0 GROUP BY c_code, c_dealtype";
			 
			ps1 = conn.prepareStatement(buyCoinQuery);
			ps1.setString(1, accountNo);
			ps1.setString(2, coinCd);
			
			rs1 = ps1.executeQuery();
			buyCoinCnt = rs1.getInt(1);
//			while (rs1.next()) buyCoinCnt++;
			
			String sellCoinQuery = "SELECT COUNT(*) FROM wallet WHERE account_no = ? and c_code = ? and c_dealtype = 1 GROUP BY c_code, c_dealtype";
			ps2 = conn.prepareStatement(sellCoinQuery);
			ps2.setString(1, accountNo);
			ps2.setString(2, coinCd);
			
			rs2 = ps2.executeQuery();
			sellCoinCnt = rs2.getInt(1);
//			while (rs2.next()) sellCoinCnt++;
		} finally {
			closeAll(ps1, null);
			closeAll(ps2, conn);
		}
		
		return buyCoinCnt - sellCoinCnt;		
	}
 
	@Override
	public void sellCoin(int custNo, String coinCd, int quentity) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// 1. 보유 코인이 구매하고자하는 수량만큼 보유하고있는지 확인
			conn = getConnect();
			conn.setAutoCommit(false);	
			
			// 잔액 조회
			Account accountInfo = getAccountInfo(custNo);
			int hasCoinCnt = getCountCoin(accountInfo.getAccountNo(), coinCd);
			
			// 해당 코인 가격 가져오기
			CoinDAOImpl coinDAOImpl = CoinDAOImpl.getInstance();
			Coin coinInfo = coinDAOImpl.getCoinByCoinCd(coinCd);
			int total = coinInfo.getcNowPrice() * quentity;
			
			// 2. 없으면 에러
			if (hasCoinCnt >= quentity) {
				int balance = accountInfo.getBalance() + total;
				
				String query = "UPDATE account SET balance = ? WHERE account_no = ?";
				
				ps = conn.prepareStatement(query);
				ps.setDouble(1, balance);
				ps.setInt(2, custNo);

				ps.executeUpdate();
				
				// TODO: insertWallet (다연이하는중)
				WalletDAOImpl dao = WalletDAOImpl.getInstance();				   
				dao.createWallet(new Wallet(coinCd, quentity, coinInfo.getcNowPrice(), "0", accountInfo.getAccountNo()));
				
				System.out.println(quentity + "개의 " + coinInfo.getcName() + " 매도 완료!");
				conn.commit();
			} else {
				throw new NotEfficientException("보유 코인이 충분하지 않습니다. ");
			}
		} catch(Exception e) {
			conn.rollback();
		} finally {
			closeAll(ps, conn);
		}
	}
}
