package advocateappointment.entity;

public class Service {
	private int s_Id;
	private String s_Name;
	private String s_Discription;
	private int s_Charge;
	public int getS_id() {
		return s_Id;
	}
	public void setS_id(int s_id) {
		this.s_Id = s_id;
	}
	public String getS_Name() {
		return s_Name;
	}
	public void setS_Name(String s_Name) {
		this.s_Name = s_Name;
	}
	public String getS_Discription() {
		return s_Discription;
	}
	public void setS_Discription(String s_Discription) {
		this.s_Discription = s_Discription;
	}
	public int getS_Charge() {
		return s_Charge;
	}
	public void setS_Charge(int s_Charge) {
		this.s_Charge = s_Charge;
	}
	public Service(int s_id, String s_Name, String s_Discription, int s_Charge) {
		super();
		this.s_Id = s_id;
		this.s_Name = s_Name;
		this.s_Discription = s_Discription;
		this.s_Charge = s_Charge;
	}
	@Override
	public String toString() {
		return "Service [s_id=" + s_Id + ", s_Name=" + s_Name + ", s_Discription=" + s_Discription + ", s_Charge="
				+ s_Charge + "]";
	}
	public int getS_Id() {
		return s_Id;
	}
	public void setS_Id(int s_Id) {
		this.s_Id = s_Id;
	}
	
}
