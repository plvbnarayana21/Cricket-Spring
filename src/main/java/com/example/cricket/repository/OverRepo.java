package com.example.cricket.repository;

import com.example.cricket.Beans.Over;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class OverRepo implements OverRepository {

    private final OverRepository overRepository;

    @Override
    public <S extends Over> List<S> saveAll(Iterable<S> entities) {
        return overRepository.saveAll(entities);
    }

    @Override
    public Optional<Over> findById(String id) {
        return overRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return overRepository.existsById(id);
    }

    @Override
    public List<Over> findAll() {
        return overRepository.findAll();
    }

    @Override
    public List<Over> findAllById(Iterable<String> ids) {
        return overRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return overRepository.count();
    }

    @Override
    public void deleteById(String id) {
        overRepository.deleteById(id);
    }

    @Override
    public void delete(Over entity) {
        overRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        overRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends Over> entities) {
        overRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        overRepository.deleteAll();
    }

    @Override
    public List<Over> findAll(Sort sort) {
        return overRepository.findAll(sort);
    }

    @Override
    public Page<Over> findAll(Pageable pageable) {
        return overRepository.findAll(pageable);
    }

    @Override
    public <S extends Over> S insert(S entity) {
        return overRepository.insert(entity);
    }

    @Override
    public <S extends Over> List<S> insert(Iterable<S> entities) {
        return overRepository.insert(entities);
    }

    @Override
    public <S extends Over> Optional<S> findOne(Example<S> example) {
        return overRepository.findOne(example);
    }

    @Override
    public <S extends Over> List<S> findAll(Example<S> example) {
        return overRepository.findAll(example);
    }

    @Override
    public <S extends Over> List<S> findAll(Example<S> example, Sort sort) {
        return overRepository.findAll(example, sort);
    }

    @Override
    public <S extends Over> Page<S> findAll(Example<S> example, Pageable pageable) {
        return overRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Over> long count(Example<S> example) {
        return overRepository.count(example);
    }

    @Override
    public <S extends Over> boolean exists(Example<S> example) {
        return overRepository.exists(example);
    }

    @Override
    public <S extends Over, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return overRepository.findBy(example, queryFunction);
    }

    @Override
    public Over save(Over over) {
        return overRepository.save(over);
    }
}
