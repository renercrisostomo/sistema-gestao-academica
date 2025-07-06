package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;
import br.edu.ifce.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.ifce.meuprimeirospringboot.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    public List<Aluno> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
