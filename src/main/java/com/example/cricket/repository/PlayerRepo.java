package com.example.cricket.repository;

import com.example.cricket.Beans.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class PlayerRepo implements PlayerRepository {

    private final PlayerRepository playerRepository;

    @Override
    public <S extends Player> S save(S entity) {
        return playerRepository.save(entity);
    }

    @Override
    public <S extends Player> List<S> saveAll(Iterable<S> entities) {
        return playerRepository.saveAll(entities);
    }

    @Override
    public Optional<Player> findById(String id) {
        return playerRepository.findById(id);
    }

    public Optional<Player> findByPname(String pname) {
        return playerRepository.findByPname(pname);
    }
//
//    @Override
//    public List<Player> findAllById(List<String> ids) {
//        return List.of();
//    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findAllById(Iterable<String> ids) {
        return playerRepository.findAllById(ids);
    }

    @Override
    public List<Player> findAll(Sort sort) {
        return playerRepository.findAll(sort);
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return playerRepository.count();
    }

    @Override
    public boolean existsById(String id) {
        return playerRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void delete(Player entity) {
        playerRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        playerRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends Player> entities) {
        playerRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        playerRepository.deleteAll();
    }

    @Override
    public <S extends Player> Optional<S> findOne(Example<S> example) {
        return playerRepository.findOne(example);
    }

    @Override
    public <S extends Player> S insert(S entity) {
        return null;
    }

    @Override
    public List<Player> findAllById(List<String> ids) {
        return List.of();
    }


    @Override
    public <S extends Player> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example) {
        return playerRepository.findAll(example);
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
        return playerRepository.findAll(example, sort);
    }

    @Override
    public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
        return playerRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Player> long count(Example<S> example) {
        return playerRepository.count(example);
    }

    @Override
    public <S extends Player> boolean exists(Example<S> example) {
        return playerRepository.exists(example);
    }

    @Override
    public <S extends Player, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return playerRepository.findBy(example, queryFunction);
    }
}
