package model.dao;

import java.util.List;

import model.entities.Turma;



public interface TurmaDao {

	void insert(Turma obj);
	void update(Turma obj);
	void deleteById(Integer id);
	Turma findById(Integer id);
	List<Turma> findAll();
}