package com.nextappoficial.springboot.app.invoice.sistem.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Client;
import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Region;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public List<Region> findAllRegions();

}