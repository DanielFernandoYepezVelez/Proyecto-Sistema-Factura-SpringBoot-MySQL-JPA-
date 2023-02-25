package com.nextappoficial.springboot.app.invoice.sistem.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Client;
import com.nextappoficial.springboot.app.invoice.sistem.models.entity.Region;
import com.nextappoficial.springboot.app.invoice.sistem.models.services.IClientService;
import com.nextappoficial.springboot.app.invoice.sistem.models.services.IUploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@GetMapping("/clients")
	public List<Client> index() {
		return clientService.findAll();
	}
	
	/* Clientes Con Paginación */
	@GetMapping("/clients/page/{pageNumber}")
	public Page<Client> index(@PathVariable Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 4);
		return clientService.findAll(pageable);
	}
	
	/* API Sin Validar Posibles Errores */
	/* @GetMapping("/client/{id}")
	public Client show(@PathVariable Long id) {
		return clientService.findById(id);
	} */
	
	/* API Validando Posibles Errores */
	@GetMapping("/client/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Client client = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			client = clientService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar La Consulta En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client == null) {
			response.put("message", "Error: El Cliente Con El ID: ".concat(id.toString().concat(" No Existe En El Sistema")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	/* API Sin Validar Posibles Errores */
	/* @PostMapping("/client")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {
		return clientService.save(client);
	} */
	
	@PostMapping("/client")
	public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
		Client clientDB = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			/* List<String> errors = new ArrayList<String>();
			
			for(FieldError error: result.getFieldErrors()) {
				errors.add("El Campo '" + error.getField() + "' " + error.getDefaultMessage());
			} */
			
			/* ============ PROGRAMACIÓN FUNCIONAL (STREAMS) ============== */
			/*List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> {
						return "El Campo '" + error.getField() + "' " + error.getDefaultMessage();
						})
					.collect(Collectors.toList()); */
			
			/* ============ PROGRAMACIÓN FUNCIONAL (STREAMS) ============== */
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> "El Campo '" + error.getField() + "' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			clientDB = clientService.save(client);
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar El Insert En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El Cliente Se Creo Exitosamente");
		response.put("client", clientDB);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/* API Sin Validar Posibles Errores */
	/* @PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client, @PathVariable Long id) {
		Client clientDB = clientService.findById(id);
		
		clientDB.setName(client.getName());
		clientDB.setLastName(client.getLastName());
		clientDB.setEmail(client.getEmail());
		
		return clientService.save(clientDB);
	} */
	
	/* API Validando Posibles Errores */
	@PutMapping("/clients/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id) {
		Client clientDB = null;
		Client clientUpdate = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			/* ============ PROGRAMACIÓN FUNCIONAL (STREAMS) ============== */
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> "El Campo '" + error.getField() + "' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			clientDB = clientService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar La Consulta En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(clientDB == null) {
			response.put("message", "Error: No Se Puede Editar, El Cliente Con El ID: ".concat(id.toString().concat(" No Existe En El Sistema")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			clientDB.setName(client.getName());
			clientDB.setLastName(client.getLastName());
			clientDB.setEmail(client.getEmail());
			clientDB.setCreatedAt(client.getCreatedAt());
			clientDB.setRegion(client.getRegion());
			
			clientUpdate = clientService.save(clientDB);
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar El Update En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El Cliente Se Actualizo Exitosamente");
		response.put("client", clientUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/* API Sin Validar Posibles Errores */
	/* @DeleteMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	} */
	
	/* API Validando Posibles Errores */
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Client clientDB = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			clientDB = clientService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar La Consulta En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(clientDB == null) {
			response.put("message", "Error: No Se Puede Eliminar, El Cliente Con El ID: ".concat(id.toString().concat(" No Existe En El Sistema")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		// En Caso Tal De Que Tengamos Un Error En La Base De Datos, Lo Manejamos Con El Try - Catch
		try {
			
			// Antes De Eliminar Un Usuario, Elimino Su Foto (SI EXISTE) Para Que No Quede HUERFANA
			// Debo Válidar Si El ID Existe Para Traer La Información De Dicho Usuario
			String beforePhotoName = clientDB.getPhoto();
			uploadService.delete(beforePhotoName);
			
			// No Es Necesario Validar El ID, Por Que Spring Data Con El Metodo Delete LO Hace Por Debajo
			clientService.delete(id);
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar El Delete En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Cliente Eliminado Exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/* Primer Método Handler Para Subir Las Imagenes Del Cliente, El De Abajo Esta Mejorado */
	
	/* @PostMapping("/clients/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
		Client clientDB = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			clientDB = clientService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar La Consulta En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {				
				response.put("message", "Error Al Subir La Foto ".concat(fileName));
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}	
			
			clientDB.setPhoto(fileName);
			clientService.save(clientDB);
			
			response.put("client", clientDB);
			response.put("message", "Ha Subido Correctamente La Imagen: ".concat(fileName));
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	} */
	
	@PostMapping("/clients/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
		Client clientDB = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			clientDB = clientService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("message", "Error Al Realizar La Consulta En La Base De Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(!file.isEmpty()) {
			String fileName = null;
			
			try {
				// Guardando Imagen Del Cliente
				fileName = uploadService.saveImage(file);
			} catch (IOException e) {				
				response.put("message", "Error Al Subir La Foto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			// Eliminar La Foto Anterior Del Cliente (SI EXISTE)
			String beforePhotoName = clientDB.getPhoto();
			uploadService.delete(beforePhotoName);
			
			// Establecer Foto Y Guardar Cliente Con La Foto
			clientDB.setPhoto(fileName);
			clientService.save(clientDB);
			
			response.put("message", "Ha Subido Correctamente La Imagen: ".concat(fileName));
			response.put("client", clientDB);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@GetMapping("/uploads/image/{photoName:.+}")
	public ResponseEntity<Resource> photoView(@PathVariable String photoName) {
		Resource resource = null;
		
		try {
			resource = uploadService.loadImage(photoName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Esta Cabecera Obliga La Descarga De La Imagen
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@GetMapping("/clients/regions")
	public List<Region> regionsList() {
		return clientService.findAllRegions();
	}

}