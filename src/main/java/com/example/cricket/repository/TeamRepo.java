package com.example.cricket.repository;

import com.example.cricket.Beans.Team;
import lombok.RequiredArgsConstructor;
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
public class TeamRepo implements TeamRepository {

    private final TeamRepository teamRepository;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Optional<Team> findById(String id) {
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> findByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public boolean existsById(String id) {
        return teamRepository.existsById(id);
    }

    @Override
    public <S extends Team> List<S> saveAll(Iterable<S> entities) {
        return teamRepository.saveAll(entities);

    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllById(Iterable<String> ids) {
        return teamRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return teamRepository.count();
    }

    @Override
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void delete(Team entity) {
        teamRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        teamRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends Team> entities) {
        teamRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        teamRepository.deleteAll();
    }

    @Override
    public List<Team> findAll(Sort sort) {
        return teamRepository.findAll(sort);
    }

    @Override
    public Page<Team> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public <S extends Team> S insert(S entity) {
        return teamRepository.insert(entity);
    }

    @Override
    public <S extends Team> List<S> insert(Iterable<S> entities) {
        return teamRepository.insert(entities);
    }

    @Override
    public <S extends Team> Optional<S> findOne(Example<S> example) {
        return teamRepository.findOne(example);
    }

    @Override
    public <S extends Team> List<S> findAll(Example<S> example) {
        return teamRepository.findAll(example);
    }

    @Override
    public <S extends Team> List<S> findAll(Example<S> example, Sort sort) {
        return teamRepository.findAll(example, sort);
    }

    @Override
    public <S extends Team> Page<S> findAll(Example<S> example, Pageable pageable) {
        return teamRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Team> long count(Example<S> example) {
        return teamRepository.count(example);
    }

    @Override
    public <S extends Team> boolean exists(Example<S> example) {
        return teamRepository.exists(example);
    }

    @Override
    public <S extends Team, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return teamRepository.findBy(example, queryFunction);
    }
}
