package site.binghai.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.entity.RespEntity;
import site.binghai.service.RespEntityService;
import site.binghai.utils.HttpRequestUtils;
import site.binghai.utils.MD5;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static site.binghai.utils.SimpleLogger.log;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
@RestController
public class Api {
    @Autowired
    RespEntityService service;

    @RequestMapping("api")
    public Object api(HttpServletRequest request) {
        log("Full Url : " + HttpRequestUtils.getRequestFullPath(request));
        List<RespEntity> rs = service.findByHash(MD5.encryption(HttpRequestUtils.getRequestPath(request)));
        log("Answer : " + (!CollectionUtils.isEmpty(rs) ? rs.get(0).getResp() : "No suitable answer."));
        return !CollectionUtils.isEmpty(rs) ? rs.get(0).getResp() : "No suitable answer.";
    }

}
