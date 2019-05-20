import entity.Group;
import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TstStudent {
    public static void main(String[] args) {
        Student tre=new Student();
        tre.setName("tre");

        Student asd=new Student();
        asd.setName("asd");

     ////////////////////////////////////////////
        Group jjd=new Group();
        jjd.setGroupName("jjd");

        Group nodejs=new Group();
        nodejs.setGroupName("nodejs");


        //связываем с помощью списков

        tre.getGroups().add(jjd);
        tre.getGroups().add(nodejs);

        asd.getGroups().add(jjd);

        jjd.getStudents().add(tre);
        jjd.getStudents().add(asd);

        nodejs.getStudents().add(tre);
     ///////////////////////////////////////////////

        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("OrmExample");
        EntityManager entityManager=managerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(tre);
        entityManager.persist(asd);
        entityManager.getTransaction().commit();

        List<Student> students=entityManager.find(Group.class,902).getStudents();
        System.out.println("Сутденты группы с id");
        for (Student student:students){
            System.out.println(student.getName());
        }


        List<Group> groups=entityManager.find(Student.class, 901).getGroups();
        System.out.println("Группа студента с id");
        for (Group group:groups){
            System.out.println(group.getGroupName());
        }

        entityManager.close();
        managerFactory.close();


    }
}
