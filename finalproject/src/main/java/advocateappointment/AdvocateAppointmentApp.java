package advocateappointment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import advocateappointment.dao.AdvocateDao;
import advocateappointment.dao.AppointmentDao;
import advocateappointment.dao.CustomerDao;
import advocateappointment.dao.ServiceDao;
import advocateappointment.dao.impl.AdvocateDaoImpl;
import advocateappointment.dao.impl.AppointmentDaoImpl;
import advocateappointment.dao.impl.CustomerDaoImpl;
import advocateappointment.dao.impl.ServiceDaoImpl;
import advocateappointment.entity.Advocate;
import advocateappointment.entity.Appointment;
import advocateappointment.entity.Customer;
import advocateappointment.entity.Service;
import advocateappointment.exception.AdvocateNotFoundException;
import advocateappointment.exception.AppointmentNotFoundException;
import advocateappointment.exception.CustomerNotFoundException;
import advocateappointment.exception.ServiceNotFoundException;

public class AdvocateAppointmentApp 
{
	public static CustomerDao customerDao=new CustomerDaoImpl();
	public static AdvocateDao advocateDao=new AdvocateDaoImpl();
	public static ServiceDao serviceDao=new ServiceDaoImpl();
	public static AppointmentDao appointmentDao =new AppointmentDaoImpl();
	public static  Scanner scanner =new Scanner(System.in);
    public static void main( String[] args ) throws AdvocateNotFoundException, SQLException, AppointmentNotFoundException, CustomerNotFoundException, ServiceNotFoundException
    {
        
    	while(true) {
    		System.out.println("\n*****MAIN MENU*****\n"+"1. Customer\r\n"
    				+ "2. Advocate\r\n"
    				+ "3. Appointment\r\n"
    				+ "4. Service\r\n"
    				+ "0. Exit\r\n");
    		
    		int choice = Integer.parseInt(scanner.nextLine());
    		switch(choice) {
    		case 1:
    			System.out.println("** Starting Customer Services **\n");
    			customerServices();
    			System.out.println("** Ending Customer Services **\n");
    			//break;
    		case 2:
    			System.out.println("** Staring Advocate Seevices **\n");
    			advocateServices();
    			System.out.println("** Ending Advocate  Seevices **\n");
    			//break;
    		case 3:
    			break;
    		case 4:
    			break;
    		case 0:
    			System.exit(0);  
    		default:
    			System.exit(0);
    		};
    		
    		
    	}
    }
    public static void allAppointment()throws SQLException,AppointmentNotFoundException{
    	System.out.println("\n Printing All Appointments \n");
    	try {
			List<Appointment> allApp= appointmentDao.allRecord(0);
			for(Appointment i: allApp)System.out.println(i+"\n");
		} catch (AppointmentNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("\n No Data\n");
			System.err.println(e);
			//e.printStackTrace();
		}
    	
    }
    //*********************APPOINTMENT METHOD********************************
    public static void singleAppointment() throws SQLException,AppointmentNotFoundException{
    	System.out.println("Enter The Appointment Id \n");
    	int id= scanner.nextInt();
    		Appointment obj;
			try {
				obj = appointmentDao.singleRecord(id);
				System.out.println(obj);
			} catch (AppointmentNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("No Data Found With Id "+id);
				System.err.println(e);
				//e.printStackTrace();
			}
    		return ;
    }
    public static void deleteAppointment() throws SQLException,AppointmentNotFoundException{
    	System.out.println("\n Enter Appointment Id To Delete");
    	int id = scanner.nextInt();
    	try {
    		if(appointmentDao.delete(appointmentDao.singleRecord(id))){
    			System.out.println("\n Data Has Been Deleted \n");
    		}
    	}catch(SQLException | AppointmentNotFoundException e) {
    		System.out.println("Data Has Not Been Deleted \n");
    		System.err.println(e);
    	}
    }
    public static void modifyAppointment() throws SQLException ,AppointmentNotFoundException{
    	System.out.println("\n Enter Appointment Id To Modify Details \n");
    	int ap_Id= scanner.nextInt();
    	System.out.println("\n Enter New Customer Id To Modify \n");
    	int c_Id= scanner.nextInt();
    	System.out.println("\n Enter New Advocate Id To Modify \n");
    	int a_Id= scanner.nextInt();
    	System.out.println("\n Enter New Service Id To Modify  \n");
    	
    	int s_Id= scanner.nextInt();
    	System.out.println("\n Enter New Appointment Date To Modify\n");
    	scanner.nextLine();
    	String ap_Date= scanner.nextLine();
    	Appointment obj = new Appointment(ap_Id,c_Id,a_Id,s_Id,ap_Date);
    	try {
    		if(appointmentDao.modify(obj)) {
    			System.out.println("\n Updation Has Been Done \n");
    			return ;
    		}
    	}catch(SQLException |AppointmentNotFoundException e) {
    		System.err.println(e);
    	}
    	System.out.println("\n Updation Has Not Been Performed \n");
    	return ;
    	
    	
    	
    }
    public static void bookAppointment() throws SQLException,AppointmentNotFoundException, CustomerNotFoundException,
    AdvocateNotFoundException, ServiceNotFoundException {

    	System.out.println("\n Enter Appointment Id To Book Appointment \n");
    	int ap_Id=scanner.nextInt();    	
    	System.out.println("\n Enter Customer Id To Book Appointment \n");
    	int c_Id=scanner.nextInt();
    	Customer c_Obj = customerDao.singleRecord(c_Id);
    	System.out.println("\n Enter Advocate Id To Book Appointment \n");
    	int a_Id=scanner.nextInt();
    	Advocate a_Obj = advocateDao.singleRecord(a_Id);
    	System.out.println("\n Enter Service Id To Book Appointment \n");
    	int s_Id=scanner.nextInt();
    	//Service s_Obj = serviceDao.singleRecord(s_Id);
    	scanner.nextLine();
    	System.out.println("\n Enter Date To Book Appointment \n");
    	//System.out.println("Yaha tak chal agya date daal bc\n");
    	String ap_Date= scanner.nextLine();
    	//System.out.println("Date k baad ni chal rha \n");
    	Appointment obj= new Appointment(ap_Id,c_Obj.getC_Id(),a_Obj.getA_Id(),s_Id,ap_Date);
    	//Appointment obj= new Appointment(ap_Id,c_Id,a_Id,s_Id,ap_date);
    	try {
    		if(appointmentDao.bookAppointment(obj)) {
    			System.out.println("\n Data Has Been Inserted \n");
    		}
    	}catch(SQLException |AppointmentNotFoundException  e) {
    		System.err.println(e);
    	}
    	
    	return ;
    }
    
