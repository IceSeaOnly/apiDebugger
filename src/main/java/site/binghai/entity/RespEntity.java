package site.binghai.entity;

import site.binghai.utils.MD5;
import site.binghai.utils.TimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
@Entity
public class RespEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String hash;
    private String request;
    private String resp;
    private String addTime;

    public RespEntity(String name,String request, String resp) {
        this.name = name;
        this.hash = MD5.encryption(request);
        this.request = request;
        this.resp = resp;
        this.addTime = TimeFormatter.format(System.currentTimeMillis());
    }

    public RespEntity() {
    }

    public void dealRequest(){
        setRequest(getRequest().substring(getRequest().lastIndexOf("/"),getRequest().length()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
        this.hash = MD5.encryption(request);
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.addTime = TimeFormatter.format(System.currentTimeMillis());
        this.name = name;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
