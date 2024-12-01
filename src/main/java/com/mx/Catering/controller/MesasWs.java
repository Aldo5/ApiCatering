package com.mx.Catering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Catering.model.Mesas;
import com.mx.Catering.service.MesasServImp;



@RestController
@RequestMapping(path = "MesasWs")
@CrossOrigin
public class MesasWs {

	@Autowired
	MesasServImp mesasImp;
	@GetMapping(path =  "listar")
	public List<Mesas> listar(){
		return mesasImp.listar();
	}
	
	@PostMapping(path = "guardar")
	public ResponseEntity<String> guardar(@RequestBody Mesas mesas) {
	    String response = mesasImp.guardar(mesas);

	    switch (response) {
	        case "idMeseroNoExiste":
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("El ID del mesero no existe.");
	        case "idMesasYaExiste":
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("El ID de la mesa ya existe.");
	        case "numMesaYaExiste":
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("El número de mesa ya existe.");
	        case "guardado":
	            return ResponseEntity.status(HttpStatus.OK)
	                .body("Mesa guardada con éxito.");
	        default:
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error desconocido.");
	    }
	}

	
	@PostMapping(path = "buscar")
	public Mesas buscar(@RequestBody Mesas modelo) {
		return mesasImp.buscar(modelo.getId());
	}
	
	@PostMapping(path = "editar")
	public ResponseEntity<String> editar(@RequestBody Mesas mesas) {
	    String response = mesasImp.editar(mesas);

	    switch (response) {
	        case "idMeseroNoExiste":
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("El ID del mesero no existe.");
	        case "idMesasNoExiste":
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("El ID de la mesa no existe.");
	        case "exito":
	            return ResponseEntity.status(HttpStatus.OK)
	                .body("Se editó con éxito.");
	        default:
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error desconocido.");
	    }
	}
	
	@PostMapping("eliminar")
	public ResponseEntity <String> eliminar(@RequestBody Mesas mesas){
		boolean response = mesasImp.eliminar(mesas);
		
		if(response == true)
			return new ResponseEntity <>("Eliminado exitosamente", HttpStatus.OK);
		else
			return new ResponseEntity<>("No existe ese id de producto", HttpStatus.OK);
					
	}

}
