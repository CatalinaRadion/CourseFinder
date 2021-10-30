package com.ibm.coursefinder.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class RESTService<T, TDTO, ID> {
    protected JpaRepository<T, ID> repo;
    protected Function<T, TDTO> function;
    protected Function<TDTO, T> reverseFunction;

    public RESTService(JpaRepository<T, ID> repo,
                       Function<T, TDTO> function,
                       Function<TDTO, T> reverseFunction) {
        this.repo = repo;
        this.function = function;
        this.reverseFunction = reverseFunction;
    }

    public List<TDTO> getAll() {
        return repo.findAll().stream().map(function).collect(Collectors.toList());
    }

    public Optional<TDTO> get(ID id) {
        return repo.findById(id).map(function);
    }

    public Optional<TDTO> delete(ID id) {
        var opt = repo.findById(id);
        opt.ifPresent(($) -> repo.deleteById(id));
        return opt.map(function);
    }

    public TDTO post(TDTO object) {
        System.out.println(object.toString());
        return function.apply(repo.save(reverseFunction.apply(object)));
    }

    //shouldn't be done by reflection
    //could be, but shouldn't
    public abstract Optional<TDTO> put(ID id, TDTO newObject);
}
