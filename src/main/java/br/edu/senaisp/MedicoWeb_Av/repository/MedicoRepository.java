package br.edu.senaisp.MedicoWeb_Av.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senaisp.MedicoWeb_Av.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
