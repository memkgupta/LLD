package zomato.interfaces;

import zomato.models.Order;

public interface Consumer <T>{
    void subscribe(Producer<T> producer);

    void notification();
}
