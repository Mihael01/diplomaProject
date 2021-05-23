package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Admin;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface AdminDao extends CrudRepository<Admin, Long> {

    @Query("select a from Admin as a join User as u on a.user.id=u.id")
    public Iterable<Admin> findAllAdminsWithUser();

    @Query("select a from Admin as a join User as u on a.user.id=u.id where a.adminTelephoneNumber=:adminTelephoneNumber")
    public Iterable<Admin> findAdminsWithUserByTelephoneNumber(String adminTelephoneNumber);
}