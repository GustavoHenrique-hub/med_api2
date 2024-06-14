package br.edu.senaisp.MedicoWeb_Av.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RestController;

import br.edu.senaisp.MedicoWeb_Av.model.Medico;
import br.edu.senaisp.MedicoWeb_Av.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	MedicoRepository repository;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Medico>> listar(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/busca/{id}")
	public ResponseEntity<Optional<Medico>> busca(@PathVariable("id") Integer id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@PostMapping("/inserir")
	public @Valid Medico insere(@RequestBody @Valid Medico med) {
		return repository.save(med);
	}
	
	@PutMapping("/altera/{id}")
	public @Valid ResponseEntity<Medico> alterar(@RequestBody @Valid Medico med, @PathVariable("id") Integer id) {
		Optional<Medico> medicoData = repository.findById(id);
		
		if(medicoData.isPresent()) {
			Medico medico = medicoData.get();
			medico.setNome(med.getNome());
			medico.setCrm(med.getCrm());
			
			return new ResponseEntity<>(repository.save(medico), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Integer id) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        repository.deleteById(id);
	        response.put("message", "Deletado com sucesso");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("error", "ERRO: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
}

