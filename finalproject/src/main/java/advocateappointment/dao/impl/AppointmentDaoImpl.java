package advocateappointment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import advocateappointment.dao.AppointmentDao;
import advocateappointment.entity.Appointment;
import advocateappointment.entity.Customer;
import advocateappointment.exception.AppointmentNotFoundException;
import advocateappointment.util.DbUtility;

public  class AppointmentDaoImpl implements AppointmentDao{
	private final static String INSERT="INSERT INTO APPOINTMENT VALUES(?,?,?,?,?)";
	private final static String SELECT_BY_ID="SELECT * FROM APPOINTMENT WHERE ap_Id=?";
	private final static String DELETE="DELETE FROM APPOINTMENT WHERE ap_Id=?";
	private final static String UPDATE="UPDATE APPOINTMENT SET c_Id =? ,a_Id=?,s_Id=?,ap_Date=? where ap_Id=?";
	private final static String SELECT_ALL="SELECT * FROM APPOINTMENT ";
	Connection connection =DbUtility.getConnection();
	

	@Override
	public boolean bookAppointment(Appointment obj) throws SQLException, AppointmentNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st = connection.prepareStatement(INSERT);
		st.setInt(1, obj.getAp_Id());
		st.setInt(2, obj.getC_Id());
		st.setInt(3, obj.getA_Id());
		st.setInt(4, obj.getS_Id());
		st.setString(5, obj.getAp_Date());
		
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		st.close();
		return false;
	}

	@Override
	public boolean modify(Appointment obj) throws SQLException, AppointmentNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st= connection.prepareStatement(UPDATE);		
		st.setInt(1,obj.getC_Id());
		st.setInt(2,obj.getA_Id());
		st.setInt(3,obj.getS_Id());
		st.setString(4,obj.getAp_Date());
		st.setInt(5,obj.getAp_Id());
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Appointment obj) throws SQLException, AppointmentNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st= connection.prepareStatement(DELETE);
		st.setInt(1, obj.getAp_Id());
		if(st.executeUpdate()>0) {
			st.close();
			return true;
		}
		st.close();
		return false;
	}

	@Override
	public Appointment singleRecord(int id) throws SQLException, AppointmentNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);
		st.setInt(1,id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Appointment obj = new Appointment(rs.getInt("ap_Id"),rs.getInt("c_Id"),rs.getInt("a_Id"),rs.getInt("s_Id"),rs.getString("ap_Date"));
			st.close();
			return obj;
		}		
		
		return null;
	}

	@Override
	public List<Appointment> allRecord(int id) throws SQLException, AppointmentNotFoundException {
		// TODO Auto-generated method stub
		List<Appointment> appointmentList =new ArrayList<>();
		PreparedStatement st= connection.prepareStatement(SELECT_ALL);
		ResultSet rs= st.executeQuery();
		while(rs.next()) {
			Appointment obj = new Appointment(rs.getInt("ap_Id"),rs.getInt("c_Id"),rs.getInt("a_Id"),rs.getInt("s_Id"),rs.getString("ap_Date"));
			appointmentList.add(obj);
		}
		rs.close();
		st.close();
		return appointmentList;
		//return null;
	}

}
