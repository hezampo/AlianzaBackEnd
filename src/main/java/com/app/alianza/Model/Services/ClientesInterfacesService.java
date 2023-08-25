package com.app.alianza.Model.Services;

import java.util.List;
import java.util.Optional;

import com.app.alianza.Model.Entity.Clientes;

public interface ClientesInterfacesService {
	 	public List<Clientes> listar();
	    public Clientes guardar(Clientes cliente);
	    public Optional<Clientes> buscarById(int id);
	    public void eliminar(int id);
	    Optional<Clientes> buscarPorSharedkey(String sharedkey);
}
