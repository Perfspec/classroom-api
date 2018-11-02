package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Classroom;
import com.qa.persistence.domain.Trainee;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class ClassroomDBRepository implements ClassroomRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;
	


	@Override
	public String getAll() {
		Query query = manager.createQuery("Select a FROM Classroom a");
		Collection<Classroom> allClasses = (Collection<Classroom>) query.getResultList();
		return util.getJSONForObject(allClasses);
	}
	
	@Override
	public String getClass(Integer idClass) {
		Classroom aClass = findClass(idClass);
		return util.getJSONForObject(aClass);
	}

	@Override
	public String getTrainee(Integer idClass, Integer idTrainee) {
		Classroom aClass = findClass(idClass);
		Trainee aTrainee = aClass.getTrainee(idTrainee);
		return util.getJSONForObject(aTrainee);
	}

	@Override
	@Transactional(REQUIRED)
	public String createClass(String newClass) {
		Classroom aClass = util.getObjectForJSON(newClass, Classroom.class);
		manager.persist(aClass);
		return "{\"message\": \"classroom has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String createTrainee(Integer idClass, String newTrainee) {
		Classroom aClass = findClass(idClass);
		Trainee aTrainee = util.getObjectForJSON(newTrainee, Trainee.class);
		aClass.createTrainee(aTrainee);
		manager.refresh(aClass);
		return "{\"message\": \"trainee has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateTrainee(Integer idClass, String newTrainee) {
		Classroom aClass = findClass(idClass);
		Trainee aTrainee = util.getObjectForJSON(newTrainee, Trainee.class);
		aClass.updateTrainee(aTrainee);
		manager.refresh(aClass);
		return "{\"message\": \"trainee has been sucessfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateTrainer(Integer idClass, String trainerName) {
		Classroom aClass = findClass(idClass);
		aClass.setTrainerName(trainerName);
		manager.refresh(aClass);
		return "{\"message\": \"trainer has been sucessfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteClass(Integer idClass) {
		Classroom aClass = findClass(idClass);
		if(aClass!=null) {
			manager.remove(aClass);
		}
		return "{\"message\": \"trainee has been sucessfully deleted\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteTrainee(Integer idClass, Integer idTrainee) {
		Classroom aClass = findClass(idClass);
		aClass.deleteTrainee(idTrainee);
		manager.refresh(aClass);
		return "{\"message\": \"trainee has been sucessfully deleted\"}";
	}
	
	private Classroom findClass(Integer id) {
		return manager.find(Classroom.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	

	
}
