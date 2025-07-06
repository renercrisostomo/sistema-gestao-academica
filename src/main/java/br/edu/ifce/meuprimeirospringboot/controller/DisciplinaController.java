package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public String listarDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaService.buscarTodos());
        return "disciplina/lista";
    }

    @GetMapping("/novo")
    public String novaDisciplinaForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplina/cadastro";
    }

    @PostMapping
    public String salvarDisciplina(Disciplina disciplina) {
        disciplinaService.salvar(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/editar/{id}")
    public String editarDisciplinaForm(@PathVariable Long id, Model model) {
        model.addAttribute("disciplina", disciplinaService.buscarPorId(id));
        return "disciplina/cadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletar(id);
        return "redirect:/disciplinas";
    }
}
