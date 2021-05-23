package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

//    @Query("select u from User as u join Parent as p on u.relatedId=p.id join StudentParent as sp on p.id=sp.parent.id \n" +
//            "join Student as st on sp.student.id=st.id where u.userType=3 and st.school.id=:schoolId and u.enable<>'Y'")
    @Query("select u from User as u join Parent as p on u.id=p.user.id")
    public Iterable<User> findAllInactiveParentsBySchool(Integer schoolId); //АКО СЪЩЕСТУВА ЕМАИЛ?

    //medicinsko lice
    //gp


    @Query("select u from User as u join Parent as p on u.id=p.user.id where p.id=:parentId")
    public Optional<User> findParentUser(Long parentId);

    @Query("select u from User as u where u.userCode=:userCode")
    public Optional<User> findUserByCode(String userCode);


    @Query("select u from User as u where u.password=:password")
    public Optional<User> findUserByPassword(String password);
}