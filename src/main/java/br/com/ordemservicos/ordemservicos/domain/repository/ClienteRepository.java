package br.com.ordemservicos.ordemservicos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ordemservicos.ordemservicos.domain.model.Cliente;
//e um componente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//List<Cliente> findByNome(String nomeCompleto);
	
}
