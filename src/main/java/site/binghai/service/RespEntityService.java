package site.binghai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.binghai.dao.RespEntityDao;
import site.binghai.entity.RespEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
@Service
public class RespEntityService {
    @Autowired
    RespEntityDao respEntityDao;


    public List<RespEntity> listAll() {
        List<RespEntity> rs = respEntityDao.findAll();
        rs.sort((a, b) -> b.getId() - a.getId());
        return rs;
    }

    @Transactional
    public void addOne(RespEntity entity) {
        RespEntity result = respEntityDao.findOne(entity.getId());
        if (result != null) {
            if (result.getPassCode().equals(entity.getPassCode())) {
                respEntityDao.delete(entity.getId());
                respEntityDao.save(entity);
            }
        } else {
            respEntityDao.save(entity);
        }
    }

    @Transactional
    public void delete(int id) {
        respEntityDao.delete(id);
    }

    public List<RespEntity> findByName(String byName) {
        return respEntityDao.findByName(byName);
    }

    public List<RespEntity> findByHash(String hash) {
        return respEntityDao.findByHash(hash);
    }

    public RespEntity findById(int id) {
        return respEntityDao.findOne(id);
    }
}
