package advocateappointment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import advocateappointment.dao.ServiceDao;
import advocateappointment.entity.Service;
import advocateappointment.exception.ServiceNotFoundException;
import advocateappointment.util.DbUtility;

public class ServiceDaoImpl implements ServiceDao{

	private final static String SELECT_ALL="SELECT * FROM SERVICES";
	private final static String SELECT_BY_ID = "SELECT * FROM SERVICES WHERE s_Id=?";
	private Connection connection = DbUtility.getConnection();
	

	@Override
	public Service singleRecord(int id) throws SQLException, ServiceNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st= connection.prepareStatement(SELECT_BY_ID);
		st.setInt(1, id);
		ResultSet rs= st.executeQuery();
		if(rs.next()) {
			Service obj = new Service(rs.getInt("s_Id"),rs.getString("s_Name"),rs.getString("s_Discription"),rs.getInt("s_Charge"));
			st.close();
			rs.close();
			return obj;
		}
		st.close();
		rs.close();
		return null;
	}

	@Override
	public List<Service> aLLRecord() throws SQLException, ServiceNotFoundException {
		// TODO Auto-generated method stub
		List<Service> services = new ArrayList<>();
		PreparedStatement st= connection.prepareStatement(SELECT_ALL);
		ResultSet rs= st.executeQuery();
		
		while(rs.next()) {
			Service obj =new Service(rs.getInt("s_Id"),rs.getString("s_Name"),rs.getString("s_Discreption"),rs.getInt("s_Charge"));
			services.add(obj);
			
		}
		st.close();
		rs.close();
		
		
		return services;
	}

}
