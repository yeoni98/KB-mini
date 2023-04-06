package coin.vo;

//import java.sql.Date;
import java.util.Date;


public class Wallet {

    private int walNo;
	private String cCode;
    private double cQuantity;
    private double cDealPrice;
    private String cDealType;
    private Date cDealDate;
    private String accountNo;
    
    
    
    public Wallet(int walNo, String cCode, double cQuantity, double cDealPrice, String cDealType, Date cDealDate,
			String accountNo) {
		super();
		this.walNo = walNo;
		this.cCode = cCode;
		this.cQuantity = cQuantity;
		this.cDealPrice = cDealPrice;
		this.cDealType = cDealType;
		this.cDealDate = cDealDate;
		this.accountNo = accountNo;
	}




	public Wallet(String cCode, double cQuantity, double cDealPrice, String cDealType,
			String accountNo) {
		super();
		this.cCode = cCode;
		this.cQuantity = cQuantity;
		this.cDealPrice = cDealPrice;
		this.cDealType = cDealType;
		this.accountNo = accountNo;
	}
    

	

    public int getWalNo() {
		return walNo;
	}

	public void setWalNo(int walNo) {
		this.walNo = walNo;
	}

    
    
    public String getcCode() {
        return cCode;
    }
    
    public void setcCode(String cCode) {
        this.cCode = cCode;
    }
    
    public double getcQuantity() {
        return cQuantity;
    }
    
    public void setcQuantity(Integer cQuantity) {
        this.cQuantity = cQuantity;
    }
    
    public double getcDealPrice() {
        return cDealPrice;
    }
    
    public void setcDealPrice(Integer cDealPrice) {
        this.cDealPrice = cDealPrice;
    }
    
    public String getcDealType() {
        return cDealType;
    }
    
    public void setcDealType(String cDealType) {
        this.cDealType = cDealType;
    }
    
    public Date getcDealDate() {
        return cDealDate;
    }
    
    public void setcDealDate(Date cDealDate) {
        this.cDealDate = cDealDate;
    }

    @Override
    public String toString() {
        return "Wallet [walNo=" + walNo + ", cCode=" + cCode + ", cQuantity=" + cQuantity

            + ", cDealPrice=" + cDealPrice + ", cDealType=" + cDealType + ", cDealDate=" + cDealDate + "]";
    }

	public String getAccountNo() {
		return accountNo;
	}

	
}


