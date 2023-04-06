package coin.vo;

import java.util.Date;

public class Account {	
	private String accountNo;
	private int custNo;
	private int balance;
	private String status;
	private Date cDate;
	

	public Account(String accountNo, int custNo, int balance, String status, Date cDate) {
		super();
		this.accountNo = accountNo;
		this.custNo = custNo;
		this.balance = balance;
		this.status = status;
		this.cDate = cDate;
	}


	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", custNo=" + custNo + ", balance=" + balance + ", status=" + status
				+ ", cDate=" + cDate + "]";
	}
}
