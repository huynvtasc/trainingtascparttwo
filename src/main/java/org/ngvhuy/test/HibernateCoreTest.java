package org.ngvhuy.test;

import org.hibernate.Session;
import org.ngvhuy.config.HibernateUtils;
import org.ngvhuy.entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HibernateCoreTest {
    public static void main(String[] args) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Long id = 8L;

            User user = new User();
            user.setFullname("Donald Trump");
            user.setUsername("donaldtrump");
            user.setPassword("password");
            user.setCreatedAt(Date.valueOf(LocalDate.now()));
            user.setModifiedAt(Date.valueOf(LocalDate.now()));

            Long userId = (Long) session.save(user);
            System.out.println("User id = " + userId);

            Long number1OfUser = session.createQuery("SELECT COUNT(id) FROM User", Long.class).uniqueResult();
            System.out.println("Number user in database: " + number1OfUser);

            Long number2OfUser = session.createQuery("SELECT COUNT(id) FROM User", Long.class).uniqueResult();
            System.out.println("Number user in database: " + number2OfUser);

            User userLoad1 = session.load(User.class, id);
            System.out.println("1st user: " + userLoad1.getFullname());

            User userLoad2 = session.find(User.class, id);
            System.out.println("2nd user: " + userLoad2.getFullname());

            User userLoad3 = session.get(User.class, id);
            System.out.println("3rd user: " + userLoad3.getFullname());

//            userFirstLoad.setFullname("Nguyen Van Huy C");
//            session.update(userFirstLoad);

            session.clear();

            User userLoad4 = session.load(User.class, 2L);
            System.out.println("4th user: " + userLoad4.getFullname());

            User userLoad5 = session.load(User.class, 2L);
            System.out.println("5th user: " + userLoad5.getFullname());

            userLoad5.setFullname("Meow Ahihihihi");

            List<User> user1s = session.createQuery("FROM User").list();
            user1s.forEach(System.out::println);
//
//            List<User> user2s = session.createQuery("FROM User").list();
//            user2s.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
