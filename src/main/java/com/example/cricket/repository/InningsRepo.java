package com.example.cricket.repository;

import com.example.cricket.Beans.Innings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InningsRepo implements InningsRepository {

    private final InningsRepository inningsRepository;

    @Override
    public List<Innings> findByMatchId(String matchId) {
        return inningsRepository.findByMatchId(matchId);
    }

    public Optional<Innings> findByMatchIdAndINo(String matchId, int iNo) {
        List<Innings> inningsList = findByMatchId(matchId);

        return inningsList.stream()
                .filter(inning -> inning.getINo() == iNo)
                .findFirst();
    }


    @Override
    public <S extends Innings> S insert(S entity) {
        return inningsRepository.insert(entity);
    }

    @Override
    public <S extends Innings> List<S> insert(Iterable<S> entities) {
        return inningsRepository.insert(entities);
    }

    @Override
    public <S extends Innings> Optional<S> findOne(Example<S> example) {
        return inningsRepository.findOne(example);
    }

    @Override
    public <S extends Innings> List<S> findAll(Example<S> example) {
        return inningsRepository.findAll(example);
    }

    @Override
    public <S extends Innings> List<S> findAll(Example<S> example, Sort sort) {
        return inningsRepository.findAll(example, sort);
    }

    @Override
    public <S extends Innings> Page<S> findAll(Example<S> example, Pageable pageable) {
        return inningsRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Innings> long count(Example<S> example) {
        return inningsRepository.count(example);
    }

    @Override
    public <S extends Innings> boolean exists(Example<S> example) {
        return inningsRepository.exists(example);
    }

    @Override
    public <S extends Innings, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return inningsRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Innings> S save(S entity) {
        return inningsRepository.save(entity);
    }

    @Override
    public <S extends Innings> List<S> saveAll(Iterable<S> entities) {
        return inningsRepository.saveAll(entities);
    }

    @Override
    public Optional<Innings> findById(String s) {
        return inningsRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return inningsRepository.existsById(s);
    }

    @Override
    public List<Innings> findAll() {
        return inningsRepository.findAll();
    }

    @Override
    public List<Innings> findAllById(Iterable<String> strings) {
        return inningsRepository.findAllById(strings);
    }

    @Override
    public long count() {
        return inningsRepository.count();
    }

    @Override
    public void deleteById(String s) {
        inningsRepository.deleteById(s);
    }

    @Override
    public void delete(Innings entity) {
        inningsRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        inningsRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends Innings> entities) {
        inningsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        inningsRepository.deleteAll();
    }

    @Override
    public List<Innings> findAll(Sort sort) {
        return inningsRepository.findAll(sort);
    }

    @Override
    public Page<Innings> findAll(Pageable pageable) {
        return inningsRepository.findAll(pageable);
    }
}
