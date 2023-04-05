package coin.vo;

public class Coin {

    private String cCode;
    private String cName;
    private Integer cNowPrice;

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
    
    public Integer getcNowPrice() {
        return cNowPrice;
    }
    
    public void setcNowPrice(Integer cNowPrice) {
        this.cNowPrice = cNowPrice;
    }

    @Override
    public String toString() {
        return "Coin [cCode=" + cCode + ", cName=" + cName + ", cNowPrice=" + cNowPrice + "]";
    }
}