package io.github.xisyn.spring.data.data;

import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import io.github.xisyn.spring.data.entity.PurchasedBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchasedBookRepository extends CrudRepository<PurchasedBook, Long> {

    List<PurchasedBook> findBooksByBook(Book book);

    List<PurchasedBook> findBooksByCustomer(Customer customer);
}
