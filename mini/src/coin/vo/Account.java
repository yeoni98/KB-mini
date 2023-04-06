package coin.vo;

public class Account {	
	private String accountNo;
	private String custNo;
	private int balance;
	private int status;
	private String cDate;
	

	public Account(String accountNo, String custNo, int balance, int status, String cDate) {
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

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", custNo=" + custNo + ", balance=" + balance + ", status=" + status
				+ ", cDate=" + cDate + "]";
	}
}
