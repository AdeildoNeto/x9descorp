package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import br.edu.ifpe.recife.x9academicoDescorp.model.Responsavel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class queryResponsavel {

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

    public void t_01responsavelPorNome() {
        logger.info("Executando t01: responsavel por Nome");
        TypedQuery query = em.createQuery(
                "SELECT p FROM Responsavel p WHERE p.nome = ?1",
                Responsavel.class);
        query.setParameter(1, "Valdeir");
        Responsavel responsavel = (Responsavel) query.getSingleResult();

        logger.info(responsavel.getNome());
        assertEquals("Valdeir", responsavel.getNome());

    }

    @Test
    public void t_02listaDeResponsaveis() {

        logger.info("Executando t02: ListadeResponsaveis");
        List<Responsavel> resp;

        Query query = em.createNamedQuery("Responsavel.listar");

        resp = query.getResultList();

        resp.forEach((respo) -> {
            logger.info(respo.getNome());
        });
        assertEquals(1, resp.size());
    }

    @Test
    public void t_03buscarAluno() {

        logger.info("Executando t03: BuscarAlunoDeResponsavel");
        Query query = em.createQuery(
                "SELECT u FROM Responsavel u WHERE u.nome =?1",
                Responsavel.class);
        query.setParameter(1, "Valdeir");
        Responsavel responsavel = (Responsavel) query.getSingleResult();

        List<Aluno> alns = responsavel.getAlunos();

        Aluno aln = alns.get(1);

        logger.info(aln.getNome());
        assertEquals("Joao", aln.getNome());

    }

    @Test
    public void t_04listarAlunosResp() {
        logger.info("Executando t04: listarAlunosDeResponsaveis");
        Query query = em.createQuery("Select u FROM Responsavel u where u.nome = ?1",
                Responsavel.class);
        query.setParameter(1, "Valdeir");
        Responsavel resp = (Responsavel) query.getSingleResult();

        List<Aluno> alns = resp.getAlunos();

        alns.forEach((alunos)
                -> {
            logger.info(alunos.getNome());
        });
        assertEquals(2, alns.size());
    }

    @Test
    public void t_05visualizarRelatorioParental() {

        logger.info("Executando t05: visualizarRelatorio");

        Query query = em.createQuery(
                "SELECT u FROM Responsavel u WHERE u.nome =?1",
                Responsavel.class);
        query.setParameter(1, "Valdeir");
        Responsavel responsavel = (Responsavel) query.getSingleResult();

        List<Aluno> alns = responsavel.getAlunos();

        for (Aluno aluno : alns) {
            switch (aluno.getRelatorioParental().getObservacoes()) {
                case "OBS feita":
                    assertTrue(true);
                    break;
                case "nao ha OBS":
                    assertTrue(true);
                    break;
                default:
                    assertTrue(false);
                    break;
            }
        }

        assertEquals(2, alns.size());

    }

}
