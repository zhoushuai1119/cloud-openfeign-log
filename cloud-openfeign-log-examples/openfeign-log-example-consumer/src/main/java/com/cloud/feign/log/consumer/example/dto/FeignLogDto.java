package com.cloud.feign.log.consumer.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/10/11 16:55
 * @version: v1
 */
@Data
@AllArgsConstructor
public class FeignLogDto implements Serializable {

    private static final long serialVersionUID = 6783838681413974784L;

    private String name;

    private Integer age;

}
