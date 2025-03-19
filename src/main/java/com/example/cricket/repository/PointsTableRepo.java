package com.example.cricket.repository;

import com.example.cricket.Beans.PointsTable;
import com.example.cricket.Beans.TeamPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class PointsTableRepo implements PointsTableRepository {

    private final PointsTableRepository pointsTableRepository;

    @Override
    public <S extends PointsTable> S insert(S entity) {
        return pointsTableRepository.insert(entity);
    }

    @Override
    public <S extends PointsTable> List<S> insert(Iterable<S> entities) {
        return pointsTableRepository.insert(entities);
    }

    @Override
    public <S extends PointsTable> Optional<S> findOne(Example<S> example) {
        return pointsTableRepository.findOne(example);
    }

    @Override
    public <S extends PointsTable> List<S> findAll(Example<S> example) {
        return pointsTableRepository.findAll(example);
    }

    @Override
    public <S extends PointsTable> List<S> findAll(Example<S> example, Sort sort) {
        return pointsTableRepository.findAll(example, sort);
    }

    @Override
    public <S extends PointsTable> Page<S> findAll(Example<S> example, Pageable pageable) {
        return pointsTableRepository.findAll(example, pageable);
    }

    @Override
    public <S extends PointsTable> long count(Example<S> example) {
        return pointsTableRepository.count(example);
    }

    @Override
    public <S extends PointsTable> boolean exists(Example<S> example) {
        return pointsTableRepository.exists(example);
    }

    @Override
    public <S extends PointsTable, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return pointsTableRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends PointsTable> S save(S entity) {
        return pointsTableRepository.save(entity);
    }

    @Override
    public <S extends PointsTable> List<S> saveAll(Iterable<S> entities) {
        return pointsTableRepository.saveAll(entities);
    }

    @Override
    public Optional<PointsTable> findById(String s) {
        return pointsTableRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return pointsTableRepository.existsById(s);
    }

    @Override
    public List<PointsTable> findAll() {
        return pointsTableRepository.findAll();
    }

    @Override
    public List<PointsTable> findAllById(Iterable<String> strings) {
        return pointsTableRepository.findAllById(strings);
    }

    @Override
    public long count() {
        return pointsTableRepository.count();
    }

    @Override
    public void deleteById(String s) {
        pointsTableRepository.deleteById(s);
    }

    @Override
    public void delete(PointsTable entity) {
        pointsTableRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        pointsTableRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends PointsTable> entities) {
        pointsTableRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        pointsTableRepository.deleteAll();
    }

    @Override
    public List<PointsTable> findAll(Sort sort) {
        return pointsTableRepository.findAll(sort);
    }

    @Override
    public Page<PointsTable> findAll(Pageable pageable) {
        return pointsTableRepository.findAll(pageable);
    }

    @Override
    public PointsTable findByTournamentId(String tournamentId) {
        return pointsTableRepository.findByTournamentId(tournamentId);
    }
}