    //********************* ADVOCATE METHODS***********************************
    public static void a_AllRecord() throws AdvocateNotFoundException {
    	System.out.println("\n Showing All Records Of Advocates \n");
    	try {
    		List<Advocate> advocateList = advocateDao.allRecord();
    		for (Advocate i: advocateList ) {
    			System.out.println(i+"\n");
    		}
    		
    	}catch(SQLException e) {
    		System.err.println(e);
    	}
    	
    	
    	return ;
    }
    public static void a_SingleRecord() {
    	System.out.println("\n** Enter Advocate Id **\n");
    	int id = scanner.nextInt();
    	try {
    		Advocate obj;
			try {
				obj = advocateDao.singleRecord(id);
				System.out.println(obj);
			} catch (AdvocateNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}catch(SQLException e) {
    		System.err.println(e);
    	}
    }
//****************************SERVICE METHODS******************************** 
    public static void s_AllRecord()  {
    	List<Service> service =new ArrayList<>();
    	try {
    		service = serviceDao.aLLRecord();
    		for(Service i:service)System.out.println(i+"\n");
    	}
    	catch(SQLException |ServiceNotFoundException e) {
    		System.err.println(e);
    	}
    	
    }
    public static void advocateServices() throws AdvocateNotFoundException, SQLException, AppointmentNotFoundException, CustomerNotFoundException, ServiceNotFoundException {
    	while(true) {
    		System.out.println("1. Book an appointment\r\n"
    				+ "2. Modify appointment details\r\n"
    				+ "3. Delete an appointment\r\n"
    				+ "4. View single record\r\n"
    				+ "5. View all records\r\n"
    				+ "0. Exit\r\n");
    		int choice = Integer.parseInt(scanner.nextLine());
    		switch(choice) {
    		case 1:
    			System.out.println(" \r\n **Statring Book Appointment Services **\r\n");
    			bookAppointment();
    			System.out.println("\r\n **Ending Book Appointment Services **\r\n");
    			break;
    		case 2:
    			System.out.println(" \r\n **Statring Modify Appointment Services **\r\n");
    			modifyAppointment();
    			System.out.println("\r\n **Ending Modify Appointment Services **\r\n");
    			break;
    		case 3:
    			System.out.println(" \r\n **Statring Delete Appointment Services **\r\n");
    			deleteAppointment();
    			System.out.println("\r\n **Ending Delete Appointment Services **\r\n");
    			break;
    		case 4:
    			System.out.println(" \r\n **Statring View Single Record Services **\r\n");
    			singleAppointment();
    			System.out.println("\r\n **Ending View Single Records Services **\r\n");
    			break;
    		case 5:
    			System.out.println(" \r\n **Statring View All Record Services **\r\n");
    			//sAllRecord();
    			allAppointment();
    			System.out.println("\r\n **Ending View All Records Services **\r\n");
    			break;
    		case 0:
    			return ;
    			
    		}
    	}
    }
    
//*************************CUSTOMER METHODA******************************
    public static void c_Delete() {
    	scanner.nextLine();
    	System.out.println("\n **Enter Customer Id To Delete \n");
    	int c_Id= scanner.nextInt();
    	
    	try {
    		if(customerDao.delete(c_Id)) {
    			System.out.println("\n Data has been deleted \n");
    		}else {
    			System.out.println("\n No Data has been Found With Id :- "+c_Id+"\n");
    		}
    	} catch(SQLException | CustomerNotFoundException e) {
    		System.err.println(e);
    	}
    	return ;
    }
    public static void c_Register() {
 
    	scanner.nextLine();
    	System.out.println("\n **Enter Customer Name \n");
    	
    	String Name = scanner.nextLine();
    	
    	System.out.println("\n **Enter Customer Id \n");
    	int id = Integer.parseInt(scanner.nextLine());
    	System.out.println("\n **Enter Customer Email Address \n");
    	String email= scanner.nextLine();
    	System.out.println("\n **Enter Customer Number \n");
    	String number= scanner.nextLine();
    	System.out.println("\n **Enter Customer Address \n");
    	String address= scanner.nextLine();
    	
    	Customer obj = new Customer(id,Name,number,email,address);
    	//Connection connection = DbUtility.getConnection();
    	try {
			if(customerDao.insert(obj)) {
				System.out.println("Inserted");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
    	
    	//System.out.println(obj);
    	
    	
    	
    }
    public static void c_Modify() {
    	System.out.println("**\n Enter Customer Id To Modify Data \n**");
    	int id= scanner.nextInt();
    	System.out.println("\n Enter Name of Customer to update \n");
    	scanner.nextLine();
    	String n_Name = scanner.nextLine();
    	System.out.println("\n Enter Contact Number  of Customer to update \n");
    	String n_ContactNumber= scanner.nextLine();
    	System.out.println("\n Enter Email Address of Customer to update \n");
    	String n_Email= scanner.nextLine();
    	System.out.println("\n Enter Address of Customer to update \n");
    	String n_Address = scanner.nextLine();
    	Customer obj =new Customer(id,n_Name,n_ContactNumber,n_Email,n_Address);
    	try {
    		if(customerDao.modify(obj)) {
    			System.out.println("\n Data has been Updated \n");
    		}else {
    			System.out.println("\n Data has not been Updated\n");
    			}
    	}catch(SQLException | CustomerNotFoundException e){
    		System.err.println(e);
    		
    	}
 
    	
    	
    	return ;
    	
    }
    public static void c_SingleRecord() {
    	System.out.println("\n** Enter Customer Id **\n");
    	int id = scanner.nextInt();
    	try {
    		Customer obj = customerDao.singleRecord(id);
    		System.out.println(obj);
    		
    	}catch(SQLException  | CustomerNotFoundException e) {
    		System.err.println(e);
    	}
    	
    	
    }
    public static void c_AllRecord() {
    	System.out.println("\n Showing All Records \n");
    	try {
    		List<Customer> customerList = customerDao.allRecord();
    		for (Customer i: customerList ) {
    			System.out.println(i+"\n");
    		}
    		
    	}catch(SQLException e) {
    		System.err.println(e);
    	}
    	return ;
    }
    public static void customerServices() {
    	//Scanner scanner = new Scanner(System.in);
    	while(true) {
    		System.out.println("1. Register Customer\r\n"
    				+ "2. Modify Customer details\r\n"
    				+ "3. Delete Customer record\r\n"
    				+ "4. View single record\r\n"
    				+ "5. View all records\r\n"
    				+ "0. Exit\r\n");
    		int choice =Integer.parseInt(scanner.nextLine());
    		switch(choice) {
    		case 1:
    			System.out.println("** Starting Registering Services **\n");
    			c_Register();
    			System.out.println("** Ending Registering Services **\n");
    			break;
    		case 2:
    			System.out.println("** Starting Modifying Services **\n");
    			c_Modify();
    			System.out.println("** Ending Registering Services **\n");
    			break;
    		case 3:
    			System.out.println("** Starting Deleting Services **\n");
    			c_Delete();
    			System.out.println("** Ending Deleting Services **\n");
    			break;
    		case 4:
    			System.out.println("** Starting View Single Record Services **\n");
    			c_SingleRecord();
    			System.out.println("** Ending View Single Record Services **\n");
    			break;
    		case 5:
    			System.out.println("** Starting View All Record Services **\n");
    			c_AllRecord();
    			System.out.println("** Ending View All Record Services **\n");
    			break;
    		case 0:
    			System.out.println("**Exiting services**");
    			//System.exit(0);
    			return;
    			    		
    		};
    	}
    	
    	
    }
    
	
}
