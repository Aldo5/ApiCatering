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

import com.mx.Catering.model.Mesero;
import com.mx.Catering.service.MeseroServImp;




@RestController
@RequestMapping(path = "MeseroWs")
@CrossOrigin
public class MeseroWs {
	
	@Autowired
	MeseroServImp meseroImp;
	
	@GetMapping("listar")
	public List<Mesero> listar(){
		return meseroImp.listar();
	}
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesero mesero) {
	    // Llamar al método guardar y obtener el mensaje de respuesta
	    String response = meseroImp.guardar(mesero);

	    
	    if (response.equals("El ID ya existe")) {
	        return new ResponseEntity<>("El Id de ese mesero ya existe", HttpStatus.OK);
	    } else if (response.equals("El nombre ya existe")) {
	        return new ResponseEntity<>("El nombre de ese mesero ya existe", HttpStatus.OK);
	    } else if (response.equals("Mesero guardado con éxito")) {
	        return new ResponseEntity<>(mesero, HttpStatus.CREATED);
	    }

	    // Respuesta por defecto (caso no esperado)
	    return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path = "buscar")
	public Mesero buscar(@RequestBody Mesero mesero) {
		return meseroImp.buscar(mesero.getId());
	}
	
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Mesero mesero){
		
		boolean response = meseroImp.editar(mesero);
		
		if(response == true)
			return new ResponseEntity <>(mesero, HttpStatus.CREATED);
		else
			return new ResponseEntity<>("No existe ese id de esa marca", HttpStatus.OK);	
	}
	
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Mesero mesero){
		
		boolean response = meseroImp.eliminar(mesero.getId());
		
		if(response == true)
			return new ResponseEntity <>("Eliminado exitosamente", HttpStatus.OK);
		else
			return new ResponseEntity<>("No existe ese id de esa marca", HttpStatus.OK);
		
	
	}

}
