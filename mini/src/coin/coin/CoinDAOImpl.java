package coin.coin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import coin.exception.RecordNotFoundException;
import coin.vo.Coin;
import config.ServerInfo;

public class CoinDAOImpl implements CoinDAO {
	private static CoinDAOImpl dao = new CoinDAOImpl();
	
	private CoinDAOImpl() {}
	
	public static CoinDAOImpl getInstance() {
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
	
	@Override
	public Coin getCoinByCoinCd(String CoinCd) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			
	        String query = "SELECT c_code, c_name, c_nowprice FROM coin where c_code = ?";
	        ps = conn.prepareStatement(query);
			ps.setString(1, CoinCd);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Coin(rs.getString("c_code"), 
						rs.getString("c_name"),
						rs.getInt("c_nowprice"));
			} else {
				throw new RecordNotFoundException("존재하지 않는 코인입니다. ");
			}
		} finally {
			closeAll(ps, conn);
		}
	}
}
