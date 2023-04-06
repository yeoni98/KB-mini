package coin.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


import coin.dao.Database;
import coin.vo.Coin;
import coin.vo.Wallet;

public class DatabaseTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Database db = new Database("127.0.0.1");
		
		/* 거래소에 있는 코인 목록 조회 */
//		ArrayList<Coin> c = db.getCoinList();
//		for(Coin cc: c) System.out.println(cc);

		/* 코인 명으로 코인 정보 조회 */
//		ArrayList<Coin> c = db.getCoinList("이더리움");
//		System.out.println(c);
		
		/* 전자지갑 생성 */
		//     private int walNo;
		   
//		 private int walNo;
//			private String cCode;
//		    private double cQuantity;
//		    private double cDealPrice;
//		    private String cDealType;
//		    private Date cDealDate;
//		    private String accountNo;
		db.createWallet(new Wallet("BT", 0.234, 975484.15, "1","533-12648"));
	
	}

}
