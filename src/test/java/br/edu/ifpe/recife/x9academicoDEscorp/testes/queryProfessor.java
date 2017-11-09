/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import br.edu.ifpe.recife.x9academicoDescorp.model.Professor;
import br.edu.ifpe.recife.x9academicoDescorp.model.RelatorioParental;
import br.edu.ifpe.recife.x9academicoDescorp.model.Turma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class queryProfessor {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        emf = Persistence.createEntityManagerFactory("x9_PU");
        DBunitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());

            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
            em = null;
            et = null;
        }
    }

    @Test
    public void t01_listarProfessores() {

        logger.info("Executando t01: ListarProfessores com namedQuery");
        List<Professor> profs;

        Query query = em.createQuery("select p FROM Professor p");

        profs = query.getResultList();

        profs.forEach((professor) -> {
            logger.info(professor.getNome());
        });
        assertEquals(2, profs.size());
    }

    @Test
    public void t02_buscarProfessor() {

        logger.info("Executando t02: BuscarProfessor por nome");
        Query query = em.createNamedQuery("Professor.PorNome");
        query.setParameter(1, "Aida");

        List<Professor> profs;

        profs = query.getResultList();

        profs.forEach((professor)
                -> {
            logger.info(professor.getNome());
        });
        assertEquals(1, profs.size());
    }

    @Test
    public void t03_buscarTurmasProfessor() {

        logger.info("Executando t03: BuscarTurmaProfessor");
        Query query = em.createQuery(
                "SELECT u FROM Professor u WHERE u.login = ?1 ",
                Professor.class);
        query.setParameter(1, "professor");

        Professor prof = (Professor) query.getSingleResult();
        List<Turma> turmas = prof.getTurmas();

        turmas.forEach((turma) -> {

            logger.info(turma.getSerie());
        });
        assertEquals(1, turmas.size());
    }

    @Test
    public void t04_buscarUnicaTurma() {

        logger.info("Executando t04: BuscarProfessorDaTurma");
        Query query = em.createQuery(
                "SELECT u FROM Professor u WHERE u.login = ?1 ",
                Professor.class);
        query.setParameter(1, "professor");

        Professor prof = (Professor) query.getSingleResult();
        List<Turma> turmas = prof.getTurmas();
        Turma turma = turmas.get(0);

        assertEquals("Quinta", turma.getSerie());
    }

    @Test
    public void t05_buscarAlunoProfessor() {

        logger.info("Executando t05: BuscarAlunoProfessor");
        Query query = em.createQuery(
                "SELECT u FROM Professor u WHERE u.login = ?1 ",
                Professor.class);
        query.setParameter(1, "professor");

        Professor prof = (Professor) query.getSingleResult();
        List<Turma> turmas = prof.getTurmas();

        Turma turma = turmas.get(0);
        List<Aluno> alunos = turma.getAlunos();
        alunos.forEach((aluno) -> {

            logger.info(aluno.getNome());
        });

        assertEquals(2, alunos.size());
    }

    @Test
    public void t06_buscarRelatorioProfessor() {

        logger.info("Executando t06: BuscarRelatorioProfessor");
        Query query = em.createQuery(
                "SELECT u FROM Professor u WHERE u.login = ?1 ",
                Professor.class);
        query.setParameter(1, "professor");

        Professor prof = (Professor) query.getSingleResult();
        List<Turma> turmas = prof.getTurmas();

        Turma turma = turmas.get(0);
        List<Aluno> alunos = turma.getAlunos();
        Aluno aluno = alunos.get(0);
        RelatorioParental relt = aluno.getRelatorioParental();

        assertEquals(8.0, relt.getTrabalhoEmEquipe(), 0.01);

    }
}
