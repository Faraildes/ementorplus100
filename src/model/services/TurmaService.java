package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TurmaDao;
import model.entities.Turma;

public class TurmaService {

	private TurmaDao dao = DaoFactory.createTurmaDao();
	
	public List<Turma> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Turma obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Turma obj) {
		dao.deleteById(obj.getId());
	}
}