package com.example.cricket.repository;

import com.example.cricket.Beans.ScoreCard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ScoreCardRepo implements ScoreCardRepository {

    private final ScoreCardRepository scorecardRepository;

    @Override
    public <S extends ScoreCard> S save(S entity) {
        return scorecardRepository.save(entity);
    }

    @Override
    public <S extends ScoreCard> List<S> saveAll(Iterable<S> entities) {
        return scorecardRepository.saveAll(entities);
    }

    @Override
    public Optional<ScoreCard> findById(String id) {
        return scorecardRepository.findById(id);
    }

    @Override
    public List<ScoreCard> findAll() {
        return scorecardRepository.findAll();
    }

    @Override
    public List<ScoreCard> findAllById(Iterable<String> ids) {
        return scorecardRepository.findAllById(ids);
    }

    @Override
    public List<ScoreCard> findAll(Sort sort) {
        return scorecardRepository.findAll(sort);
    }

    @Override
    public Page<ScoreCard> findAll(Pageable pageable) {
        return scorecardRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return scorecardRepository.count();
    }

    @Override
    public boolean existsById(String id) {
        return scorecardRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        scorecardRepository.deleteById(id);
    }

    @Override
    public void delete(ScoreCard entity) {
        scorecardRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        scorecardRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends ScoreCard> entities) {
        scorecardRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        scorecardRepository.deleteAll();
    }

    @Override
    public <S extends ScoreCard> Optional<S> findOne(Example<S> example) {
        return scorecardRepository.findOne(example);
    }

    @Override
    public <S extends ScoreCard> S insert(S entity) {
        return scorecardRepository.insert(entity);
    }

    @Override
    public <S extends ScoreCard> List<S> insert(Iterable<S> entities) {
        return scorecardRepository.insert(entities);
    }

    @Override
    public <S extends ScoreCard> List<S> findAll(Example<S> example) {
        return scorecardRepository.findAll(example);
    }

    @Override
    public <S extends ScoreCard> List<S> findAll(Example<S> example, Sort sort) {
        return scorecardRepository.findAll(example, sort);
    }

    @Override
    public <S extends ScoreCard> Page<S> findAll(Example<S> example, Pageable pageable) {
        return scorecardRepository.findAll(example, pageable);
    }

    @Override
    public <S extends ScoreCard> long count(Example<S> example) {
        return scorecardRepository.count(example);
    }

    @Override
    public <S extends ScoreCard> boolean exists(Example<S> example) {
        return scorecardRepository.exists(example);
    }

    @Override
    public <S extends ScoreCard, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return scorecardRepository.findBy(example, queryFunction);
    }
}
