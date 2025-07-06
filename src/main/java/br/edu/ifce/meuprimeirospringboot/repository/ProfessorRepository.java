package br.edu.ifce.meuprimeirospringboot.repository;

import br.edu.ifce.meuprimeirospringboot.beans.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
