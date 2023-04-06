package coin.coin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import coin.exception.RecordNotFoundException;
import coin.vo.Coin;

public interface CoinDAO {
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	Coin getCoinByCoinCd(String CoinCd) throws SQLException, RecordNotFoundException;
}
