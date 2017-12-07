package com.ascba.rebate.bean;
/**
 * Created by 李平 on 2017/12/6 14:29
 * Describe: 商家权益
 */

public class MctRights {

    /**
     * seller_id : 35
     * active_status : 0
     * active_desc : 筹备中
     * active_title : 我是活动
     * active_img : 我是图片
     * active_time : 2017/10/30
     * seller_last_time : 0天
     * seller_status_text : 开通
     * seller_text : 未缴纳年费，查看可享受商家各种服务
     * seller_purchase_money : 0
     * seller_referee_money : 9025
     * seller_give_money : 1615200
     */

    private int seller_id;
    private int active_status;
    private String active_desc;
    private String active_title;
    private String active_img;
    private String active_time;
    private String seller_last_time;
    private String seller_status_text;
    private String seller_text;
    private String seller_purchase_money;
    private String seller_referee_money;
    private String seller_give_money;
    private String seller_url;

    public String getSeller_url() {
        return seller_url;
    }

    public void setSeller_url(String seller_url) {
        this.seller_url = seller_url;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getActive_status() {
        return active_status;
    }

    public void setActive_status(int active_status) {
        this.active_status = active_status;
    }

    public String getActive_desc() {
        return active_desc;
    }

    public void setActive_desc(String active_desc) {
        this.active_desc = active_desc;
    }

    public String getActive_title() {
        return active_title;
    }

    public void setActive_title(String active_title) {
        this.active_title = active_title;
    }

    public String getActive_img() {
        return active_img;
    }

    public void setActive_img(String active_img) {
        this.active_img = active_img;
    }

    public String getActive_time() {
        return active_time;
    }

    public void setActive_time(String active_time) {
        this.active_time = active_time;
    }

    public String getSeller_last_time() {
        return seller_last_time;
    }

    public void setSeller_last_time(String seller_last_time) {
        this.seller_last_time = seller_last_time;
    }

    public String getSeller_status_text() {
        return seller_status_text;
    }

    public void setSeller_status_text(String seller_status_text) {
        this.seller_status_text = seller_status_text;
    }

    public String getSeller_text() {
        return seller_text;
    }

    public void setSeller_text(String seller_text) {
        this.seller_text = seller_text;
    }

    public String getSeller_purchase_money() {
        return seller_purchase_money;
    }

    public void setSeller_purchase_money(String seller_purchase_money) {
        this.seller_purchase_money = seller_purchase_money;
    }

    public String getSeller_referee_money() {
        return seller_referee_money;
    }

    public void setSeller_referee_money(String seller_referee_money) {
        this.seller_referee_money = seller_referee_money;
    }

    public String getSeller_give_money() {
        return seller_give_money;
    }

    public void setSeller_give_money(String seller_give_money) {
        this.seller_give_money = seller_give_money;
    }
}