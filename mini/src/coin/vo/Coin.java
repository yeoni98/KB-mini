package coin.vo;

public class Coin {

    private String cCode;
    private String cName;
    private int cNowPrice;


	public Coin(String cCode, String cName, int cNowPrice) {
		super();
		this.cCode = cCode;
		this.cName = cName;
		this.cNowPrice = cNowPrice;
	}

	public String getcCode() {
        return cCode;
    }
    
    public void setcCode(String cCode) {
        this.cCode = cCode;
    }
    
    public String getcName() {
        return cName;
    }
    
    public void setcName(String cName) {
        this.cName = cName;
    }
    
    public int getcNowPrice() {
        return cNowPrice;
    }
    
    public void setcNowPrice(int cNowPrice) {
        this.cNowPrice = cNowPrice;
    }

    @Override
    public String toString() {
        return "Coin [cCode=" + cCode + ", cName=" + cName + ", cNowPrice=" + cNowPrice + "]";
    }
}