package com.qa.persistence.domain;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50)
	private String trainerName;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Trainee> trainees;

	public Classroom() {
	}

	public Classroom(Integer id, String trainerName, List<Trainee> trainees) {
		this.id = id;
		this.trainerName = trainerName;
		this.trainees = trainees;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public Trainee getTrainee(Integer idTrainee) {
		for (int i = 0; i < this.trainees.size(); i++) {
			if (this.trainees.get(i).getId() == idTrainee) {
				return trainees.get(i);
			}
		}
		return new Trainee();
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public void createTrainee(Trainee trainee) {
		this.trainees.add(trainees.size(), trainee);
	}

	public void updateTrainee(Trainee trainee) {
		if (this.contains(trainee.getId())) {
			int indexWhereIdIs = this.fetchIndex(this.trainees, trainee.getId());
			this.trainees.remove(indexWhereIdIs);
			this.trainees.add(indexWhereIdIs, trainee);
		} else {
			this.createTrainee(trainee);
		}
	}

	public void deleteTrainee(Integer idTrainee) {
		if (this.contains(idTrainee)) {
			int indexWhereIdIs = this.fetchIndex(this.trainees, idTrainee);
			this.trainees.remove(indexWhereIdIs);
		}
	}

	public boolean contains(Integer idTrainee) {
		Predicate<Trainee> hasId = t -> (t.getId() == idTrainee);
		return !trainees.stream().noneMatch(hasId);
	}

	public int fetchIndex(List<Trainee> trainees, Integer idTrainee) {
		for (int i = 0; i < trainees.size(); i++) {
			if (trainees.get(i).getId() == idTrainee) {
				return i;
			}
		}
		return 0;
	}

}
