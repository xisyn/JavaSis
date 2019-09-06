package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import io.github.xisyn.spring.data.entity.PurchasedBook;

public interface PurchasedBookService {

    void save(PurchasedBook purchasedBook);

    void purchaseBook(Book book, Customer customer, double cost);

    double bookAmountPurchases(Book book);

    double customerAmountPurchases(Customer customer);
}
