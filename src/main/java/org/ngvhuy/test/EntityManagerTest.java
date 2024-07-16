package org.ngvhuy.test;

import org.ngvhuy.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class EntityManagerTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entitymanagertest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        User user = new User();
//        user.setFullname("Neymar Jr");
//        user.setUsername("neymarjr");
//        user.setPassword("password");
//        user.setCreatedAt(new Date());
//        user.setModifiedAt(new Date());
//
//        em.persist(user);

        User userLoad1 = em.find(User.class, 2L);
        System.out.println("1st load user: " + userLoad1.getFullname());
        User userLoad2 = em.find(User.class, 2L);
        System.out.println("2nd load user: " + userLoad2.getFullname());
        User userLoad3 = em.find(User.class, 2L);
        System.out.println("3rd load user: " + userLoad3.getFullname());

        tx.commit();
        em.clear();
        emf.close();
    }
}
