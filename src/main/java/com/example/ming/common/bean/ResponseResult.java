package com.example.ming.common.bean;

import com.example.ming.common.exception.RequestException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "请求结果响应体")
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "响应状态回执码")
    private Integer status;

    @ApiModelProperty(value = "数据体")
    private T data;

    @ApiModelProperty(value = "响应回执消息")
    private String msg;

    @ApiModelProperty(value = "响应时间戳")
    private final long timestamps = System.currentTimeMillis();

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum) {
        return e(statusEnum,null);
    }

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum, T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }

    public synchronized static ResponseEntity<byte[]> bytesEntity(Object fo) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            if (fo instanceof XSSFWorkbook) {
                ((XSSFWorkbook) fo).write(outputStream);
            } else if (fo instanceof ByteArrayOutputStream) {
                ((ByteArrayOutputStream) fo).writeTo(outputStream);
            } else if (fo instanceof String) {
                outputStream.write(((String) fo).getBytes());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(outputStream.toByteArray(),
                    headers, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RequestException(ResponseCode.ERROR_SYSTEM);
        }
    }

    private static final long serialVersionUID = 8992436576262574064L;
}
