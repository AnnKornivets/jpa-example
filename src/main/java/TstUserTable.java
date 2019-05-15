import entity.UserTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TstUserTable {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("OrmExample");
        EntityManager entityManager =entityManagerFactory.createEntityManager(); //менеджер управления сущностями, сможем взаимодействовать с БД
        entityManager.getTransaction().begin(); //начинаем что-то делать в БД
                //добавление
//        UserTable userTable=new UserTable();
//        userTable.setLogin("sdf");
//
//        entityManager.persist(userTable);//добавляет запись в таблицу,пока не подтвердили действия

        //получение пользователя
        UserTable userFromDB = entityManager.find(UserTable.class,1); //поиск по уникальному значению
        System.out.println(userFromDB.getLogin());

        entityManager.getTransaction().commit(); //подтверждение действий .rollback()-если есть ошибка, все возвращает назад

        entityManager.close();  // закрываем менеджер
        entityManagerFactory.close(); //закрываем фабрику


    }
}
