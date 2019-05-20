package repository;

import entity.Book;
import specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.List;

public class BookRepository implements Repository<Book,Integer> {
    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
//добавление записей
    public void add(Book book) {
        //начали транзакцию
entityManager.getTransaction().begin();

entityManager.persist(book); //добавление новой записи в БД

//подтвердили транзакцию
entityManager.getTransaction().commit();
    }

    public void update(Book book) {
        entityManager.getTransaction().begin();
entityManager.merge(book); //обновление существующей записи в БД
        entityManager.getTransaction().commit();

    }
//удаление записи
    public void delete(Integer integer) {
        entityManager.getTransaction().begin();

       Book book=getByPK(integer); //получили обьект из БД
       entityManager.remove(book); //удаление записи в БД

        entityManager.getTransaction().commit();

    }
//получение одной записи
    public Book getByPK(Integer integer) {

        return entityManager.find(Book.class, integer);
    }
  //  получение всех записей
    public List<Book> getAll() {
        // 1.named queries
//        TypedQuery<Book>query =entityManager.createNamedQuery("Book.findAll",Book.class);
//        List<Book> books= query.getResultList();

        //2. JPQL
//        Query query=entityManager.createQuery("SELECT b FROM Book b");
//        List<Book> books= (List<Book>)query.getResultList();


        //3. Criteria API
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery= criteriaBuilder.createQuery(Book.class);
        Root<Book> root=criteriaQuery.from(Book.class);
        criteriaQuery.select(root);

        TypedQuery<Book> typedQuery=entityManager.createQuery(criteriaQuery);
        List<Book> books= typedQuery.getResultList();

        return books;
    }

    public List<Book> getBySpecification(Specification spec) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery=criteriaBuilder.createQuery(spec.getType());
        Root<Book> root=criteriaQuery.from(spec.getType());

        Predicate condition= spec.toPredicate(root,criteriaBuilder);
        criteriaQuery.where(condition);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Book getBookByTitle(String title){
        //1.named quary
//        TypedQuery<Book> query=entityManager.createNamedQuery("Book.findDyTitle",Book.class);
//        query.setParameter("title",title); //установка значений для параметра
//        Book book=query.getSingleResult();

        //2.JPQL
//        TypedQuery<Book> query= entityManager.createQuery("SELECT b FROM Book b WHERE b.title=:title",Book.class);
//        query.setParameter("title",title);
//        Book book=query.getSingleResult();

        //3.Criteria API
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery= criteriaBuilder.createQuery(Book.class);
        Root<Book> root=criteriaQuery.from(Book.class);
        criteriaQuery.select(root);

        Predicate condition = criteriaBuilder.equal(root.<String>get("title"),title);
        criteriaQuery.select(root).where(condition);//"SELECT b FROM Book b WHERE b.title=" + title
        TypedQuery<Book> query=entityManager.createQuery(criteriaQuery);
        Book book=query.getSingleResult();
        return book;
    }

   public Book getBookByPageCount(int pageCount){
//        //2.JPQL
////     TypedQuery<Book> query= entityManager.createQuery("SELECT b FROM Book b WHERE b.pageCount>:pageCount",Book.class);
////        query.setParameter("pageCount",pageCount);
////        Book book=query.getSingleResult();
////        return book;

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery= criteriaBuilder.createQuery(Book.class);
        Root<Book> root=criteriaQuery.from(Book.class);
        criteriaQuery.select(root);

       Predicate condition = criteriaBuilder.equal(root.<Integer>get("pageCount"),pageCount);
        criteriaQuery.select(root).where(condition);//"SELECT b FROM Book b WHERE b.title=" + title
        TypedQuery<Book> query=entityManager.createQuery(criteriaQuery);
        Book book=query.getSingleResult();
        return book;



    }
}
