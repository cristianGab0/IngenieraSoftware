package com.gt.umg.ing.software.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
public class CommonService<E,I, Repo extends JpaRepository<E, I>> {

    @Autowired
    protected Repo repository;

    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<E> findById(I id) {
        return repository.findById(id);
    }

    @Transactional()
    public E save(E entity) {
        return repository.save(entity);
    }

    @Transactional()
    public void deleteById(I id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
