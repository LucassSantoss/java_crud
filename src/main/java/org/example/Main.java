package org.example;

import jakarta.persistence.EntityManager;
import org.example.dao.AlunoDao;
import org.example.dao.AlunoDao_hibernate;
import org.example.model.Aluno;
import org.example.service.JPAUtil;

import java.math.BigDecimal;
import java.util.logging.Level;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Aluno aluno = new Aluno("Lucas", "213", "@gmail", BigDecimal.valueOf(10), BigDecimal.valueOf(8), BigDecimal.valueOf(9));
        Aluno aluno2 = new Aluno("Tiago", "511", "@gmail", BigDecimal.valueOf(10), BigDecimal.valueOf(8), BigDecimal.valueOf(9));

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao_hibernate(em);

        em.getTransaction().begin();

        dao.cadastrar(aluno);
        dao.cadastrar(aluno2);

        em.getTransaction().commit();

        em.close();
    }
}