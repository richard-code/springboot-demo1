package com.study.model;

import lombok.*;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String userName;

    /**
     * 描述
     */
    private String description;
    private City city;

}
