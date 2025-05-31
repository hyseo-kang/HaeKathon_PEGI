import java.util.Map;

public class GradualStatus {
	private int gradualCredit;
	private int curCredit;
	private int remainCredit;
	private Map<String, Integer> semPerCredit;
	private double remainPercent;
	private int retakenSub;
	
	public int getGCredit() {
		return gradualCredit;
	}
	public void setGCredit(int gradualCredit) {
		this.gradualCredit = gradualCredit;
	}
	
	public int getCurCredit() {
		return curCredit;
	}
	public void setCurCredit(int curCredit) {
		this.curCredit = curCredit;
	}
	
	public int getRemainCredit() {
		return remainCredit;
	}
	public void setRemainCredit(int remainCredit) {
		this.remainCredit = remainCredit;
	}
	
	public Map<String, Integer> getSemPerCredit() {
		return semPerCredit;
	}
	public void setSemPerCredit(Map<String, Integer> semPerCredit) {
		this.semPerCredit = semPerCredit;
	}
	
	public double getRemainPercent() {
		return remainPercent;
	}
	public void setRemainPercent(double remainPercent) {
		this.remainPercent = remainPercent;
	}
	
	public int getRetaken() {
		return retakenSub;
	}
	public void setRetaken(int retakenSub) {
		this.retakenSub = retakenSub;
	}
}
