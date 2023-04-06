package coin.test;

import coin.account.AcoountDAOImpl;

public class AccountDAOImplTest {

	public static void main(String[] args) {
		AcoountDAOImpl dao = AcoountDAOImpl.getInstance();
		try {
			// 계좌개설
//			dao.openAccount(1); 
			
			// 계좌 상태
//			if (dao.getAccountStatus("6665-1538-3541-8983") == 0) {
//				System.out.println("사용가능 계좌");
//			} else {
//				System.out.println("사용 불가능 계좌");
//			}
			
			// 계좌 잔액
			System.out.println(dao.getAccountBalance(1) + "원");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
