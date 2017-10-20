/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDEscorp.testes;

import br.edu.ifpe.recife.x9academicoDescorp.model.Endereco;
import br.edu.ifpe.recife.x9academicoDescorp.model.Professor;
import br.edu.ifpe.recife.x9academicoDescorp.model.Turma;
import br.edu.ifpe.recife.x9academicoDescorp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author aldo_neto
 */
public class Main {

    
      public static List<Turma> criarTurmas()
    {
        List<Turma> turma = new ArrayList();
        
        Turma turma1 = new Turma();
        turma1.setNumeroSala(0);
        turma1.setQtdAluno(10);
        turma1.setSerie("Primeira");
        turma1.setTurno("Manha");
        
        
          Turma turma2 = new Turma();
        turma2.setNumeroSala(0);
        turma2.setQtdAluno(10);
        turma2.setSerie("Primeira");
        turma2.setTurno("Manha");
        
        
        
        turma.add(turma1);
        turma.add(turma2);
        
        return turma;
    }

    
    public static void insereUsuario() {
        Endereco endereco = new Endereco();

        
        
        endereco.setCep("12345");
        endereco.setCidade("olinda");
        endereco.setNumeroEndereco(123);
        endereco.setRua("abc");
        endereco.setUf("pe");

        Professor prof = new Professor();

        prof.setCpf("1898");

        prof.setEmail("aaa@bbb.com");
        prof.setEndereco(endereco);
        prof.setSenha("1234");
        prof.setTelefone("998989");
        prof.setEndereco(endereco);
        prof.setTurmas(criarTurmas());
       
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("x9_PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".

            et.begin();
            em.persist(prof);

            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                System.out.println("ERRO: " + ex);
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
    
  
    public static void main(String[] args) {

        insereUsuario();

        System.out.println("teste");

    }

}
