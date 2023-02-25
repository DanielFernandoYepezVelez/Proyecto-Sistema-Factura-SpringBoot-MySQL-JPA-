package com.nextappoficial.springboot.app.invoice.sistem.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Client;
import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Region;

public interface IClientDao extends JpaRepository<Client, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegions();

}