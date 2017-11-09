package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import br.edu.ifpe.recife.x9academicoDescorp.model.RelatorioParental;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlCRUDAluno {

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
    public void t01_criaAluno() {

        Aluno aluno1 = new Aluno();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2001, Calendar.DECEMBER, 10);
        aluno1.setDataNascimento(calendar.getTime());
        aluno1.setDeficiencia("Não");
        aluno1.setMatricula("a2015003");
        aluno1.setNome("Hugo");

        em.persist(aluno1);
        
        em.flush();
        
        assertNotNull(aluno1.getIdAluno());
        
    }

    @Test
    public void t02_listarAlunos() {
        
        List<Aluno> alunos;

        String jpql = "SELECT u FROM Aluno u";
        Query query = em.createQuery(jpql);

      
            alunos = (List<Aluno>) query.getResultList();
            
            
            assertEquals(3, query.getResultList().size());

       
    }

    @Test
    public void t03_buscarAluno() {
   
        Aluno aln;
        String jpql = "SELECT u FROM Aluno u Where u.idAluno = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 3);

       
            aln = (Aluno) query.getSingleResult();
           
            
            assertEquals("Hugo", aln.getNome());

    
    }

    @Test
    public void t04_atualizarAluno() {
     
        Aluno aln;

        String jpql = "SELECT u FROM Aluno u Where u.nome = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "Hugo");

     
            aln = (Aluno) query.getSingleResult();

            aln.setNome("Antonio");

            Calendar calendar = new GregorianCalendar();
            calendar.set(2001, Calendar.MAY, 10);
            aln.setDataNascimento(calendar.getTime());

            em.merge(aln);

            em.flush();
            
            assertEquals("Antonio", aln.getNome());
       

    }

    @Test
    public void t05_insereRelatorio() {

        RelatorioParental rp = new RelatorioParental();

        rp.setCriatividade(10.0);
        rp.setLideranca(7.0);
        rp.setMotivacao(8.0);
        rp.setObservacoes("Observação feita.");
        rp.setParticipacaoEmSala(8.5);
        rp.setTrabalhoEmEquipe(5.0);

        Aluno aln;
        String jpql = "SELECT u FROM Aluno u Where u.idAluno = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 3);

       
            aln = (Aluno) query.getSingleResult();

            aln.setRelatorioParental(rp);

            em.merge(aln);
            
            em.flush();
            
            
            assertNotNull(aln.getRelatorioParental());

       

    }

    @Test
    public void t06_buscaRelatorioParental() {

        RelatorioParental rp;
        Aluno aln;

        String jpql = "SELECT u FROM Aluno u Where u.idAluno = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 3);

       
            aln = (Aluno) query.getSingleResult();

            rp = aln.getRelatorioParental();
            
           Long rpId = (long) 3;

            assertEquals(rpId, rp.getIdRelatorioParental());

       

    }

    @Test
    public void t07_atualizaRelatorioParental() {

        RelatorioParental rp;
        Aluno aln;

        String jpql = "SELECT u FROM Aluno u Where u.idAluno = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 3);
   
            aln = (Aluno) query.getSingleResult();
            rp = aln.getRelatorioParental();
            rp.setObservacoes("Observação alterada");

            em.merge(rp);          
            em.flush();
            assertEquals("Observação alterada", rp.getObservacoes());

    }

    @Test
    public void t08_removeAluno() {

        Aluno aln;

        String jpql = "SELECT u FROM Aluno u Where u.idAluno = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, 3);

            long id = 3;
        
            aln = (Aluno) query.getSingleResult();

            em.remove(aln);
            
            em.flush();
            
            Aluno apagado = em.find(Aluno.class, id);
            
            assertNull(apagado);   
    }
}
