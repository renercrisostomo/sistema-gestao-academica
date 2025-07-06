package br.edu.ifce.meuprimeirospringboot.repository;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
