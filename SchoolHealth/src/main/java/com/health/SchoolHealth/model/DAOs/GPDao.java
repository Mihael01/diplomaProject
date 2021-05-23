package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface GPDao extends CrudRepository<GP, Long> {

    @Query("select gp from GP as gp join Student as s on gp.id=s.gp.id where s.id=:studentId")
    public Optional<GP> findGPByStudentId(Long studentId);

    @Query("select gp from GP as gp join User as u on gp.user.id=u.id")
    public Iterable<GP> findAllGPsWithUser();

    @Query("select gp from GP as gp join User as u on gp.user.id=u.id where gp.gpTelephoneNumber=:gpTelephoneNumber")
    public Iterable<GP> findGPsWithUserByTelephoneNumber(String gpTelephoneNumber);
}