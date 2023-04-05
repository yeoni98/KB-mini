package coin.dao;

import java.util.ArrayList;

import coin.vo.Account;
import coin.vo.Customer;
import coin.vo.WalletNo;

public interface coinTemplate {
	// 비즈니스 로직
	void insertCust(Customer cust);
	void updateCust(Customer cust);
	void withdrawCust(int custId);
	Customer getCustInfo(int custId);

	void openAccount(int custId);
	int getAccountStatus(int accountNo);
	int getAccountBalance();
	
	void createWallet(int custId);
	void deleteWallet(String walNo);
	
	ArrayList<Account> getCoinList();
	ArrayList<Account> getCoinList(String coinName);
	
	void buyCoin(int custId, String coinCode);
	void sellCoin(int custId, String coinCode);
	
	void deductBalance(String accountNo, int amount);
	void addBalance(String accountNo, int amount);
	
	void insertWalletLog(WalletNo wl);
}
 