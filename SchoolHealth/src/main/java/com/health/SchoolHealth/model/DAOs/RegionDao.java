package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Region;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Primary
@Transactional
public interface RegionDao extends CrudRepository<Region, String> {

    @Query("select r from Region as r order by r.regionName")
    List<Region> findAllRegionsOrderedByName();

}