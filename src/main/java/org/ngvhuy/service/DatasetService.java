package org.ngvhuy.service;

import org.hibernate.Session;
import org.ngvhuy.config.HibernateUtils;
import org.ngvhuy.entity.Dataset;
import org.ngvhuy.entity.DatasetValue;

import java.time.Instant;
import java.util.List;

public class DatasetService {
    public Long save() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Dataset dataset = Dataset.builder().name("name1").fields("LAC_CELL").rangeInput(false)
                    .createdAt(Instant.now()).createdBy("Admin").updatedAt(Instant.now()).updatedBy("Admin").build();

            System.out.println("Id của dataset trước khi persist: " + dataset.getId());

            session.persist(dataset);

            System.out.println("Id của dataset sau khi persist: " + dataset.getId());

            session.flush();
            session.getTransaction().commit();

            return dataset.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1L;
    }

    public void getDatasetThenGetDataset() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            Long id = 3L;

            Dataset dataset1 = session.load(Dataset.class, id);
            System.out.println("Load lần 1: " + dataset1.getName());

            Dataset dataset2 = session.load(Dataset.class, id);
            System.out.println("Load lần 2: " + dataset2.getName());

            session.clear();

            Dataset dataset3 = session.load(Dataset.class, id);
            System.out.println("Load lần 3: " + dataset3.getName());

            Dataset dataset4 = session.load(Dataset.class, id);
            System.out.println("Load lần 4: " + dataset4.getName());

            List<DatasetValue> values = dataset4.getDatasetValues();
            System.out.println("..............");
            values.forEach(v -> System.out.println(v.getValue()));

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDatasetThenGetValueInCache() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Dataset dataset1 = session.load(Dataset.class, 2L);
            System.out.println("Load lần 1: " + dataset1.getName());

            dataset1.getDatasetValues().size();
            System.out.println("Đã gọi hàm .getDatasetValue().size() ");

            DatasetValue value1 = session.load(DatasetValue.class, 1L);
            System.out.println("Load value1 sau khi load dataset: " + value1.getValue());

            DatasetValue value2 = session.load(DatasetValue.class, 1L);
            System.out.println("Load value2 sau khi load dataset: " + value2.getValue());

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDatasetValueThenGetDataset() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            Long idValue = 3L;
            Long idDataset = 3L;

            DatasetValue value1 = session.find(DatasetValue.class, idValue);
            System.out.println("Load dataset value lần 1: " + value1.getValue());

            Dataset dataset = value1.getDataset();
            System.out.println("....................");

            Dataset dataset1 = session.load(Dataset.class, idDataset);
            System.out.println("....................");

            System.out.println(dataset.getName());
            System.out.println("....................");
            System.out.println(dataset1.getName());

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Long id = 3L;

            List<Dataset> datasets = session.createQuery("FROM Dataset ").list();

            System.out.println("............");
            Dataset dataset = session.load(Dataset.class, id);
            System.out.println("............");
            System.out.println("Id của dataset: " + dataset.getId());

            System.out.println("Số lượng dataset khi .size(): " + datasets.size());
            Long quantity = session.createQuery("SELECT COUNT(id) FROM Dataset ", Long.class).uniqueResult();
            System.out.println("Số lượng dataset khi count: " + quantity);
            System.out.println("............");
            Dataset dataset1 = session.createQuery("FROM Dataset d WHERE d.id = :id", Dataset.class)
                    .setParameter("id", id)
                    .uniqueResult();
            System.out.println("Id dataset được load khi dùng query: " + dataset1.getId());

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public Long persistThenSleepTenSeconds() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Dataset dataset = Dataset.builder()
                    .name("name1").fields("LAC_CELL").rangeInput(false)
                    .createdAt(Instant.now()).createdBy("Admin")
                    .updatedAt(Instant.now()).updatedBy("Admin").build();

            session.persist(dataset);

            Thread.sleep(10_000);

            session.flush();

            session.getTransaction().commit();
            return dataset.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1L;
    }

    public Long persistNotInTransaction() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
//            session.getTransaction().begin();
            Dataset dataset = Dataset.builder()
                    .name("name1").fields("LAC_CELL").rangeInput(false)
                    .createdAt(Instant.now()).createdBy("Admin")
                    .updatedAt(Instant.now()).updatedBy("Admin").build();
            session.persist(dataset);
            session.flush();
//            session.getTransaction().commit();
            return dataset.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
//            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1L;
    }

    public void updateNotInTransaction() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Long id = 1L;
            Dataset dataset = session.get(Dataset.class, id);
            dataset.setFields("Hanoi");
            session.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadNotInTransaction() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Long id = 1L;
            Dataset dataset = session.get(Dataset.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteNotInTransaction() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Long id = 1L;
            Dataset dataset = session.get(Dataset.class, id);
            session.delete(dataset);
            session.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
