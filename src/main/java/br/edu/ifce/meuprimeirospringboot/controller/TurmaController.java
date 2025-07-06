package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;
import br.edu.ifce.meuprimeirospringboot.service.ProfessorService;
import br.edu.ifce.meuprimeirospringboot.service.TurmaService;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public String listarTurmas(Model model) {
        model.addAttribute("turmas", turmaService.buscarTodos());
        return "turma/lista";
    }

    @GetMapping("/novo")
    public String novaTurmaForm(Model model) {
        model.addAttribute("turma", new Turma());
        model.addAttribute("disciplinas", disciplinaService.buscarTodos());
        model.addAttribute("professores", professorService.buscarTodos());
        return "turma/cadastro";
    }

    @PostMapping
    public String salvarTurma(Turma turma) {
        turmaService.salvar(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editarTurmaForm(@PathVariable Long id, Model model) {
        model.addAttribute("turma", turmaService.buscarPorId(id));
        model.addAttribute("disciplinas", disciplinaService.buscarTodos());
        model.addAttribute("professores", professorService.buscarTodos());
        return "turma/cadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarTurma(@PathVariable Long id) {
        turmaService.deletar(id);
        return "redirect:/turmas";
    }
}
