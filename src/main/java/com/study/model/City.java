package com.study.model;

import lombok.*;

/**
 * 城市实体
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private String provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}