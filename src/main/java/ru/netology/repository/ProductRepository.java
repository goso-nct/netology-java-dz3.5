package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {

  private Product[] storage = new Product[0];

  public int size() {
    return storage.length;
  }

  public void save(Product item) {
    if (findById(item.getId()) == null ) {
      Product[] tmp = new Product[storage.length + 1];
      System.arraycopy(storage, 0, tmp, 0, storage.length);
      tmp[tmp.length - 1] = item;
      storage = tmp;
    }
  }

  public Product[] getAll() {
    return storage;
  }

  public Product findById(int id) {
    for (Product item : storage) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  public void removeById(int id) {
    if (findById(id) != null) {
      Product[] tmp = new Product[storage.length - 1];
      int index = 0;
      for (Product item : storage) {
        if (item.getId() != id) {
          tmp[index] = item;
          index++;
        }
      }
      storage = tmp;
    }
  }

  // for tests:
  void setStorage(Product[] storage) {
    this.storage = storage;
  }

}
