package com.firewall.linkedinlite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firewall.linkedinlite.entity.Education;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;
import com.firewall.linkedinlite.service.EducationService;

@RestController
@RequestMapping("educations")
public class EducationController {
	@Autowired
	private EducationService service;

	@PostMapping("/{emid}")
	public ResponseStructure<Education> saveEducation(@RequestBody Education education, @PathVariable int emid) {
		return service.saveEducation(education, emid);
	}

	@GetMapping
	public ResponseStructure<List<Education>> FindAllEducation() {
		return service.findAllEducation();
	}

	@GetMapping("/{edid}")
	public ResponseStructure<Education> findEducationById(@PathVariable int edid) {
		return service.findEducationById(edid);
	}

	@GetMapping("/employee/{emid}")
	public ResponseStructure<List<Education>> findAllEducationByEmpId(@PathVariable int emid) {
		return service.findAllEducationByEmpId(emid);
	}

	@PatchMapping("/status/inactive/{edid}")
	public ResponseStructure<Education> updateStatusToInactive(@PathVariable int edid) {
		return service.updateStatusToInactive(edid);
	}

	@PatchMapping("/status/active/{edid}")
	public ResponseStructure<Education> updateStatusToactive(@PathVariable int edid) {
		return service.updateStatusToactive(edid);
	}

	@PatchMapping("/hq/no/{edid}")
	public ResponseStructure<Education> updateHQtoNo(@PathVariable int edid) {
		return service.updateHQtoNo(edid);
	}

	@PatchMapping("/hq/yes/{edid}")
	public ResponseStructure<Education> updateHQtoYes(@PathVariable int edid) {
		return service.updateHQtoYes(edid);
	}

	@GetMapping("/search/morethan/{percen}")
	public ResponseStructure<List<Education>> searchMorethanpercentage(@PathVariable double percen) {
		return service.searchMorethanpercentage(percen);
	}

	@GetMapping("/search/lessthan/{percen}")
	public ResponseStructure<List<Education>> searchLessthanPercentage(@PathVariable double percen) {
		return service.searchLessthanPercentage(percen);
	}

	@GetMapping("/search/passedout/{year}")
	public ResponseStructure<List<Education>> searchPassedoutYear(@PathVariable int year) {
		return service.searchPassedoutYear(year);
	}

	@GetMapping("/search/universityname/{uniname}")
	public ResponseStructure<List<Education>> searchUniversityName(@PathVariable String uniname) {
		return service.searchUniversityName(uniname);
	}

	@GetMapping("/search/qualification/{qualifi}")
	public ResponseStructure<List<Education>> searchQualification(@PathVariable String qualifi) {
		return service.searchQualification(qualifi);
	}

	@GetMapping("/search/stream/{stream}")
	public ResponseStructure<List<Education>> searchStream(@PathVariable String stream) {
		return service.searchStream(stream);
	}

	@GetMapping("/status/active")
	public ResponseStructure<List<Education>> findAllActiveEducation() {
		return service.findAllActiveEducation();
	}

	@GetMapping("/status/inactive")
	public ResponseStructure<List<Education>> findAllInactiveEducation() {
		return service.findAllInactiveEducation();
	}

	@GetMapping("/hq/yes")
	public ResponseStructure<List<Education>> findAllHqYesEducation() {
		return service.findAllHqYesEducation();
	}

	@GetMapping("/hq/no")
	public ResponseStructure<List<Education>> findAllHqNoEducation() {
		return service.findAllHqNoEducation();
	}

}
