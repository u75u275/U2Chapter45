package U2Chapter45;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;


public class JPAExample {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public static void main(String[] args) {
        JPAExample example = new JPAExample();
        System.out.println("STEP 1.");
        tmp_student student1 = example.saveStudent("SuperMan1");
        System.out.println("STEP 2.");
        tmp_student student2 = example.saveStudent("SuperMan2");
        System.out.println("STEP 3.");
        example.listStudent();
        /*        System.out.println("STEP 4.");
        example.updateStudent(student1.getStudentId(), "Херматотпупин");
        System.out.println("STEP 5.");
        example.updateStudent(student2.getStudentId(), "Гленобоб");
        System.out.println("STEP 6.");
        example.listStudent();
        System.out.println("STEP 7.");
        example.deleteStudent();
        System.out.println("STEP 8.");
        example.listStudent();*/


    }

    public tmp_student saveStudent(String studentName) {
        tmp_student student = new tmp_student();
        try {
            entityManager.getTransaction().begin();
            student.setStudentName(studentName);
            student = entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("-*-*-*-* saveStudent экзепшн");
            entityManager.getTransaction().rollback();
        }
        return student;
    }

    public void listStudent() {
        try {
            entityManager.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<tmp_student> Students = entityManager.createQuery("select s from tmp_student s").getResultList();
            for (Iterator<tmp_student> iterator = Students.iterator(); iterator.hasNext();) {
                tmp_student student = (tmp_student) iterator.next();
                System.out.println(student.getStudentName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("-*-*-*-* listStudent экзепшн "+e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    public void updateStudent(Long studentId, String studentName) {
        try {
            entityManager.getTransaction().begin();
            tmp_student student = (tmp_student) entityManager.find(tmp_student.class, studentId);
            student.setStudentName(studentName);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("-*-*-*-* updateStudent экзепшн");
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteStudent(Long studentId) {
        try {
            entityManager.getTransaction().begin();
            tmp_student student = (tmp_student) entityManager.find(tmp_student.class, studentId);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("-*-*-*-* deleteStudent экзепшн");
            entityManager.getTransaction().rollback();
        }
    }
}