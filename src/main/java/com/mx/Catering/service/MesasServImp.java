package com.mx.Catering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.Catering.dao.MesasDao;
import com.mx.Catering.dao.MeseroDao;
import com.mx.Catering.model.Mesas;
import com.mx.Catering.model.Mesero;


@Service
public class MesasServImp {
	@Autowired
	MesasDao mesasDao;
	
	@Autowired
	MeseroDao meseroDao;
	@Transactional(readOnly = true)
	public List<Mesas> listar(){
		return mesasDao.findAll();
	}
	
	@Transactional
	public String guardar(Mesas modelo) {
	    boolean banderaMesero = false;
	    boolean banderaMesa = false;
	    String respuesta = "";

	    // Validar que el ID del mesero exista
	    for (Mesero mesero : meseroDao.findAll()) {
	        if (mesero.getId().equals(modelo.getMesero().getId())) {
	            banderaMesero = true;
	            
	            // Validar que el ID o el numMesa no se repitan
	            for (Mesas mesa : mesasDao.findAll()) {
	                if (mesa.getId().equals(modelo.getId())) {
	                    banderaMesa = true;
	                    respuesta = "idMesasYaExiste";
	                    break;
	                } else if (mesa.getNumMesa().equals(modelo.getNumMesa())) {
	                    banderaMesa = true;
	                    respuesta = "numMesaYaExiste";
	                    break;
	                }
	            }
	            break;
	        }
	    }

	    // Si el ID del mesero no existe
	    if (!banderaMesero) {
	        respuesta = "idMeseroNoExiste";
	        banderaMesa = true;
	    }
	    // Si no hay problemas, guardar
	    else if (!banderaMesa) {
	        mesasDao.save(modelo);
	        respuesta = "guardado";
	    }

	    return respuesta;
	}

	
	@Transactional(readOnly = true)
	public Mesas buscar(Long id) {
		return mesasDao.findById(id).orElse(null);
	}
	
	@Transactional
	public String editar(Mesas modelo) {
	    // Validar que idMesero exista
	    if (!meseroDao.existsById(modelo.getMesero().getId())) {
	        return "idMeseroNoExiste";
	    }
	    // Validar que idMesas exista
	    if (!mesasDao.existsById(modelo.getId())) {
	        return "idMesasNoExiste";
	    }
	    // Si ambos existen, guardar el modelo
	    mesasDao.save(modelo);
	    return "exito";
	}
	
	@Transactional
	public boolean eliminar(Mesas mesas) {
		boolean bandera = false;
		for (Mesas m : mesasDao.findAll()) {
			if (m.getId().equals(mesas.getId())) {
				mesasDao.delete(mesas);
				bandera = true;
				break;
			}
		}
		return bandera;

	}

}
