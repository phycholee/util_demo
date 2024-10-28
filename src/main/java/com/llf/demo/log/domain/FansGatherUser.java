package com.llf.demo.log.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FansGatherUser {

    private Long auid;

    private Long uid;

    private Long gatherId;

    private String time;

}
