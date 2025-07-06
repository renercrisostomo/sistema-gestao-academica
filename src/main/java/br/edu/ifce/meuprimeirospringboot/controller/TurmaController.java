package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;
import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.exceptions.TurmaNaoEncontradaException;
import br.edu.ifce.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.ifce.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;
import java.util.List;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/novo")
    public String showCadastroForm(Model model) {
        model.addAttribute("turma", new Turma());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("professores", professorRepository.findAll());
        return "turma/cadastro";
    }

    @PostMapping
    public String salvar(Turma turma, RedirectAttributes redirectAttributes) {
        try {
            if (turma.getVagasDisponiveis() == null) {
                turma.setVagasDisponiveis(turma.getVagasTotal());
            }
            turmaRepository.save(turma);
            redirectAttributes.addFlashAttribute("mensagem", "Turma salva com sucesso!");
            return "redirect:/turmas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar turma: " + e.getMessage());
            return "redirect:/turmas/novo";
        }
    }

    @GetMapping
    public String lista(Model model) {
        List<Turma> turmas = turmaRepository.findAll();
        model.addAttribute("turmas", turmas);
        return "turma/lista";
    }

    @GetMapping("/{id}/matricula")
    public String showMatriculaForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));
            
            List<Aluno> alunosNaoMatriculados = alunoRepository.findAll();
            alunosNaoMatriculados.removeAll(turma.getAlunos());
            
            model.addAttribute("turma", turma);
            model.addAttribute("alunos", alunosNaoMatriculados);
            return "turma/matricula";
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/turmas";
        }
    }

    @PostMapping("/{id}/matricular")
    public String matricularAlunos(@PathVariable Long id, @RequestParam(required = false) List<Long> alunoIds, RedirectAttributes redirectAttributes) {
        if (alunoIds == null || alunoIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Selecione pelo menos um aluno para matricular.");
            return "redirect:/turmas/" + id + "/matricula";
        }
        try {
            Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));
            
            if (turma.getVagasDisponiveis() < alunoIds.size()) {
                redirectAttributes.addFlashAttribute("erro", "Número de vagas insuficiente");
                return "redirect:/turmas/" + id + "/matricula";
            }
            
            List<Aluno> alunosSelecionados = alunoRepository.findAllById(alunoIds);
            turma.getAlunos().addAll(alunosSelecionados);
            turma.setVagasDisponiveis(turma.getVagasDisponiveis() - alunoIds.size());
            
            turmaRepository.save(turma);
            redirectAttributes.addFlashAttribute("mensagem", "Alunos matriculados com sucesso!");
            return "redirect:/turmas/" + id + "/alunos";
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/turmas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao matricular alunos: " + e.getMessage());
            return "redirect:/turmas/" + id + "/matricula";
        }
    }

    @PostMapping("/{turmaId}/cancelar-matricula/{alunoId}")
    public String cancelarMatricula(@PathVariable Long turmaId, @PathVariable Long alunoId, RedirectAttributes redirectAttributes) {
        try {
            Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));
                
            Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
                
            if (turma.getAlunos().remove(aluno)) {
                turma.setVagasDisponiveis(turma.getVagasDisponiveis() + 1);
                turmaRepository.save(turma);
                redirectAttributes.addFlashAttribute("mensagem", "Matrícula cancelada com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Aluno não encontrado na turma");
            }
            
            return "redirect:/turmas/" + turmaId + "/alunos";
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/turmas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cancelar matrícula: " + e.getMessage());
            return "redirect:/turmas/" + turmaId + "/alunos";
        }
    }

    @GetMapping("/{id}/alunos")
    public String listarAlunos(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));
            
            model.addAttribute("turma", turma);
            model.addAttribute("alunos", turma.getAlunos());
            return "turma/alunos";
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/turmas";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));
            
            model.addAttribute("turma", turma);
            model.addAttribute("disciplinas", disciplinaRepository.findAll());
            model.addAttribute("professores", professorRepository.findAll());
            return "turma/cadastro";
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/turmas";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (!turmaRepository.existsById(id)) {
                throw new TurmaNaoEncontradaException("Turma não encontrada");
            }
            turmaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Turma excluída com sucesso!");
        } catch (TurmaNaoEncontradaException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir turma: " + e.getMessage());
        }
        return "redirect:/turmas";
    }
}
