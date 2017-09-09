package site.binghai.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.utils.HttpRequestUtils;

import javax.servlet.http.HttpServletRequest;

import static site.binghai.utils.SimpleLogger.log;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
@RestController
public class Api {
    @RequestMapping("api")
    public String api(HttpServletRequest request){
        log("Full Url -> ",HttpRequestUtils.getRequestFullPath(request));
        return HttpRequestUtils.getRequestParams(request);
    }


}
