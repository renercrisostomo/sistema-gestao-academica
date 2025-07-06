package br.edu.ifce.meuprimeirospringboot.controller;

import br.edu.ifce.meuprimeirospringboot.beans.Professor;
import br.edu.ifce.meuprimeirospringboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public String listarProfessores(Model model) {
        model.addAttribute("professores", professorService.buscarTodos());
        return "professor/lista";
    }

    @GetMapping("/novo")
    public String novoProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/cadastro";
    }

    @PostMapping
    public String salvarProfessor(Professor professor) {
        professorService.salvar(professor);
        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String editarProfessorForm(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorService.buscarPorId(id));
        return "professor/cadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProfessor(@PathVariable Long id) {
        professorService.deletar(id);
        return "redirect:/professores";
    }
}
