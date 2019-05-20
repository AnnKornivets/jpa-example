import entity.Book;
import repository.BookRepository;
import specification.BookByPageCount;
import specification.BookByTitle;
import specification.Specification;
import specification.TwoSpecification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.GregorianCalendar;
import java.util.List;

public class TstBook {
    public static void main(String[] args) {
        EntityManagerFactory factory =
        Persistence.createEntityManagerFactory("OrmExample");
        EntityManager entityManager =factory.createEntityManager();

        BookRepository bookRepository=new BookRepository(entityManager);

        //добавление книг в БД
//        for(int i=1;i<6;i++){
//            Book book=new Book();
//            book.setTitle("Книга: " +i);
//            book.setPageCount(i);
//            book.setAddDate(new GregorianCalendar());
//            bookRepository.add(book);
//
//        }

        //получить книгу с id=1
//        Book bookById=bookRepository.getByPK(151);
//        System.out.println(bookById.getTitle());
//
//        bookById.setTitle("Новое название");
//        bookRepository.update(bookById);


        // удаление книги
//        bookRepository.delete(151);

//        List<Book> books=bookRepository.getAll();
//       for (Book book:books){
//            System.out.println(book.getTitle());
//       }

//        List<Book> booksByTitle = bookRepository.getBySpecification(new BookByTitle("Книга 3"));
//        for (Book book:booksByTitle){
//            System.out.println(book.getTitle());
//       }

        //TODO:
        //Specification<Book> multiSpec= new TwoSpecification<Book>(new BookByTitle("Книга 3"),new SomeSpec());
        //List<Book> bookList=bookRepository.getBySpecification(multiSpec);

//
//
//        List<Book> booksByTitle = bookRepository.getBySpecification(new BookByTitle("Книга 3"));
//        for (Book book:booksByTitle){
//            System.out.println(book.getTitle());
//        }

        List<Book> booksByPageCount = bookRepository.getBySpecification(new BookByPageCount(43));
       for (Book book:booksByPageCount){


            System.out.println(book.getTitle());
        }

       entityManager.close();
        factory.close();


    }
}
