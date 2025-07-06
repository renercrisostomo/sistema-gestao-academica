package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;

public interface DisciplinaService {
    List<Disciplina> buscarTodos();
    Disciplina buscarPorId(Long id);
    Disciplina salvar(Disciplina disciplina);
    void deletar(Long id);
}
