package com.easy.common.util.http;

import lombok.Data;

import java.io.Serializable;

/**
 * ip属性
 * </p>
 *
 * @author Matt
 */
@Data
public class IpLocation implements Serializable {

    /**
     * IP
     */
    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 服务
     */
    private String isp;


}