 package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entities.Teacher;

public class TeacherService {
	
	//private TeacherDao dao = DaoFactory.createTeacherDao();
	
	public List<Teacher> findAll(){
		List<Teacher> list = new ArrayList<>();
		list.add(new Teacher(1, "Joa", "12345678998", "987654321", new Date(), 5000.00, "nao", "nâo"));
		return list;
	}
		
		
		/*return dao.findAll();		
	
	
	public void saveOrUpadate(Teacher obj) {
		if (obj.getId() == null)
			dao.insert(obj);
		dao.update(obj);
	}
		
	public void remove(Teacher obj) {
		dao.deleteById(obj.getId());
	}*/
}
