package advocateappointment.dao;

import java.sql.SQLException;
import java.util.List;

import advocateappointment.entity.Service;
import advocateappointment.exception.ServiceNotFoundException;

public interface ServiceDao {
	public Service singleRecord(int id)throws SQLException , ServiceNotFoundException;
	List<Service>aLLRecord()throws SQLException , ServiceNotFoundException;
 }
