package coin.vo;

public class Wallet {

    private Integer Id;
    private Integer walNo;
    private String cCode;
    private Integer cQuantity;
    private Integer cDealPrice;
    private String cDealType;
    private String cDealDate;

    public Integer getId() {
        return Id;
    }
    
    public void setId(Integer id) {
        Id = id;
    }
    
    public Integer getWalNo() {
        return walNo;
    }
    
    public void setWalNo(Integer walNo) {
        this.walNo = walNo;
    }
    
    public String getcCode() {
        return cCode;
    }
    
    public void setcCode(String cCode) {
        this.cCode = cCode;
    }
    
    public Integer getcQuantity() {
        return cQuantity;
    }
    
    public void setcQuantity(Integer cQuantity) {
        this.cQuantity = cQuantity;
    }
    
    public Integer getcDealPrice() {
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
    
    public String getcDealDate() {
        return cDealDate;
    }
    
    public void setcDealDate(String cDealDate) {
        this.cDealDate = cDealDate;
    }

    @Override
    public String toString() {
        return "Wallet [Id=" + Id + ", walNo=" + walNo + ", cCode=" + cCode + ", cQuantity=" + cQuantity

            + ", cDealPrice=" + cDealPrice + ", cDealType=" + cDealType + ", cDealDate=" + cDealDate + "]";
    }
}


