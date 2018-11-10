package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.ClassroomRepository;

public class ClassroomServiceImpl implements ClassroomService {

	@Inject
	private ClassroomRepository repo;

	@Override
	public String getAll() {
		return repo.getAll();
	}

	@Override
	public String getClass(Integer idClass) {
		return repo.getClass(idClass);
	}

	@Override
	public String getTrainee(Integer idClass, Integer idTrainee) {
		return repo.getTrainee(idClass, idTrainee);
	}

	@Override
	public String createClass(String newClass) {
		return repo.createClass(newClass);
	}

	@Override
	public String createTrainee(Integer idClass, String trainee) {
		return repo.createTrainee(idClass, trainee);
	}

	@Override
	public String updateTrainee(Integer idClass, String trainee) {
		return repo.updateTrainee(idClass, trainee);
	}

	@Override
	public String updateTrainer(Integer idClass, String trainerName) {
		return repo.updateTrainer(idClass, trainerName);
	}

	@Override
	public String deleteClass(Integer idClass) {
		return repo.deleteClass(idClass);
	}

	@Override
	public String deleteTrainee(Integer idClass, Integer idTrainee) {
		return repo.deleteTrainee(idClass, idTrainee);
	}

}
