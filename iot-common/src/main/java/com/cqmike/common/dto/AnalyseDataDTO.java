package com.cqmike.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: iot
 * @ClassName: AnalyseDataDTO
 * @Description: 数据传输对象
 * @Author: chen qi
 * @Date: 2020/3/10 20:57
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseDataDTO {

    private String deviceSn;
    private String productId;

    private String data;
}
