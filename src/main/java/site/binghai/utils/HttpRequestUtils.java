package site.binghai.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public class HttpRequestUtils {
    public static String getRequestParams(HttpServletRequest request){
        return request.getQueryString();
    }

    public static String getRequestURL(HttpServletRequest request){
        return request.getRequestURL().toString();
    }

    public static String getRequestFullPath(HttpServletRequest request){
        return request.getRequestURL()+"?" + request.getQueryString();
    }
}
