package advocateappointment.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import advocateappointment.dao.CustomerDao;
import advocateappointment.entity.Customer;
import advocateappointment.exception.CustomerNotFoundException;
import advocateappointment.util.DbUtility;
public class CustomerDaoImpl implements CustomerDao{
	
	private final static String SELECT_ALL = "SELECT * FROM CUSTOMER";
	private final static String SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE c_id=?";
	private final static String DELETE ="DELETE FROM CUSTOMER WHERE c_Id = ?";
	private final static String UPDATE ="UPDATE Customer SET c_Name=?, c_ContactNumber=? ,c_Email=?,c_Address=? where c_id= ?";
	private final static String INSERT = "insert into Customer(c_Id,c_Name,c_Email,c_ContactNumber,c_Address) values(?,?,?,?,?)";
	private Connection connection = DbUtility.getConnection();


	@Override
	public boolean insert(Customer obj) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = connection.prepareStatement(INSERT);
		st.setInt(1, obj.getC_Id());
		st.setString(2, obj.getC_Name());
		st.setString(3, obj.getC_EmailId());
		st.setString(4, obj.getC_ContactNumber());
		st.setString(5, obj.getC_Address());
		//System.out.println("Function to call hua h");
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		//System.out.println("Nhi hua update");
		st.close();
		return false;
	}

	@Override
	public boolean modify(Customer obj) throws SQLException, CustomerNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st= connection.prepareStatement(UPDATE);
		//st.setInt(1, obj.getC_Id());
		st.setString(1, obj.getC_Name());
		st.setString(2,obj.getC_ContactNumber());
		st.setString(3,obj.getC_EmailId());
		st.setString(4,obj.getC_Address());
		st.setInt(5, obj.getC_Id());
		System.out.println(obj);
		System.out.println(st);
		
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException, CustomerNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st= connection.prepareStatement(DELETE);
		st.setInt(1, id);
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		st.close();
		return false;
		
	}

	@Override
	public List<Customer> allRecord() throws SQLException {
		// TODO Auto-generated method stub
		List<Customer> customerList =new ArrayList<>();
		PreparedStatement st= connection.prepareStatement(SELECT_ALL);
		ResultSet rs= st.executeQuery();
		while(rs.next()) {
			Customer obj = new Customer(rs.getInt("c_Id"), rs.getString("c_Name"), rs.getString("c_ContactNumber"), rs.getString("c_Email"), rs.getString("c_Address"));
			customerList.add(obj);
		}
		rs.close();
		st.close();
		return customerList;
	}

	@Override
	public Customer singleRecord(int id) throws SQLException ,CustomerNotFoundException{
		// TODO Auto-generated method stub
		PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);
		st.setInt(1,id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Customer obj = new Customer(rs.getInt("c_id"),rs.getString("c_Name"),rs.getString("c_ContactNumber"),rs.getString("c_Email"),rs.getString("c_Address"));
			st.close();
			return obj;
		}
		else {
			System.out.println("No Record Found With Id :- "+id);
		}
		
		
		return null;
	}
}
