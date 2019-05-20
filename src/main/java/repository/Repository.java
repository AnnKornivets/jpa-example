package repository;

import entity.Book;
import org.eclipse.persistence.jpa.jpql.parser.TrimExpression;
import specification.Specification;

import java.util.List;

public interface Repository<T,PK> {
    void add(T t); //добавление записи
    void update(T t);//обновление записи в БД
    void delete(PK pk); //удаление записи
    T getByPK(PK pk); //получение записи по идентификатору
    List<T> getAll();// получение всех записей
    List<T> getBySpecification(Specification spec);


}
