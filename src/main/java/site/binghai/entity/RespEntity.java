package site.binghai.entity;

import site.binghai.utils.MD5;

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
    private String hash;
    private String request;
    private String resp;

    public RespEntity(String request, String resp) {
        this.hash = MD5.encryption(request);
        this.request = request;
        this.resp = resp;
    }

    public RespEntity() {
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
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
