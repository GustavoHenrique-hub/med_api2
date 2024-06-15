package br.edu.senaisp.MedApi_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senaisp.MedApi_3.model.Medico;

public interface MedApiRepository extends JpaRepository<Medico, Integer>{

}
