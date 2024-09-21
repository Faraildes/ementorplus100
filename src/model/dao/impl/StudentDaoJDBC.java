package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.StudentDao;
import model.entities.Student;
import model.entities.Turma;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;
	
	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Student obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO student "
					+ "(Name, Email, BirthDate, BaseSalary, TurmaId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setDate(2, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setString(3, obj.getCpf());
			st.setString(4, obj.getPhone());
			st.setInt(5, obj.getPeriod());
			st.setInt(5, obj.getTurma().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Student obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE student "
					+ "SET Name = ?, BirthDate = ?, Cpf = ?, Phone = ?. Period = ?, TurmaId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());			
			st.setDate(2, new java.sql.Date(obj.getBirthDate().getTime()));	
			st.setString(3, obj.getCpf());
			st.setString(1, obj.getPhone());
			st.setInt(1, obj.getPeriod());
			st.setInt(5, obj.getTurma().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM student WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Student findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT student.*,turma.Name as TurName "
					+ "FROM student INNER JOIN turma "
					+ "ON student.TurmaId = turma.Id "
					+ "WHERE student.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Turma tur = instantiateTurma(rs);
				Student obj = instantiateStudent(rs, tur);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Student instantiateStudent(ResultSet rs, Turma tur) throws SQLException {
		Student obj = new Student();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));	
		obj.setBirthDate(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		obj.setCpf(rs.getString("Cpf"));
		obj.setPhone(rs.getString("Phone"));
		obj.setPeriod(rs.getInt("Period"));
		obj.setTurma(tur);
		return obj;
	}

	private Turma instantiateTurma(ResultSet rs) throws SQLException {
		Turma tur = new Turma();
		tur.setId(rs.getInt("TurmaId"));
		tur.setName(rs.getString("TurName"));
		return tur;
	}

	@Override
	public List<Student> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT student.*,turma.Name as TurName "
					+ "FROM student INNER JOIN turma "
					+ "ON student.TurmaId = turma.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Student> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();
			
			while (rs.next()) {
				
				Turma tur = map.get(rs.getInt("TurmaId"));
				
				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				
				Student obj = instantiateStudent(rs, tur);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Student> findByTurma(Turma turma) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT student.*,turma.Name as TurName "
					+ "FROM student INNER JOIN turma "
					+ "ON student.TurmaId = turma.Id "
					+ "WHERE TurmaId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, turma.getId());
			
			rs = st.executeQuery();
			
			List<Student> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();
			
			while (rs.next()) {
				
				Turma tur = map.get(rs.getInt("TurmaId"));
				
				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				
				Student obj = instantiateStudent(rs, tur);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}