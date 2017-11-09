package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class queryAluno {

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
    public void t01_buscarPorNome() {
        logger.info("Executando t01: BuscarPorNome");
        Query query = em.createQuery("SELECT a FROM Aluno a WHERE a.nome LIKE :nome ORDER BY a.idAluno");
        query.setParameter("nome", "Aldo%");

        List<Aluno> alunos = query.getResultList();
        for (Aluno aluno : alunos) {
            assertTrue(aluno.getNome().startsWith("Aldo"));
        }
        assertEquals(1, alunos.size());
    }

    @Test
    public void t02_buscarTodos() {
        logger.info("Executando t02: BuscarTodos");
        Query query = em.createQuery("SELECT a FROM Aluno a ORDER BY a.idAluno");

        List<Aluno> alunos = query.getResultList();
        alunos.forEach((aluno)
                -> {
            logger.info(aluno.getNome());
        });
        assertEquals(2, alunos.size());
    }

    @Test
    public void t03_buscarPorTurma() {
        logger.info("Executando t03: BuscarAlunosPorTurma");
        Query query = em.createQuery("SELECT a FROM Aluno a WHERE a.turma.serie like ?1 ORDER BY a.nome");
        query.setParameter(1, "Quinta"); //Setando parâmetro posicional.
        List<Aluno> alunos = query.getResultList();

        for (Aluno aluno : alunos) {
            assertTrue(aluno.getTurma().getSerie().startsWith("Quinta"));
        }

        assertEquals(2, alunos.size());
    }

    @Test
    public void t04_buscarRelatorio() {
        logger.info("Executando t04: BuscarRelatorio");
        Query query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.relatorioParental.idRelatorioParental like ?1");
        query.setParameter(1, 1);
        Aluno aluno = (Aluno) query.getSingleResult();
        long id = aluno.getRelatorioParental().getIdRelatorioParental();
        assertEquals(1, id);
    }

    @Test
    public void t05_buscarPorNotaMaior() {
        logger.info("Executando t04: BuscarAlunoPorNotaMaior8");
        Query query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.relatorioParental.participacaoEmSala >= ?1 ORDER BY a.relatorioParental.participacaoEmSala");
        query.setParameter(1, 8);
        List<Aluno> Alunos = query.getResultList();
        assertEquals(1, Alunos.size());
    }

    @Test
    public void t06_buscarTurma() {
        logger.info("Executando t06: BuscarTurma");
        Query query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.nome like ?1 AND a.turma.idTurma like ?2");
        query.setParameter(1, "Aldo");
        query.setParameter(2, 1);
        Aluno aluno = (Aluno) query.getSingleResult();
        assertEquals("Quinta", aluno.getTurma().getSerie());
    }

    @Test
    public void t07_buscarPorResponsavel() {
        logger.info("Executando t07: BuscarResponsavel");
        Query query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.responsavel.nome like ?1");
        query.setParameter(1, "Valdeir");
        List<Aluno> alunos = query.getResultList();

        for (Aluno aluno : alunos) {
            assertTrue(aluno.getResponsavel().getNome().startsWith("Valdeir"));
        }
        assertEquals(2, alunos.size());
    }

    @Test
    public void t08_maximaEMinimaDataNascimento() {
        logger.info("Executando t08: MaximaEMinimaDataNascimento");
        Query query = em.createQuery(
                "SELECT MAX(a.dataNascimento), MIN(a.dataNascimento) FROM Aluno a");
        Object[] resultado = (Object[]) query.getSingleResult();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String maiorData = dateFormat.format((Date) resultado[0]);
        String menorData = dateFormat.format((Date) resultado[1]);
        assertEquals("10-10-2000", maiorData);
        assertEquals("06-09-1998", menorData);
    }
}
