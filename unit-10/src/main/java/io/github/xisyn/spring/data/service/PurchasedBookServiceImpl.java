package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.data.PurchasedBookRepository;
import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import io.github.xisyn.spring.data.entity.PurchasedBook;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PurchasedBookServiceImpl implements PurchasedBookService {

    private final PurchasedBookRepository purchasedBookRepository;

    public PurchasedBookServiceImpl(PurchasedBookRepository purchasedBookRepository) {
        this.purchasedBookRepository = purchasedBookRepository;
    }

    @Override
    public void purchaseBook(Book book, Customer customer, BigDecimal cost) {
        PurchasedBook purchasedBook = new PurchasedBook();
        purchasedBook.setBook(book);
        purchasedBook.setCustomer(customer);
        purchasedBook.setCost(cost);

        purchasedBookRepository.save(purchasedBook);
    }

    @Override
    public BigDecimal bookAmountPurchases(Book book) {
        BigDecimal price = BigDecimal.valueOf(0);
        List<PurchasedBook> purchasedBooks = purchasedBookRepository.findBooksByBook(book);
        for (PurchasedBook purchasedBook : purchasedBooks) {
            price = price.add(purchasedBook.getCost());
        }
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal customerAmountPurchases(Customer customer) {
        BigDecimal price = BigDecimal.valueOf(0);
        List<PurchasedBook> purchasedBooks = purchasedBookRepository.findBooksByCustomer(customer);
        for (PurchasedBook purchasedBook : purchasedBooks) {
            price = price.add(purchasedBook.getCost());
        }
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void save(PurchasedBook purchasedBook) {
        purchasedBookRepository.save(purchasedBook);
    }
}
