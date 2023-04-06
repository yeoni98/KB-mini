package coin.test;

import java.sql.SQLException;
import java.util.ArrayList;

import coin.account.AcoountDAOImpl;
import coin.customer.CustomerDAOImpl;
import coin.exception.AlreadyHasAccountException;
import coin.exception.NotEfficientException;
import coin.exception.RecordNotFoundException;
import coin.vo.Coin;
import coin.vo.Customer;
import coin.vo.Wallet;
import coin.wallet.WalletDAOImpl;

public class TotalTest {

	public static void main(String[] args) throws SQLException, RecordNotFoundException, NotEfficientException, AlreadyHasAccountException  {
		CustomerDAOImpl custDao = CustomerDAOImpl.getInstance();
		AcoountDAOImpl accountDao = AcoountDAOImpl.getInstance();
		WalletDAOImpl walletDao = WalletDAOImpl.getInstance();
		
		// 1. 회원가입
		custDao.addCustomer(new Customer("김연찬","yc", "1234","1111","010-1111-1111","수원"));
//		custDao.removeCustomer(3);
		custDao.updateCustomer(new Customer(2,"김연찬", "abc", "123", "1111", "010-1111-1111","수원"));
		
		// 2. 계좌생성
		accountDao.openAccount(3); // 8514-1009-1967-5912
		if (accountDao.getAccountStatus("8514-1009-1967-5912") == 0) {
			System.out.println("사용가능 계좌");
		} else {
			System.out.println("사용 불가능 계좌");
		}
		
		// 3. 잔액 조회
		System.out.println(accountDao.getAccountBalance(3) + "원");
		
		// 4. 전자지갑 계정 생성
		walletDao.createWallet(new Wallet("BT", 0.234, 975484.15, "3", "8514-1009-1967-5912"));
		
		// 5.거래소에 있는 코인 목록 조회
		ArrayList<Coin> c = walletDao.getCoinList();
		for(Coin cc: c) System.out.println(cc);
		System.out.println();
		
		/* 코인 명으로 코인 정보 조회 */
		ArrayList<Coin> coinListByName = walletDao.getCoinList("이더리움");
		for(Coin cc: coinListByName) System.out.println(cc);
	
		// 4. 매수
//		accountDao.buyCoin(2, "ML", 2);

		// 5. 매도
//		accountDao.sellCoin(2, "ML", 2);
	}

}
