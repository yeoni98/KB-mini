package coin.account.test;

import coin.account.AcoountDAOImpl;

public class AccountTest {

	public static void main(String[] args) {
		AcoountDAOImpl dao = AcoountDAOImpl.getInstance();
		try {
//			dao.openAccount(1); 
//			if (dao.getAccountStatus("6665-1538-32222541-8983") == 0) {
//				System.out.println("사용가능 계좌");
//			} else {
//				System.out.println("사용 불가능 계좌");
//			}
//			
			System.out.println(dao.getAccountBalance(1) + "원");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
