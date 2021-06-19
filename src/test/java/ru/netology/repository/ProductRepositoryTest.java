package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository actual = new ProductRepository();
    ProductRepository expected = new ProductRepository();

    Book b1 = new Book( 1, "first book mr. First", 111, "mr. First");
    Book b2 = new Book( 2, "first book mr. Second", 222, "mr. Second");
    Book b3 = new Book( 3, "second book mr. Second", 333, "mr. Second");
    Smartphone s1 = new Smartphone(4, "a20", 11, "Samsung" );
    Smartphone s2 = new Smartphone(5, "s21", 22, "Samsung" );
    Smartphone s3 = new Smartphone(6, "a40", 33, "Samsung" );
    Smartphone s4 = new Smartphone(7, "iphone 5", 44, "Apple" );
    Smartphone s5 = new Smartphone(8, "iphone 11 pro", 55, "Apple" );
    Smartphone s6 = new Smartphone(9, "iphone xs", 22, "Apple" );

    @Test
    void save_shouldSave() {
        actual.save(b1);
        actual.save(s1);
        actual.save(s2);
        assertEquals(3, actual.size());
        expected.setStorage(new Product[]{b1, s1, s2});
        assertArrayEquals(expected.findAll(), actual.findAll());
    }

    @Test
    void save_shouldDontSave() {
        actual.setStorage(new Product[]{b1, b2, s1, s2, s3});
        actual.save(s1);
        assertEquals(5, actual.size());
    }

    @Test
    void findById_shouldFind() {
        actual.setStorage(new Product[]{b1, b2, s1, s4, s5});
        Smartphone found = (Smartphone) actual.findById(s1.getId());
        assertEquals(s1, found);
    }

    @Test
    void findById_shouldDontFind() {
        actual.setStorage(new Product[]{b1, b2, s1, s4, s5});
        assertNull(actual.findById(100));
    }

    @Test
    void removeById_shouldRemove() {
        actual.setStorage(new Product[]{b1, b2, s1, s4, s5});
        actual.removeById(s1.getId());
        assertEquals(4, actual.size());
        expected.setStorage(new Product[]{b1, b2, s4, s5});
        assertArrayEquals(expected.findAll(), actual.findAll());
    }

    @Test
    void removeById_shouldDontRemove() {
        actual.setStorage(new Product[]{b1, b2, s1, s4, s5});
        actual.removeById(100);
        assertEquals(5, actual.size());
    }

}