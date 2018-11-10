package com.qa.business.service;

public interface ClassroomService {

	String getAll();

	String getClass(Integer idClass);

	String getTrainee(Integer idClass, Integer idTrainee);

	String createClass(String newClass);

	String createTrainee(Integer idClass, String trainee);

	String updateTrainee(Integer idClass, String trainee);

	String updateTrainer(Integer idClass, String trainerName);

	String deleteClass(Integer idClass);

	String deleteTrainee(Integer idClass, Integer idTrainee);
}
