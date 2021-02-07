package com.health.SchoolHealth.model.DAOs.impl;

import com.health.SchoolHealth.model.DAOs.AddictionsDao;
import com.health.SchoolHealth.model.entities.Addictions;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class AddictionsDaoImpl { //implements AddictionsDao {
//
//    AddictionsDao repo;
//
//    public AddictionsDaoImpl(ApplicationContext context) {
//        this.repo = context.getBean(AddictionsDao.class);
//    }
//
//    @Override
//    public <S extends Addictions> S save(S addictions) {
//        return  repo.save(addictions);
//    }
//
//    @Override
//    public <S extends Addictions> Iterable<S> saveAll(Iterable<S> iterable) {
//        return repo.saveAll(iterable);
//    }
//
//    @Override
//    public Optional<Addictions> findById(Long id) {
//        return repo.findById(id);
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        return repo.findById(id).isPresent();
//    }
//
//    @Override
//    public Iterable<Addictions> findAll() {
//        return repo.findAll();
//    }
//
//    @Override
//    public Iterable<Addictions> findAllById(Iterable<Long> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Addictions addictions) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Addictions> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
}
