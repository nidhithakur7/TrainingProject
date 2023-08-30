package advocateappointment.dao;

import java.sql.SQLException;
import java.util.List;

import advocateappointment.entity.Appointment;
import advocateappointment.exception.AppointmentNotFoundException;

public interface AppointmentDao  {
	public boolean bookAppointment(Appointment obj) throws  SQLException , AppointmentNotFoundException;	
	public boolean modify(Appointment obj) throws SQLException , AppointmentNotFoundException;
	//public boolean delete() throws SQLException , AppointmentNotFoundException;
	public Appointment singleRecord(int id) throws SQLException ,AppointmentNotFoundException;
	public List<Appointment> allRecord(int id) throws SQLException ,AppointmentNotFoundException;
	boolean delete(Appointment obj) throws SQLException, AppointmentNotFoundException;
	
}
