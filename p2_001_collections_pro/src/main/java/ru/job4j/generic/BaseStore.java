package ru.job4j.generic;

public abstract class BaseStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> data;
    protected int size;

    public BaseStore(int quantity) {
        this.data = new SimpleArray<>(quantity);
        this.size = quantity;
    }

    @Override
    public void add(T model) {
        this.data.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int idx = indexById(id);
        if (idx != -1) {
            this.data.set(idx, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int idx = indexById(id);
        if (idx != -1) {
            this.data.set(idx, null);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T element : this.data) {
            if (element == null) {
                continue;
            }
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }

    public int indexById(String id) {
        int result = -1;
        for (int i = 0; i != this.size; i++) {
            var element = this.data.get(i);
            if (element == null) {
                continue;
            }
            if (element.getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
