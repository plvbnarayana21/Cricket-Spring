package com.example.cricket.repository;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TournamentRepo implements TournamentRepository {

    private final TournamentRepository tournamentRepository;

    @Override
    public <S extends Tournament> S save(S entity) {
        return tournamentRepository.save(entity);
    }

    @Override
    public <S extends Tournament> List<S> saveAll(Iterable<S> entities) {
        return tournamentRepository.saveAll(entities);
    }

    @Override
    public Optional<Tournament> findById(String s) {
        return tournamentRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return tournamentRepository.existsById(s);
    }

    @Override
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public List<Tournament> findAllById(Iterable<String> strings) {
        return tournamentRepository.findAllById(strings);
    }

    @Override
    public long count() {
        return tournamentRepository.count();
    }

    @Override
    public void deleteById(String s) {
        tournamentRepository.deleteById(s);
    }

    @Override
    public void delete(Tournament entity) {
        tournamentRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        tournamentRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends Tournament> entities) {
        tournamentRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        tournamentRepository.deleteAll();
    }

    @Override
    public List<Tournament> findAll(Sort sort) {
        return tournamentRepository.findAll(sort);
    }

    @Override
    public Page<Tournament> findAll(Pageable pageable) {
        return tournamentRepository.findAll(pageable);
    }

    @Override
    public <S extends Tournament> S insert(S entity) {
        return tournamentRepository.insert(entity);
    }

    @Override
    public <S extends Tournament> List<S> insert(Iterable<S> entities) {
        return tournamentRepository.insert(entities);
    }

    @Override
    public <S extends Tournament> Optional<S> findOne(Example<S> example) {
        return tournamentRepository.findOne(example);
    }

    @Override
    public <S extends Tournament> List<S> findAll(Example<S> example) {
        return tournamentRepository.findAll(example);
    }

    @Override
    public <S extends Tournament> List<S> findAll(Example<S> example, Sort sort) {
        return tournamentRepository.findAll(example, sort);
    }

    @Override
    public <S extends Tournament> Page<S> findAll(Example<S> example, Pageable pageable) {
        return tournamentRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Tournament> long count(Example<S> example) {
        return tournamentRepository.count(example);
    }

    @Override
    public <S extends Tournament> boolean exists(Example<S> example) {
        return tournamentRepository.exists(example);
    }


    @Override
    public <S extends Tournament, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return tournamentRepository.findBy(example, queryFunction);
    }

    @Override
    public Tournament findMatchesById(String id) {
        return null;
    }

}
