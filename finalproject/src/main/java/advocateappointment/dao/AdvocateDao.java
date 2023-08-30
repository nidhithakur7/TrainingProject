package advocateappointment.dao;

import java.sql.SQLException;
import java.util.List;

import advocateappointment.entity.Advocate;
import advocateappointment.entity.Appointment;
import advocateappointment.exception.AdvocateNotFoundException;
import advocateappointment.exception.AppointmentNotFoundException;

public interface AdvocateDao {
	public List<Advocate> allRecord()throws SQLException,AdvocateNotFoundException;
	public Advocate singleRecord(int id) throws SQLException ,AdvocateNotFoundException;
	public boolean delete(Appointment obj) throws SQLException, AppointmentNotFoundException;
	public boolean modify(Appointment obj) throws SQLException, AppointmentNotFoundException;
	
}
