package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Administrador;
import br.edu.ifpe.recife.x9academicoDescorp.model.Aluno;
import br.edu.ifpe.recife.x9academicoDescorp.model.Endereco;
import br.edu.ifpe.recife.x9academicoDescorp.model.Professor;
import br.edu.ifpe.recife.x9academicoDescorp.model.Responsavel;
import br.edu.ifpe.recife.x9academicoDescorp.model.Turma;
import br.edu.ifpe.recife.x9academicoDescorp.model.Usuario;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlTest {

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

    public static Endereco insereEndereco() {
        Endereco endereco = new Endereco();

        endereco.setCep("50.680-000");
        endereco.setCidade("olinda");
        endereco.setNumeroEndereco(123);
        endereco.setRua("abc");
        endereco.setUf("pe");

        return endereco;
    }

    @Test
    public void t01_criaAdminValido() {
        logger.info("Executando t01: CriarAdminValido");
        Administrador admin = new Administrador();

        admin.setCpf("096.328.124-09");
        admin.setNome("Gilbertojpql");
        admin.setEmail("aaa@outlook.com");
        admin.setEndereco(insereEndereco());
        admin.setSenha(".Qr324565");
        admin.setTelefone("8198989999");
        Calendar calendar = new GregorianCalendar();
        calendar.set(1980, Calendar.OCTOBER, 10);
        admin.setDataNascimento(calendar.getTime());
        admin.setCargo("Coordenador");
        admin.setLogin("admin");
        em.persist(admin);
        em.flush();
        assertNotNull(admin.getIdUsuario());
    }

    @Test
    public void t02_criaAdminInvalido() {
        logger.info("Executando t02: CriarAdminInvalido");
        Administrador admin = new Administrador();

        try {
            admin.setCpf("1898"); //cpf invalido
            admin.setNome("gilbertoJPQL"); //nome invalido
            admin.setEmail("aaa@"); //email invalido

            Endereco endereco = new Endereco();
            endereco.setCep("50.680-000");
            endereco.setCidade("olinda");
            endereco.setNumeroEndereco(123);
            endereco.setRua("abc");
            endereco.setUf("pe");
            admin.setEndereco(endereco);

            admin.setSenha("123456"); //senha invalida
            admin.setTelefone("998989"); // telefone invalido
            Calendar calendar = new GregorianCalendar();
            calendar.set(1980, Calendar.OCTOBER, 10);
            admin.setDataNascimento(calendar.getTime());
            admin.setCargo("Coordenador");
            admin.setLogin("admin t"); //login invalido
            em.persist(admin);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(6, constraintViolations.size());
            assertNull(admin.getIdUsuario());
        }
    }

    @Test
    public void t03_atualizarAdminInvalido() {
        Logger.getGlobal().log(Level.INFO, "t03 atualizarAdminInvalido");
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.cpf like :cpf", Usuario.class);
        query.setParameter("cpf", "079.678.094-32");
        Usuario usuario = query.getSingleResult();
        usuario.setSenha("12345678");

        try {
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(1, constraintViolations.size());
        }
    }

    @Test
    public void t04_criaAlunoValido() {
        logger.info("Executando t04: CriarAlunoValido");
        Aluno aluno = new Aluno();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2001, Calendar.DECEMBER, 10);
        aluno.setDataNascimento(calendar.getTime());
        aluno.setDeficiencia("Não");
        aluno.setMatricula("a2015004");
        aluno.setNome("Hugo");

        em.persist(aluno);
        em.flush();
        assertNotNull(aluno.getIdAluno());
    }

    @Test
    public void t05_criaAlunoInvalido() {
        logger.info("Executando t05: CriarAlunoInvalido");
        Aluno aluno = new Aluno();

        try {
            Calendar calendar = new GregorianCalendar();
            calendar.set(2017, Calendar.DECEMBER, 10); // Data de nascimento invalida
            aluno.setDataNascimento(calendar.getTime());
            aluno.setDeficiencia("Não");
            aluno.setMatricula("55"); //Matricula invalida
            aluno.setNome("hugo");  //Nome invalido
            em.persist(aluno);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(3, constraintViolations.size());
            assertNull(aluno.getIdAluno());
        }
    }

    @Test
    public void t06_atualizarAlunoInvalido() {
        Logger.getGlobal().log(Level.INFO, "t06 atualizarAlunoInvalido");
        TypedQuery<Aluno> query = em.createQuery("SELECT u FROM Aluno u WHERE u.matricula like :matricula", Aluno.class);
        query.setParameter("matricula", "a2015001");
        Aluno aluno = query.getSingleResult();
        aluno.setNome("aldo");

        try {
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(1, constraintViolations.size());
        }
    }

    @Test
    public void t07_criarProfessorValido() {
        logger.info("Executando t07: CriarProfessorValido");
        Professor prof = new Professor();
        Endereco endereco = new Endereco();

        prof.setNome("Gabriel");
        prof.setEmail("gabriel@gmail.com");
        prof.setTelefone("1198988989");
        prof.setCpf("055.860.924-41");

        endereco.setCep("57.740-222");
        endereco.setCidade("Recife");
        endereco.setNumeroEndereco(302);
        endereco.setRua("Rua trinta");
        endereco.setUf("PE");
        prof.setEndereco(endereco);

        prof.setLogin("prof_gabriel");
        prof.setSenha(".Dw143476");
        Calendar calendar = new GregorianCalendar();
        calendar.set(1980, Calendar.OCTOBER, 10);
        prof.setDataNascimento(calendar.getTime());

        em.persist(prof);

        em.flush();

        assertNotNull(prof.getIdUsuario());
    }

    @Test
    public void t08_criaProfInvalido() {
        logger.info("Executando t08: CriarProfInvalido");
        Professor prof = new Professor();

        try {
            Endereco endereco = new Endereco();

            prof.setNome("gabriel"); //nome invalido
            prof.setEmail("gabriel@"); //email invalido
            prof.setTelefone("11989889"); //telefone invalido
            prof.setCpf("233.455.154081"); //cpf invalido

            endereco.setCep("57.740-222");
            endereco.setCidade("Recife");
            endereco.setNumeroEndereco(302);
            endereco.setRua("Rua trinta");
            endereco.setUf("PE");
            prof.setEndereco(endereco);

            prof.setLogin("prof_gabriel");
            prof.setSenha(".Dw143476");
            Calendar calendar = new GregorianCalendar();
            calendar.set(1980, Calendar.OCTOBER, 10);
            prof.setDataNascimento(calendar.getTime());
            em.persist(prof);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(4, constraintViolations.size());
            assertNull(prof.getIdUsuario());
        }
    }

    @Test
    public void t09_atualizarProfInvalido() {
        Logger.getGlobal().log(Level.INFO, "t09 atualizarProfInvalido");
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.login like :login", Usuario.class);
        query.setParameter("login", "professor");
        Usuario usuario = query.getSingleResult();
        usuario.setSenha("123432");

        try {
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(1, constraintViolations.size());
        }
    }

    @Test
    public void t10_criaResponsavelValido() {
        logger.info("Executando t10: CriarResponsavelValido");
        Endereco endereco = new Endereco();

        endereco.setCep("50.680-000");
        endereco.setCidade("Recife");
        endereco.setNumeroEndereco(123);
        endereco.setRua("Rua Cinco");
        endereco.setUf("pe");

        Responsavel resp = new Responsavel();

        resp.setCpf("052.107.794-08");
        resp.setNome("Gilberto");
        resp.setEmail("aaa@gmail.com");
        resp.setEndereco(endereco);
        resp.setSenha(".Qy123454");
        resp.setTelefone("8199898965");
        Calendar calendar = new GregorianCalendar();
        calendar.set(1980, Calendar.OCTOBER, 10);
        resp.setDataNascimento(calendar.getTime());

        resp.setLogin("resp_123");

        em.persist(resp);
        em.flush();
        assertNotNull(resp.getIdUsuario());
    }

    @Test
    public void t11_criaResponsavelInvalido() {
        logger.info("Executando t11: CriarResponsavelInvalido");
        Responsavel resp = new Responsavel();

        try {
            Endereco endereco = new Endereco();

            endereco.setCep("50.680-000");
            endereco.setCidade("Recife");
            endereco.setNumeroEndereco(123);
            endereco.setRua("Rua Cinco");
            endereco.setUf("pe");

            resp.setCpf("054.764.234-08"); //cpf invalido
            resp.setNome("GilbertO"); //nome invalido
            resp.setEmail("aaa@gmail.com");
            resp.setEndereco(endereco);
            resp.setSenha(".Qy123454");
            resp.setTelefone("8199898965545432"); //numero invalido
            Calendar calendar = new GregorianCalendar();
            calendar.set(2021, Calendar.OCTOBER, 10);
            resp.setDataNascimento(calendar.getTime()); //data nascimento invalida

            resp.setLogin("resp_123");
            em.persist(resp);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(4, constraintViolations.size());
            assertNull(resp.getIdUsuario());
        }
    }

    @Test
    public void t12_atualizarResponsavelInvalido() {
        Logger.getGlobal().log(Level.INFO, "t12 atualizarResponsavelInvalido");
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.cpf like :cpf", Usuario.class);
        query.setParameter("cpf", "798.352.954-68");
        Usuario usuario = query.getSingleResult();
        usuario.setEmail("valdeir@");

        try {
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(1, constraintViolations.size());
        }
    }

    @Test
    public void t13_criaTurmaValida() {
        logger.info("Executando t13: CriarTurmaValida");
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
    public void t14_criaTurmaInvalida() {
        logger.info("Executando t14: CriarTurmaInvalida");
        Turma turma = new Turma();

        try {
            turma.setNumeroSala(50); //NumeroSala invalido
            turma.setQtdAluno(5); //Quantidade de alunos invalida
            turma.setSerie("Sexta serie"); //Serie invalida
            turma.setTurno("Noite");
            em.persist(turma);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(3, constraintViolations.size());
            assertNull(turma.getIdTurma());
        }
    }

    @Test
    public void t15_atualizarTurmaInvalida() {
        Logger.getGlobal().log(Level.INFO, "t15 atualizarTurmaInvalida");
        TypedQuery<Turma> query = em.createQuery("SELECT u FROM Turma u WHERE u.serie like :serie", Turma.class);
        query.setParameter("serie", "Primeira");
        Turma turma = query.getSingleResult();
        turma.setQtdAluno(40);

        try {
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(1, constraintViolations.size());
        }
    }
}
