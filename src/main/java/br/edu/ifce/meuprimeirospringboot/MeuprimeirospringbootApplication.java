package br.edu.ifce.meuprimeirospringboot;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifce.meuprimeirospringboot.beans.Aluno;
import br.edu.ifce.meuprimeirospringboot.beans.Professor;
import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;
import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.ifce.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;

@SpringBootApplication
public class MeuprimeirospringbootApplication implements CommandLineRunner {
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private TurmaRepository turmaRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeuprimeirospringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Criando alunos
        Aluno aluno1 = new Aluno();
        aluno1.setNome("João Silva");
        aluno1.setMatricula("2025001");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria Santos");
        aluno2.setMatricula("2025002");

        Aluno aluno3 = new Aluno();
        aluno3.setNome("Pedro Oliveira");
        aluno3.setMatricula("2025003");

        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3));

        // Criando professores
        Professor prof1 = new Professor();
        prof1.setNome("Dr. Carlos Eduardo");
        prof1.setAreaDeAtuacao("Programação");

        Professor prof2 = new Professor();
        prof2.setNome("Dra. Ana Beatriz");
        prof2.setAreaDeAtuacao("Banco de Dados");

        professorRepository.saveAll(Arrays.asList(prof1, prof2));

        // Criando disciplinas
        Disciplina disc1 = new Disciplina();
        disc1.setNome("Programação Web");
        disc1.setAno("2025");
        disc1.setPeriodo("1");
        disc1.setDescricao("Desenvolvimento de aplicações web com Spring Boot");
        disc1.setCargaHoraria(60);

        Disciplina disc2 = new Disciplina();
        disc2.setNome("Banco de Dados");
        disc2.setAno("2025");
        disc2.setPeriodo("1");
        disc2.setDescricao("Fundamentos de Banco de Dados Relacionais");
        disc2.setCargaHoraria(60);

        disciplinaRepository.saveAll(Arrays.asList(disc1, disc2));

        // Criando turmas
        Turma turma1 = new Turma();
        turma1.setNome("Turma A - Programação Web");
        turma1.setDisciplina(disc1);
        turma1.setProfessor(prof1);
        turma1.setVagasTotal(30);
        turma1.setVagasDisponiveis(30);

        Turma turma2 = new Turma();
        turma2.setNome("Turma A - Banco de Dados");
        turma2.setDisciplina(disc2);
        turma2.setProfessor(prof2);
        turma2.setVagasTotal(30);
        turma2.setVagasDisponiveis(30);

        turmaRepository.saveAll(Arrays.asList(turma1, turma2));

        // Matriculando alunos nas turmas
        turma1.getAlunos().add(aluno1);
        turma1.getAlunos().add(aluno2);
        turma1.setVagasDisponiveis(turma1.getVagasDisponiveis() - 2);
        
        turma2.getAlunos().add(aluno2);
        turma2.getAlunos().add(aluno3);
        turma2.setVagasDisponiveis(turma2.getVagasDisponiveis() - 2);

        turmaRepository.saveAll(Arrays.asList(turma1, turma2));
    }
}
