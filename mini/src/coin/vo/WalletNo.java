package coin.vo;

public class WalletNo {
	int walNo;

	public WalletNo(int walNo) {
		super();
		this.walNo = walNo;
	}

	public int getWalNo() {
		return walNo;
	}

	public void setWalNo(int walNo) {
		this.walNo = walNo;
	}

	@Override
	public String toString() {
		return "Wallet [walNo=" + walNo + "]";
	}
}
