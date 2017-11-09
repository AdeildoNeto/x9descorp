package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Administrador;
import br.edu.ifpe.recife.x9academicoDescorp.model.Endereco;
import br.edu.ifpe.recife.x9academicoDescorp.model.Usuario;
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
public class queryAdmin {

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

    //Selects
    @Test
    public void t01_adminPorNome() {
        logger.info("Executando t01: SELECT u FROM Administrador u WHERE u.nome LIKE :nome");
        TypedQuery<Administrador> query = em.createQuery(
                "SELECT u FROM Administrador u WHERE u.nome LIKE :nome",
                Administrador.class);
        query.setParameter("nome", "Gilberto%");
        List<Administrador> administradores = query.getResultList();

        administradores.forEach((administrador) -> {
            assertTrue(administrador.getNome().startsWith("Gilberto"));
        });
        assertEquals(1, administradores.size());
    }

    @Test
    public void t02_adminPorLogin() {

        logger.info("Executando t02: SELECT u FROM Administrador u WHERE u.login = ?1 ");
        Query query = em.createQuery(
                "SELECT u FROM Administrador u WHERE u.login = ?1 ",
                Administrador.class);
        query.setParameter(1, "administrador");
        Administrador administrador = (Administrador) query.getSingleResult();

        logger.info(administrador.getLogin());
        assertEquals("administrador", administrador.getLogin());
    }

    @Test
    public void t03_adminPorId() {

        logger.info("Executando t03: NamedQuery Usuario.PorId");

        Query query = em.createNamedQuery("Usuario.PorId");
        query.setParameter(1, 1);

        Usuario usuario = (Usuario) query.getSingleResult();

        long id = usuario.getIdUsuario();
        assertEquals(1, id);
    }

    @Test
    public void t04_enderecoAdmin() {
        logger.info("Executando t04: Buscando Endereco de Usuario");

        Endereco endr;

        Query query = em.createQuery("Select u FROM Usuario u WHERE u.idUsuario =?1");
        query.setParameter(1, 1);

        Usuario user = (Usuario) query.getSingleResult();

        endr = user.getEndereco();

        logger.info(endr.getRua());
        assertEquals("Olinda", endr.getCidade());
    }
}
