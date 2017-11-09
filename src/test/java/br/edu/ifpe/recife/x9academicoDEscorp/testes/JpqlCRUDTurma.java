/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Turma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author aldo_neto
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlCRUDTurma {

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
    public void t01_criaTurma() {

        Turma turma = new Turma();

        turma.setNumeroSala(10);
        turma.setQtdAluno(20);
        turma.setSerie("Sexta");
        turma.setTurno("Noite");

        em.persist(turma);
        em.flush();
        assertNotNull(turma.getIdTurma());
    }

    @Test
    public void t02_listarTurmas() {

        List<Turma> turmas;

        String jpql = "SELECT u FROM Turma u";
        Query query = em.createQuery(jpql);
        
        turmas = (List<Turma>) query.getResultList();

        assertEquals(3, query.getResultList().size());
    }

    @Test
    public void t03_buscarTurma() {

        Turma turma;

        String jpql = "SELECT u FROM Turma u Where u.idTurma = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 2);

        turma = (Turma) query.getSingleResult();

        Integer id = 2;

        assertEquals(id, turma.getIdTurma());
    }

    @Test
    public void t04_atualizaTurma() {

        Turma turma;

        String jpql = "SELECT u FROM Turma u Where u.serie like ?1 AND u.turno like ?2";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "Sexta");
        query.setParameter(2, "Noite");

        turma = (Turma) query.getSingleResult();

        turma.setQtdAluno(34);
        turma.setTurno("Manha");
        turma.setSerie("Quarta");

        em.merge(turma);
        em.flush();
        assertEquals("Quarta", turma.getSerie());

    }

    @Test
    public void t05_removeTurma() {

        Turma turma;

        String jpql = "SELECT u FROM Turma u Where u.idTurma = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 2);

        turma = (Turma) query.getSingleResult();

        em.remove(turma);

        em.flush();

        Turma apagada = em.find(Turma.class, 2);

        assertNull(apagada);
    }

}
