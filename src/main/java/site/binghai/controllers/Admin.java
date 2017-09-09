package site.binghai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String index(ModelMap map){
        map.put("rs",service.listAll());
        return "index";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }

    @RequestMapping(value = "addRecord",method = RequestMethod.POST)
    public String addRecord(RespEntity entity){
        entity.dealRequest();
        service.addOne(entity);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(int id){
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String delete(String byName,String byParam,ModelMap map){
        List<RespEntity> rs = null;
        if(!StringUtils.isEmpty(byName)){
            rs = service.findByName(byName);
        }else{
            rs = service.findByHash(MD5.encryption(byParam));
        }
        map.put("rs",rs);
        return "index";
    }
}
