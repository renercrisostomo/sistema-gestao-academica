package br.edu.ifce.meuprimeirospringboot.service;

import br.edu.ifce.meuprimeirospringboot.beans.Professor;

import java.util.List;

public interface ProfessorService {
    Professor salvar(Professor professor);
    List<Professor> buscarTodos();
    Professor buscarPorId(Long id);
    void deletar(Long id);
}
