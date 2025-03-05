package com.example.cricket.repository;

import com.example.cricket.Beans.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class MatchRepo implements MatchRepository {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchRepo(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public <S extends Match> S insert(S entity) {
        return matchRepository.insert(entity);
    }

    @Override
    public <S extends Match> List<S> insert(Iterable<S> entities) {
        return matchRepository.insert(entities);
    }

    @Override
    public <S extends Match> Optional<S> findOne(Example<S> example) {
        return matchRepository.findOne(example);
    }

    @Override
    public <S extends Match> List<S> findAll(Example<S> example) {
        return matchRepository.findAll(example);
    }

    @Override
    public <S extends Match> List<S> findAll(Example<S> example, Sort sort) {
        return matchRepository.findAll(example, sort);
    }

    @Override
    public <S extends Match> Page<S> findAll(Example<S> example, Pageable pageable) {
        return matchRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Match> long count(Example<S> example) {
        return matchRepository.count(example);
    }

    @Override
    public <S extends Match> boolean exists(Example<S> example) {
        return matchRepository.exists(example);
    }

    @Override
    public <S extends Match, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return matchRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Match> S save(S entity) {
        return matchRepository.save(entity);
    }

    @Override
    public <S extends Match> List<S> saveAll(Iterable<S> entities) {
        return matchRepository.saveAll(entities);
    }

    @Override
    public Optional<Match> findById(String s) {
        return matchRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return matchRepository.existsById(s);
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> findAllById(Iterable<String> strings) {
        return matchRepository.findAllById(strings);
    }

    @Override
    public long count() {
        return matchRepository.count();
    }

    @Override
    public void deleteById(String s) {
        matchRepository.deleteById(s);
    }

    @Override
    public void delete(Match entity) {
        matchRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        matchRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends Match> entities) {
        matchRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        matchRepository.deleteAll();
    }

    @Override
    public List<Match> findAll(Sort sort) {
        return matchRepository.findAll(sort);
    }

    @Override
    public Page<Match> findAll(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }
}
