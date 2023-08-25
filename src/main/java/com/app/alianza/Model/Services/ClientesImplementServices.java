package com.app.alianza.Model.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.app.alianza.Model.Entity.Clientes;
import com.app.alianza.Model.Repository.ClientesRepository;

@Service
public class ClientesImplementServices implements ClientesInterfacesService{
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	
	@Override
	public List<Clientes> listar() {
		return (List<Clientes>) clientesRepository.findAll();
	}

	@Override
	public Optional<Clientes> buscarById(int id) {
		return Optional.ofNullable((Clientes) clientesRepository.findById(id).orElse(null));
	}

	@Override
	public void eliminar(int id) {
		clientesRepository.deleteById(id);		
	}
	
	@Override
	public Clientes guardar(Clientes cliente) {
		return clientesRepository.save(cliente);
	}

	@Override
	public Optional<Clientes> buscarPorSharedkey(String sharedkey) {
		return clientesRepository.buscarPorSharedKey(sharedkey);
	}


}
