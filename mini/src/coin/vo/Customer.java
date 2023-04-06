package coin.vo;

public class Customer {
	private int custNo; //cust_no
	private String custName; //cust_name
	private String custId; //cust_id
	private String custPw; //cust_pw
	private String custSsn; //cust_ssn
	private String custPhone; //cust_phone
	private String custAddr; //cust_addr
	
	public Customer(int custNo, String custName, String custId, String custPw, String custSsn, String custPhone,
			String custAddr) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custId = custId;
		this.custPw = custPw;
		this.custSsn = custSsn;
		this.custPhone = custPhone;
		this.custAddr = custAddr;
	}
	
	public Customer(String custName, String custId, String custPw, String custSsn, String custPhone,
			String custAddr) {
		this.custName = custName;
		this.custId = custId;
		this.custPw = custPw;
		this.custSsn = custSsn;
		this.custPhone = custPhone;
		this.custAddr = custAddr;
		
	}

	public int getCustNo() {
		return custNo;
	}
	
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getCustId() {
		return custId;
	}
	
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String getCustPw() {
		return custPw;
	}
	
	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}
	
	public String getCustSsn() {
		return custSsn;
	}
	
	public void setCustSsn(String custSsn) {
		this.custSsn = custSsn;
	}
	
	public String getCustPhone() {
		return custPhone;
	}
	
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	
	public String getCustAddr() {
		return custAddr;
	}
	
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	@Override
	public String toString() {
		return "Customer [custNo=" + custNo + ", custName=" + custName + ", custId=" + custId + ", custPw=" + custPw
				+ ", custSsn=" + custSsn + ", custPhone=" + custPhone + ", custAddr=" + custAddr + "]";
	}
	
	
}
