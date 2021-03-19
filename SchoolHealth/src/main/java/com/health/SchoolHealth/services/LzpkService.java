package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.LzpkDao;
import com.health.SchoolHealth.model.DAOs.SchoolMedicsDao;
import com.health.SchoolHealth.model.entities.Lzpk;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class LzpkService {

    private final LzpkDao lzpkDao;



    public LzpkService(LzpkDao lzpkDao) {
        this.lzpkDao = lzpkDao;
    }

    public Lzpk getLzpk(Long LzpkId) {
        return LzpkId == null ? null: lzpkDao.findById(LzpkId).orElse(null);
    }

    public Lzpk createOrUpdateLzpk(Lzpk lzpk) {
        return lzpkDao.save(lzpk);
    }


}
