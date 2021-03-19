package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Parent;
import com.health.SchoolHealth.model.entities.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface ParentDao extends CrudRepository<Parent, Long> {

    @Query("select p from Parent as p join StudentParent as sp on p.id=sp.parent.id where sp.student.id=:studentId " +
            "order by p.parentType.parentTypeCode desc")
    public Iterable<Parent> findAllParentsByStudentId(Long studentId);
}