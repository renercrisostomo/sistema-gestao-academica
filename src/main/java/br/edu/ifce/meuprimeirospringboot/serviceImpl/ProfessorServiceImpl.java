package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import br.edu.ifce.meuprimeirospringboot.beans.Professor;
import br.edu.ifce.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.ifce.meuprimeirospringboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Override
    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    @Override
    public List<Professor> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Professor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
