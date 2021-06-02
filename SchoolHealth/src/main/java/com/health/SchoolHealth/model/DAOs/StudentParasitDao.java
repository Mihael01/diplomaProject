package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.model.entities.StudentParasit;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface StudentParasitDao extends CrudRepository<StudentParasit, Long> {

    @Query("select sp from Student as st left join StudentParasit as sp on sp.student.id = st.id " +
            "join ParasitType as pt on pt.parasitTypeCode = sp.parasitType.parasitTypeCode where st.id = :studentId")
    public Iterable<StudentParasit>  findParasitesByStudentId(Long studentId);


    @Query("select sp from StudentParasit as sp where sp.parasitType.parasitTypeCode = :parasitTypeCode and sp.student.id = :studentId")
    public Iterable<StudentParasit>  findStudentParasitByParasiteCodeAndStudentId(String parasitTypeCode, Long studentId);

}