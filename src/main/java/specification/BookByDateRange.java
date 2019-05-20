package specification;

import entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;


public class BookByDateRange extends AbstractSpecification {
    private Date loadDate;

    public BookByDateRange(Date dateload) {
        this.loadDate = loadDate;
    }

    public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //  public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {
      //  return criteriaBuilder.between(order.<Date>get("loadDate"),loadDate);
  //  }



//    public List<Order> readOrdersByDateRange(final Date startDate, final Date endDate) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//
//        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
//        Root<OrderImpl> order = criteria.from(OrderImpl.class);
//        criteria.select(order);
//        criteria.where(builder.between(order.<Date>get("submitDate"), startDate, endDate));
//        criteria.orderBy(builder.desc(order.get("submitDate")));
//    }
}
