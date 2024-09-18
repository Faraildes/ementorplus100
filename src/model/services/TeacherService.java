 package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.TeacherDao;
import model.entities.Teacher;

public class TeacherService {
	
	private TeacherDao dao = DaoFactory.createTeacherDao();
	
	public List<Teacher> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpadate(Teacher obj) {
		if (obj.getId() == null)
			dao.insert(obj);
		dao.update(obj);
	}
	/*	
	public void remove(Teacher obj) {
		dao.deleteById(obj.getId());
	}*/
}

