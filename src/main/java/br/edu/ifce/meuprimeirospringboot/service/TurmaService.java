package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import br.edu.ifce.meuprimeirospringboot.beans.Turma;

public interface TurmaService {
    List<Turma> buscarTodos();
    Turma buscarPorId(Long id);
    Turma salvar(Turma turma);
    void deletar(Long id);
}
