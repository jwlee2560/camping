package com.campTeam.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campTeam.webapp.domain.UploadFile;

public interface FileDAO extends JpaRepository<UploadFile, Integer> {
	 
	public UploadFile findOneByFileName(String fileName);
	
	public UploadFile findOneById(int id);
	
	public void deleteById(int id);
	
}