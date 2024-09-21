package model.dao;

import db.DB;
import model.dao.impl.TeacherDaoJDBC;
import model.dao.impl.TurmaDaoJDBC;
import model.dao.impl.StudentDaoJDBC;

public class DaoFactory {

	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC(DB.getConnection());
	}
	
	public static TeacherDao createTeacherDao() {
		return new TeacherDaoJDBC(DB.getConnection());
	}
	
	public static TurmaDao createTurmaDao() {
		return new TurmaDaoJDBC(DB.getConnection());
	}
}
