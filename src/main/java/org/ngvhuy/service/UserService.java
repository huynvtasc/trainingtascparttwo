package org.ngvhuy.service;

import org.hibernate.Session;
import org.ngvhuy.config.HibernateUtils;
import org.ngvhuy.entity.User;

import java.sql.Date;
import java.time.LocalDate;

public class UserService {
    public Long saveUser() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            User user = User.builder().fullname("Nguyen Van Huy")
                    .username("huynv").password("123456")
                    .createdAt(Date.valueOf(LocalDate.now()))
                    .modifiedAt(Date.valueOf(LocalDate.now()))
                    .build();

            System.out.println("userId before persist: " + user.getId());
            session.persist(user);

            System.out.println("userId after persist: " + user.getId());
            session.flush();

            System.out.println("userId after flush: " + user.getId());

            User saved1User = session.find(User.class, user.getId());
            System.out.println("Get user from cache 1: " + saved1User.getUsername() + " --> Don't see sql before");
            User saved2User = session.find(User.class, user.getId());
            System.out.println("Get user from cache 2: " + saved2User.getUsername() + " --> Don't see sql before");

            session.getTransaction().commit();
            return user.getId();
        } catch (Exception e) {
            System.out.println("Errorrrrrrr");
        }
        return -1L;
    }

    public String saveAllUsers() {


        return "Save all users successfully !!";
    }
}
