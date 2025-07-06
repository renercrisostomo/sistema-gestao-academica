package br.edu.ifce.meuprimeirospringboot.controller;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;
import br.edu.ifce.meuprimeirospringboot.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.buscarTodos());
        return "aluno/lista";
    }

    @GetMapping("/novo")
    public String novoAlunoForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno/cadastro";
    }

    @PostMapping
    public String salvarAluno(Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAlunoForm(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoService.buscarPorId(id));
        return "aluno/cadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        alunoService.deletar(id);
        return "redirect:/alunos";
    }
}
