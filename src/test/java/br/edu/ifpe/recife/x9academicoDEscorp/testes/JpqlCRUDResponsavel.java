package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Endereco;
import br.edu.ifpe.recife.x9academicoDescorp.model.Responsavel;
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

/**
 *
 * @author aldo_neto
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlCRUDResponsavel {

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
    public void t01_criaResponsavel() {

        Endereco endereco = new Endereco();

        endereco.setCep("22.244-556");
        endereco.setCidade("Recife");
        endereco.setNumeroEndereco(123);
        endereco.setRua("Rua Cinco");
        endereco.setUf("pe");

        Responsavel resp = new Responsavel();

        resp.setCpf("826.176.274-20");
        resp.setNome("Gilberto");
        resp.setEmail("aaa@bbb.com");
        resp.setEndereco(endereco);
        resp.setSenha("1234.Op");
        resp.setTelefone("8199898976");
        Calendar calendar = new GregorianCalendar();
        calendar.set(1980, Calendar.OCTOBER, 10);
        resp.setDataNascimento(calendar.getTime());

        resp.setLogin("resp_123");

        em.persist(resp);
        
        em.flush();
        
        assertNotNull(resp.getIdUsuario());
        
    }

    @Test
    public void t02_listarResponsaveis() {
        List<Responsavel> responsaveis;

        String jpql = "SELECT u FROM Professor u";
        Query query = em.createQuery(jpql);

       
            responsaveis = (List<Responsavel>) query.getResultList();
            
            
            assertEquals(2, query.getResultList().size());

    }

    @Test
    public void t03_buscaResponsavel() {
        Responsavel resp = new Responsavel();

        String jpql = "SELECT u FROM Usuario u Where u.nome = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "Valdeir");

       
            resp = (Responsavel) query.getSingleResult();
            System.out.println(resp.getNome());

            
            assertEquals("Valdeir", resp.getNome());
    }

    @Test
    public void t04_atualizaResponsavel() {
        Responsavel resp;

        String jpql = "SELECT u FROM Usuario u Where u.nome = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "Valdeir");

        
            resp = (Responsavel) query.getSingleResult();

            resp.setNome("Josias");

            resp.setEmail("josias@hotmail.com");

            em.merge(resp);
            
            em.flush();
            
           assertEquals(0, query.getResultList().size());
            
    }

    @Test
    public void t05_removeResponsavel() {
        
        Long id = (long) 5;
        
        Responsavel resp;

        String jpql = "SELECT u FROM Usuario u Where u.idUsuario = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, id);

        
            resp = (Responsavel) query.getSingleResult();

            em.remove(resp);
            
            em.flush();
            
            Responsavel apagado =  em.find(Responsavel.class, id);
            
            assertNull(apagado);

    }
}
