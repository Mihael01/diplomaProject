package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.LzpkDao;
import com.health.SchoolHealth.model.entities.Lzpk;
import org.springframework.stereotype.Service;

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
