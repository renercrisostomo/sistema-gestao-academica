package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.ifce.meuprimeirospringboot.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public List<Turma> buscarTodos() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    @Override
    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }
}
