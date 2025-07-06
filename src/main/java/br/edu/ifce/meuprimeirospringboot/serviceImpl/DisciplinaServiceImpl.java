package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public List<Disciplina> buscarTodos() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    @Override
    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @Override
    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
