package konkon.service;

import java.util.List;

public interface GeneralService<E> {
  E findById(Long id);
  List<E> findByName(String name);
  List<E> findAll();
  void update(E e);
  void add (E e);
  void delete(Long id);
}
