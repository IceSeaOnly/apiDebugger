package site.binghai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.entity.RespEntity;

import java.util.List;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public interface RespEntityDao extends JpaRepository<RespEntity,Integer> {
    List<RespEntity> findByHash(String hash);
    List<RespEntity> findByName(String name);
}
