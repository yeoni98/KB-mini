package coin.test;

import coin.vo.Wallet;
import coin.wallet.WalletDAOImpl;

public class WalletDAOImplTest {

	public static void main(String[] args) {
		WalletDAOImpl dao = WalletDAOImpl.getInstance();
		/* 거래소에 있는 코인 목록 조회 */
//		ArrayList<Coin> c = db.getCoinList();
//		for(Coin cc: c) System.out.println(cc);

		/* 코인 명으로 코인 정보 조회 */
//		ArrayList<Coin> c = db.getCoinList("이더리움");
//		System.out.println(c);
		
		/* 전자지갑 생성 */
		//     private int walNo;
		   
//		dao.createWallet(new Wallet("BT", 0.234, 975484.15, "1","533-12648"));
	}

}
