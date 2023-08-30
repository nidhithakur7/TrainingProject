package advocateappointment.entity;

public class Appointment {
	private int ap_Id;
	private int c_Id;
	private int a_Id;
    private int s_Id;  	
	private String ap_Date;
	public Appointment(int ap_Id, int c_Id, int a_Id, int s_Id, String ap_Date) {
		super();
		this.ap_Id = ap_Id;
		this.c_Id = c_Id;
		this.a_Id = a_Id;
		this.s_Id = s_Id;
		this.ap_Date = ap_Date;
	}
	
	public String getAp_Date() {
		return ap_Date;
	}
	public void setAp_Date(String ap_Date) {
		this.ap_Date = ap_Date;
	}
	public int getA_Id() {
		return a_Id;
	}
	public void setA_Id(int a_Id) {
		this.a_Id = a_Id;
	}
	public int getS_Id() {
		return s_Id;
	}
	public void setS_Id(int s_Id) {
		this.s_Id = s_Id;
	}
	public int getC_Id() {
		return c_Id;
	}
	public void setC_Id(int c_Id) {
		this.c_Id = c_Id;
	}
	public int getAp_Id() {
		return ap_Id;
	}
	public void setAp_Id(int ap_Id) {
		this.ap_Id = ap_Id;
	}
	@Override
	public String toString() {
		return "Appointment [ap_Id=" + ap_Id + ", c_Id=" + c_Id + ", s_Id=" + s_Id + ", a_Id=" + a_Id + ", ap_Date="
				+ ap_Date + "]";
	}
	
}
