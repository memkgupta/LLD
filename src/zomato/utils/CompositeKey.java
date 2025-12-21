package zomato.utils;

import java.util.Objects;

public class CompositeKey<T, D> {

    private final T key1;
    private final D key2;

    public CompositeKey(T key1, D key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public T getKey1() {
        return key1;
    }

    public D getKey2() {
        return key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey<?, ?>)) return false;

        CompositeKey<?, ?> that = (CompositeKey<?, ?>) o;
        return Objects.equals(key1, that.key1)
                && Objects.equals(key2, that.key2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key1, key2);
    }
}
