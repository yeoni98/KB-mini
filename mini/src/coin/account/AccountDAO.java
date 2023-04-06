package coin.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coin.exception.AlreadyHasAccountException;
import coin.exception.RecordNotFoundException;
import coin.vo.Account;

public interface AccountDAO {
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn)throws SQLException;
	
	void openAccount(int custNo) throws SQLException, RecordNotFoundException, AlreadyHasAccountException;
	int getAccountStatus(String accountNo) throws SQLException, RecordNotFoundException;
	int getAccountBalance(int custNo) throws SQLException, RecordNotFoundException;
	Account getAccountInfo(int custNo) throws SQLException, RecordNotFoundException;
	
	void buyCoin(int custNo, String coinCd, int quentity) throws SQLException, RecordNotFoundException;
	void sellCoin(int custNo, String coinCd, int quentity) throws SQLException, RecordNotFoundException;
}
