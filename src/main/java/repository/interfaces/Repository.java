package repository.interfaces;

/**
 * Abstract interface for swimmingApp.repository objects.
 * @param <Tid> Type of ID of the objects stored in swimmingApp.repository
 * @param <T> Type of the objects stored in swimmingApp.repository
 */
public interface Repository<Tid, T> {
    Tid add(T elem);
    void delete(T elem);
    void update(T elem, Tid id);
    T findById(Tid id);
}
