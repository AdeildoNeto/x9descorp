/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import br.edu.ifpe.recife.x9academicoDescorp.model.Professor;
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

public class queryTurma {

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
    public void t01_buscarTurma() {
        logger.info("Executando t01: BuscarTurma");
        Query query = em.createQuery("SELECT t FROM Turma t WHERE t.serie = ?1");
        query.setParameter(1, "Quinta");

        Turma turma = (Turma) query.getSingleResult();

        logger.info(turma.getSerie());
        assertEquals("Quinta", turma.getSerie());

    }

    @Test
    public void t02_buscarListaDeTurmas() {
        logger.info("Executando t02: buscarListaDeTurmas");
        Query query = em.createNamedQuery("Turma.listarTurmas");

        List<Turma> turmas = (List<Turma>) query.getResultList();

        turmas.forEach((turma)
                -> {
            logger.info(turma.getSerie());
        });
        assertEquals(2, turmas.size());

    }

    @Test
    public void t03_buscarAlunosDeTurma() {
        logger.info("Executando t03: t03_buscarAlunosDeTurma");

        Query query = em.createQuery("SELECT t FROM Turma t WHERE t.serie = ?1");
        query.setParameter(1, "Quinta");

        Turma turma = (Turma) query.getSingleResult();

        List<Aluno> alns = turma.getAlunos();

        alns.forEach((aluno)
                -> {
            logger.info(aluno.getNome());
        });
        assertEquals(2, alns.size());

    }

    @Test
    public void t04_buscarProfessorDaTurma() {

        logger.info("Executando t04: buscarProfessorDaTurma");
        Query query = em.createNamedQuery("Turma.buscarTurma");
        query.setParameter(1, "Quinta");

        Turma turma = (Turma) query.getSingleResult();

        Professor prof = turma.getProfessor();

        logger.info(prof.getNome());
        assertEquals("Lucas", prof.getNome());

    }
}
