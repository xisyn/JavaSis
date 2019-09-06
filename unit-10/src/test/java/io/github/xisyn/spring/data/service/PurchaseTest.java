package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.SpringDataApplication;
import io.github.xisyn.spring.data.data.*;
import io.github.xisyn.spring.data.entity.Author;
import io.github.xisyn.spring.data.entity.AuthorOfBook;
import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataApplication.class)
public class PurchaseTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorOfBookRepository authorOfBookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchasedBookRepository purchasedBookRepository;

    @Autowired
    private PurchasedBookService purchasedBookService;

    @Autowired
    private CustomerService customerService;

    @Before
    public void init() {
        Customer customer1 = new Customer();
        customer1.setFullName("Mister Customer");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFullName("Missis Customer");
        customerRepository.save(customer2);

        Book book1 = new Book();
        book1.setDescription("Моя любимая книга");
        book1.setTitle("Любимая");
        book1.setYear(1984);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setDescription("Какая-то книга");
        book2.setTitle("Какая-то");
        book2.setYear(1999);
        bookRepository.save(book2);

        Author author1 = new Author();
        author1.setFirstname("Mister");
        author1.setLastname("Author");

        authorRepository.save(author1);

        AuthorOfBook aob1 = new AuthorOfBook();
        aob1.setAuthor(author1);
        aob1.setBook(book1);
        authorOfBookRepository.save(aob1);

        AuthorOfBook aob2 = new AuthorOfBook();
        aob2.setAuthor(author1);
        aob2.setBook(book2);
        authorOfBookRepository.save(aob2);
    }

    @Test
    public void purchase() {

        Book bookTest1 = bookService.findByTitle("Любимая");
        Customer customerTest1 = customerService.findByFullName("Mister Customer");

        Book bookTest2 = bookService.findByTitle("Какая-то");
        Customer customerTest2 = customerService.findByFullName("Missis Customer");

        purchasedBookService.purchaseBook(bookTest1, customerTest1, 100);
        purchasedBookService.purchaseBook(bookTest2, customerTest2, 50);
        purchasedBookService.purchaseBook(bookTest2, customerTest1, 60);

        assertEquals(100, purchasedBookService.bookAmountPurchases(bookTest1), 0);
        assertEquals(110, purchasedBookService.bookAmountPurchases(bookTest2), 0);
        assertEquals(160, purchasedBookService.customerAmountPurchases(customerTest1), 0);
        assertEquals(50, purchasedBookService.customerAmountPurchases(customerTest2), 0);
    }
}
