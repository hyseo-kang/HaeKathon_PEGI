import java.util.Map;

public class GradualStatus {
	private double gradualCredit;
	private double curCredit;
	private double remainCredit;
	private Map<String, Double> semPerCredit;
	private double remainPercent;
	private int retakenSub;
	
	public double getGCredit() {
		return gradualCredit;
	}
	public void setGCredit(double gradualCredit) {
		this.gradualCredit = gradualCredit;
	}
	
	public double getCurCredit() {
		return curCredit;
	}
	public void setCurCredit(double curCredit) {
		this.curCredit = curCredit;
	}
	
	public double getRemainCredit() {
		return remainCredit;
	}
	public void setRemainCredit(double remainCredit) {
		this.remainCredit = remainCredit;
	}
	
	public Map<String, Double> getSemPerCredit() {
		return semPerCredit;
	}
	public void setSemPerCredit(Map<String, Double> semPerCredit) {
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