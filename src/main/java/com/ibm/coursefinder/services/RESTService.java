package com.ibm.coursefinder.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class RESTService<T, ID> {
    protected JpaRepository<T, ID> repo;


    public RESTService(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    public List<T> getAll() {
        return repo.findAll();
    }

    public Optional<T> get(ID id) {
        return repo.findById(id);
    }

    public Optional<T> delete(ID id) {
        var opt = repo.findById(id);
        opt.ifPresent(($) -> repo.deleteById(id));
        return opt;
    }

    public T post(T object) {
        return repo.save(object);
    }

    //shouldn't be done by reflection
    //could be, but shouldn't
    public abstract T put(ID id, T newObject);
}
