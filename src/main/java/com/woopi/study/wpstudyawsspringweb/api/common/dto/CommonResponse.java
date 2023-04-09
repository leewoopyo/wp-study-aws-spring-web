package com.woopi.study.wpstudyawsspringweb.api.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woopi.study.wpstudyawsspringweb.enumeration.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Getter
public class CommonResponse<T> {

    // 0000 : 이면 성공
    // 그 외 실패.
    @ApiModelProperty(name = "code", example = "0000")
    @ApiParam(value = "상태코드", required = true)
    private String code;

    @ApiModelProperty(name = "message", example = "정상처리되었습니다.")
    @ApiParam(value = "메시지", required = true)
    private String message;

    /* 사용자에게 알림을 해야할 경우 true */
    @ApiModelProperty(name = "alertFlag", example = "false")
    @ApiParam(value = "alert여부")
    private boolean alertFlag;

    @ApiModelProperty(name = "data", example = "Object")
    @ApiParam(value = "dataObject", required = true)
    private T data;

    @JsonIgnore
    private StatusCode statusCode;

    @Builder
    public CommonResponse(StatusCode statusCode, String code, String message, boolean alertFlag, T data) {
        this.statusCode = !ObjectUtils.isEmpty(statusCode) ? statusCode : StatusCode.SUCCESS;
        this.code = !StringUtils.isEmpty(code) ? code : this.statusCode.getCode();
        this.message = !StringUtils.isEmpty(message) ? message : this.statusCode.getMessage();
        this.alertFlag = alertFlag;
        this.data = data;
    }
}
