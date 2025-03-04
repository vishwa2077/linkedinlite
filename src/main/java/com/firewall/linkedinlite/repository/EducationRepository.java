package com.firewall.linkedinlite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.firewall.linkedinlite.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Integer>{

	@Query("select e from Education e where e.percentage > ?1 ")
	List<Education> searchMorethanpercentage(double  percen);

	@Query("select e from Education e where e.percentage < :value ")
	List<Education> searchLessthanPercentage(@Param("value") double percen);

	@Query("select e from Education e where e.yearOfPassedOut = ?1")
	List<Education> searchPassedoutYear( int  year);

	@Query("select e from Education e where e.universityName = ?1")
	List<Education> searchUniversityName(String uniname);
	
	@Query("select e from Education e where e.qualification = ?1")
	List<Education> searchQualification(String qualifi);
	
	@Query("select e from Education e where e.stream = ?1")
	List<Education> searchStream(String stream);

	@Query("select e from Education e where e.status = 'ACTIVE'")
	List<Education> findAllActiveEducation();
	
	@Query("select e from Education e where e.status = 'IN_ACTIVE'")
	List<Education> findAllInactiveEducation();
	
	@Query("select e from Education e where e.highestQualification = 'YES'")
	List<Education> findAllHqYesEducation();
	
	@Query("select e from Education e where e.highestQualification = 'NO'")
	List<Education> findAllHqNoEducation();

	
}
