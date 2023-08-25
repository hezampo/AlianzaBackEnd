package com.app.alianza.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.alianza.Model.Entity.Clientes;
import com.app.alianza.Model.Services.ClientesInterfacesService;
import com.app.alianza.Util.Constantes;
import com.app.alianza.Util.CsvUtils;

@RestController
@RequestMapping("/v1/api")
public class ApiController {
	
	@Autowired
	private ClientesInterfacesService clientesInterfacesService;
	
	@PostMapping("/guardarCliente")
	public Clientes guardarCliente(@RequestBody Clientes clientes) {
		Clientes cl = new Clientes();
		cl.setEmail(clientes.getEmail());
		cl.setBussinessid(clientes.getBussinessid());
		cl.setPhone(clientes.getPhone());
		cl.setSharedkey(addShareKey(clientes.getEmail()));
		cl.setData(convertirFecha());
		return clientesInterfacesService.guardar(cl);
	}
	
	@GetMapping("/listarCliente")
	public List<Clientes>listarCliente(){
		return clientesInterfacesService.listar();
	}
	
	@PostMapping("/buscarClientePorSharedKey")
	public Optional<Clientes> buscarClientePorSharedKey(@RequestBody String sharedkey){
		return clientesInterfacesService.buscarPorSharedkey(sharedkey);
	}
	
	@GetMapping("/editarCliente/{id}")
	public List<Clientes> editarCliente(@PathVariable int id, @RequestBody Clientes clientes) {
		Optional<Clientes> clienteExistente = clientesInterfacesService.buscarById(id);
		if(clienteExistente.isPresent()) {
			Clientes clienteActual = clienteExistente.get();
			clienteActual.setEmail(clientes.getEmail());
			clienteActual.setBussinessid(clientes.getBussinessid());
			clienteActual.setPhone(clientes.getPhone());
			clienteActual.setSharedkey(addShareKey(clientes.getEmail()));
			clienteActual.setData(clientes.getData());
			clientesInterfacesService.guardar(clienteActual);
		}
		return clientesInterfacesService.listar();
	}
	
	@GetMapping("/exportar-csv")
	public ResponseEntity<byte[]> exportarCSV(){
		List<Clientes> listaClientes = clientesInterfacesService.listar();    
        byte[] csvBytes = CsvUtils.generarCsv(listaClientes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "clientes.csv");

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
	}
	
	public String addShareKey(String sharedkey) {
		String[] sk = sharedkey.split(Constantes.SPLIT_ARROBA);
		if(sk.length == 2) {
			return sk[0];
		}else {
			throw new IllegalArgumentException("Correo electrónico inválido");
		}
	}
	
	public String convertirFecha() {
        LocalDate fecha = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaComoString = fecha.format(formatter);
        return fechaComoString;
    }
}
