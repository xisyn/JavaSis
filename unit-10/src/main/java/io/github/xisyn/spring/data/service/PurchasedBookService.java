package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.entity.Book;
import io.github.xisyn.spring.data.entity.Customer;
import io.github.xisyn.spring.data.entity.PurchasedBook;

import java.math.BigDecimal;

public interface PurchasedBookService {

    void save(PurchasedBook purchasedBook);

    void purchaseBook(Book book, Customer customer, BigDecimal cost);

    BigDecimal bookAmountPurchases(Book book);

    BigDecimal customerAmountPurchases(Customer customer);
}
