package com.firewall.linkedinlite.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.entity.Education;
import com.firewall.linkedinlite.repository.EducationRepository;
@Component
public class EducationDao {
	@Autowired
	private EducationRepository repo;

	public Education saveEducation(Education e) {
		return repo.save(e);
	}

	public List<Education> findAllEducation() {
		return repo.findAll();
	}

	public Optional<Education> findEduccationById(int edid) {
		return repo.findById(edid);
	}


	public List<Education> searchMorethanpercentage(double percen) {
		return repo.searchMorethanpercentage(percen);
	}

	public List<Education> searchLessthanPercentage(double percen) {
		return repo.searchLessthanPercentage(percen);
	}

	public List<Education> searchPassedoutYear(int year) {
		return repo.searchPassedoutYear(year);
	}

	public List<Education> searchUniversityName(String uniname) {
		return repo.searchUniversityName(uniname);
	}
	
	public List<Education> searchQualification(String qualifi) {
		return repo.searchQualification(qualifi);
	}
	
	public List<Education> searchStream(String stream) {
		return repo.searchStream(stream);
	}

	public List<Education> findAllActiveEducation() {
		return repo.findAllActiveEducation();
	}

	public List<Education> findAllInactiveEducation() {
		return repo.findAllInactiveEducation();
	}

	public List<Education> findAllHqYesEducation() {
		return repo.findAllHqYesEducation();
	}
	public List<Education> findAllHqNoEducation() {
		return repo.findAllHqNoEducation();
	}
}
