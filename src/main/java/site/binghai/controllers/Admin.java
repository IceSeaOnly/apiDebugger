package site.binghai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import site.binghai.entity.RespEntity;
import site.binghai.service.RespEntityService;
import site.binghai.utils.MD5;

import java.util.List;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
@Controller
public class Admin {
    @Autowired
    private RespEntityService service;

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.put("rs", sorter(service.listAll()));
        return "index";
    }

    private List<RespEntity> sorter(List<RespEntity> ls) {
        ls.forEach(v -> v.setRequest(v.getRequest().length() > 30 ? v.getRequest().substring(0, 30) : v.getRequest()));
        ls.forEach(v -> v.setResp(v.getResp().length() > 70 ? v.getResp().substring(0, 70) : v.getResp()));
        return ls;
    }

    @RequestMapping("toAdd")
    public String toAdd(ModelMap map) {
        map.put("defaultUrl", "/api?act=" + MD5.encryption(System.currentTimeMillis() + "hello"));
        return "add";
    }

    @RequestMapping(value = "addRecord", method = RequestMethod.POST)
    public String addRecord(RespEntity entity) {
        entity.dealRequest();
        service.addOne(entity);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(int id) {
        RespEntity entity = service.findById(id);
        if (entity != null && StringUtils.isEmpty(entity.getPassCode())) {
            service.delete(id);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String delete(String byName, String byParam, ModelMap map) {
        List<RespEntity> rs = null;
        if (!StringUtils.isEmpty(byName)) {
            rs = service.findByName(byName);
        } else {
            rs = service.findByHash(MD5.encryption(byParam));
        }
        map.put("rs", rs);
        return "index";
    }

    @RequestMapping("edit")
    public String edit(int id, ModelMap map) {
        map.put("val", service.findById(id));
        return "edit";
    }
}
