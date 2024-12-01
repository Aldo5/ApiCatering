package com.mx.Catering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.Catering.dao.MeseroDao;
import com.mx.Catering.model.Mesero;


@Service
public class MeseroServImp {
	@Autowired
	MeseroDao meseroDao;
	
	@Transactional(readOnly = true)
	public List <Mesero> listar(){
		return meseroDao.findAll();
	}
	
	//VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA
	@Transactional
	public String guardar(Mesero mesero) {
	    boolean bandera = false;
	    String mensaje = "";

	    
	    for (Mesero m : meseroDao.findAll()) {
	        if (m.getId().equals(mesero.getId())) {
	            mensaje = "El ID ya existe";
	            bandera = true;
	            break; 
	        } else if (m.getNombre().equals(mesero.getNombre()) && m.getApp().equals(mesero.getApp()) && m.getApm().equals(mesero.getApm())) {
	            mensaje = "El nombre ya existe";
	            bandera = true;
	            break; 
	        }
	    }

	    if (bandera) {
	        return mensaje;
	    }

	    meseroDao.save(mesero);
	    return "Mesero guardado con éxito";
	}
	
	@Transactional(readOnly = true)
	public Mesero buscar(Long id) {
		
		return meseroDao.findById(id).orElse(null);
	}
	
	//VALIDAR QUE EL ID EXISTA
	@Transactional
	public boolean editar(Mesero mesero) {
		boolean bandera = false;
		for (Mesero m : meseroDao.findAll()) {
			if (m.getId().equals(mesero.getId())) {
				meseroDao.save(mesero);
				bandera = true;
				break;
			}
		}
		return bandera;
		}
	
	//VALIDAR QUE EL ID EXISTA
public boolean eliminar (Long id) {
		
		boolean bandera = false;
		for (Mesero m : meseroDao.findAll()) {
			if (m.getId().equals(id)) {
				// repository.delete(producto);
				meseroDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
}
