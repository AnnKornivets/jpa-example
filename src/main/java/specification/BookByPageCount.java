package specification;

import entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookByPageCount extends AbstractSpecification<Book>{
    private  int pageCount;
    private String title;


    public BookByPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Predicate toPredicate(Root<Book> bookRoot, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(bookRoot.<Integer>get("pageCount"),pageCount);
    }
}
