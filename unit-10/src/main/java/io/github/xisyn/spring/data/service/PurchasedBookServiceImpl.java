package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.data.PurchasedBookRepository;
import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import io.github.xisyn.spring.data.entity.PurchasedBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedBookServiceImpl implements PurchasedBookService {

    private final PurchasedBookRepository purchasedBookRepository;

    public PurchasedBookServiceImpl(PurchasedBookRepository purchasedBookRepository) {
        this.purchasedBookRepository = purchasedBookRepository;
    }

    @Override
    public void purchaseBook(Book book, Customer customer, double cost) {
        PurchasedBook purchasedBook = new PurchasedBook();
        purchasedBook.setBook(book);
        purchasedBook.setCustomer(customer);
        purchasedBook.setCost(cost);

        purchasedBookRepository.save(purchasedBook);
    }

    @Override
    public double bookAmountPurchases(Book book) {
        double price = 0;
        List<PurchasedBook> purchasedBooks = purchasedBookRepository.findBooksByBook(book);
        for (PurchasedBook purchasedBook : purchasedBooks) {
            price += purchasedBook.getCost();
        }
        return price;
    }

    @Override
    public double customerAmountPurchases(Customer customer) {
        double price = 0;
        List<PurchasedBook> purchasedBooks = purchasedBookRepository.findBooksByCustomer(customer);
        for (PurchasedBook purchasedBook : purchasedBooks) {
            price += purchasedBook.getCost();
        }
        return price;
    }

    @Override
    public void save(PurchasedBook purchasedBook) {
        purchasedBookRepository.save(purchasedBook);
    }
}
