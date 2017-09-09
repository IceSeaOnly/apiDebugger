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
        return respEntityDao.findAll();
    }

    @Transactional
    public void addOne(RespEntity entity) {
        List<RespEntity> rs = respEntityDao.findByHash(entity.getHash());
        if(!CollectionUtils.isEmpty(rs)){
            rs.stream().forEach(v -> respEntityDao.delete(v.getId()));
        }
        respEntityDao.save(entity);
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

    public Object findById(int id) {
        return respEntityDao.findOne(id);
    }
}
