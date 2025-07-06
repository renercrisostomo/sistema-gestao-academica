package br.edu.ifce.meuprimeirospringboot.service;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;

import java.util.List;

public interface AlunoService {
    Aluno salvar(Aluno aluno);
    List<Aluno> buscarTodos();
    Aluno buscarPorId(Long id);
    void deletar(Long id);
}
