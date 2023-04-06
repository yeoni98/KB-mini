package coin.wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coin.vo.Coin;
import coin.vo.Wallet;

public interface WalletDAO {
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn)throws SQLException;
	
	void createWallet(Wallet wl) throws SQLException;
	void deleteWallet(String Id);
	ArrayList<Coin> getCoinList() throws SQLException;
	ArrayList<Coin> getCoinList(String coinName) throws SQLException;
}
