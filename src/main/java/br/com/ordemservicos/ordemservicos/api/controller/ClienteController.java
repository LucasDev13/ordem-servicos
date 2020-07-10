package br.com.ordemservicos.ordemservicos.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordemservicos.ordemservicos.domain.model.Cliente;
import br.com.ordemservicos.ordemservicos.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	// @PersistenceContext //vai injetar o entityManager direto na classe
	// private EntityManager manager;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();// busca todos os clientes
		//return clienteRepository.findByNome("lucas fernando pontes castro");//busca
		// por nome exato
		// return clienteRepository.findByNomeContaining("h");//busca por paciais do
		// nome
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//coloca o status 201 quando a requisição for um sucesso.
	public Cliente gravar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> update(@PathVariable Long clienteId,
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setCodCliente(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
	
//	@GetMapping("/clientes")
//	public List<Cliente> listar() {
//		Cliente cliente = new Cliente();
//		cliente.setCodCliente(1L);
//		cliente.setNomeCompleto("Lucas Fernando Pontes Castro");
//		cliente.setEmail("lucaspontes140@gmail.com");
//		cliente.setTelefone(55555555);
//		
//		Cliente cliente2 = new Cliente();
//		cliente2.setCodCliente(2L);
//		cliente2.setNomeCompleto("Thais Rodrigues dos Santos");
//		cliente2.setEmail("rodriguesdossantosthais@gmail.com");
//		cliente2.setTelefone(99997777);
//		
//		return Arrays.asList(cliente, cliente2);
//	}
}
