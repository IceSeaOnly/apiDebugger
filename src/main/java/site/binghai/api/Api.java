package site.binghai.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin(origins = "*")
    public Object api(HttpServletRequest request) {
        String url = HttpRequestUtils.getRequestFullPath(request);
        log("Full Url : " + url);
        url = url.substring(url.lastIndexOf("/"), url.length());
        String callback = null;
        if (url.contains("&callback=jQuery")) {
            callback = HttpRequestUtils.getParams(request).get("callback");
            url = url.substring(0, url.indexOf("&callback=jQuery"));
            log("jQuery timeStamp found,cut out:" + url);
        }
        List<RespEntity> rs = service.findByHash(MD5.encryption(url));
        log("Answer for " + url + ": " + (!CollectionUtils.isEmpty(rs) ? rs.get(0).getResp() : "No suitable answer."));
        if (CollectionUtils.isEmpty(rs)) {
            return callback == null ? rs.get(0).getResp() : String.format("%s(%s)", callback, rs.get(0).getResp());
        } else {
            return "No suitable answer.";
        }
    }

}
