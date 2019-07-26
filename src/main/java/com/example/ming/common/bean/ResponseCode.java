package com.example.ming.common.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    OK(1,"操作成功"),
    CODE(1001,"验证码成功"),
    CODEFAIL(1002,"验证码校验失败"),
    SIGN_IN_OK(2,"登录成功"),
    LOGOUT_OK(3,"注销登录成功"),
    SIGN_IN_INPUT_FAIL(-4,"账号或密码错误"),
    SIGN_IN_FAIL(-3,"登录失败"),
    FAIL(-1,"操作失败"),
    LOGOUT_FAIL(-2,"注销登录失败"),
    SING_IN_INPUT_EMPTY(-5,"账户和密码均不能为空"),
    NOT_SING_IN(-6,"用户未登录或身份异常"),

    INVALID_PARAMETER(10800001, "参数不合法"),
    ERROR_SYSTEM(10800002, "系统异常"), // 系统异常
    ERROR_HTTP(10800003, "REST接口异常"),
    ERROR_TSP(10800004, "第三方接口异常"),
    ERROR_PARSE_INVOKE_RESULT(10800005, "解析调用结果异常"),
    INVALID_DATA(10800006, "数据异常"),
    NULL_DATA(10800007, "空数据异常"),
    PARAMETER_NULL(10800008, "参数为NULL"),
    ERROR_IP_NOT_ALLOWED(108000010, "IP不允许访问此接口"),
    ERROR_PERMISSIONS_DENIED(108000011, "没有权限访问"),
    ERROR_DOWNLOAD_FILE_FAIL(108000012, "下载文件失败"),
    ERROR_REDIS_GET(108000013, "Redis获取失败"),
    ERROR_PARTNER_INFO_INVALID(108000014, "下载文件失败"),
    ERROR_VERIFY_SIGN_FAIL(108000015, "参数验签失败"),
    ERROR_TASK_REQUEST_REPEAT(108000016, "任务池消费失败，存在重复任务"),
    ERROR_TASK_NEED_RETRY(108000017, "服务暂不可用，需要重试"),
    ERROR_PORTAL_APPLY_FAIL(108000018, "平台请求失败"),
    ERROR_USER_EXISTED(108000019, "用户已经存在，请勿重复注册"),
    ERROR_USER_AUTH_FAIL(108000020, "用户名或密码不正确"),
    ERROR_VERIFICATION_CODE_OFTEN(108000021, "验证码请勿频繁发送，请稍后再试"),
    ERROR_VERIFICATION_CODE_INVALID(108000022, "无效验证码，验证失败或者过期"),
    ERROR_REAL_NAME_FAIL(108000023, "实名认证失败"),
    ERROR_REAL_NAME_REPEAT(108000024, "已经完成实名认证，请勿重复实名"),
    ERROR_MOBILE_INVALID(108000025, "无效操作，非本人手机号码"),
    ERROR_CMSKEY_REPEAT(108000026, "关键词重复"),
    ERROR_BANK_REPEAT(108000027, "银行名称重复"),
    ERROR_WORKNO_REPEAT(108000028, "工号已存在！"),
    ERROR_SK_EXPIRE(108000029, "此订单已被抢！"),
    ERROR_CANNOT_SK(108000030, "由于此单您未及时反馈被退回，无法再次抢单。"),
    ERROR_SKNUM_UPPERLIMIT(108000031, "对不起，您本周的抢单数据已达上限！"),
    ERROR_CUST_CANCEL(108000032, "此单客户已取消"),
    ERROR_DISTRICT_CANNOT_APPLY(108000033, "您所选择的区域暂时无法受理，请选择重新选择其它区域"),

    //数据中心异常code
    ERROR_ID_IS_NULL(106000001, "入参id不可为空，请检查入参"),
    ERROR_LOCK_IS_NULL(106000002, "入参state只能是0或者1，请检查入参"),
    ERROR_RULE_IS_NULL(106000003, "入参rule_name不可为空，请检查入参"),
    ERROR_GROUP_NAME_IS_NULL(106000004, "入参group_name不可为空，请检查入参"),
    ERROR_GROUP_TYPE(106000005, "入参group_type规则执行类型错误，请检查入参"),
    ERROR_GROUP_ID_IS_NULL(106000006, "入参groupId规则id不能为空，请检查入参"),
    ERROR_EXPRESSION_IS_NULL(106000006, "规则表达式不能为空，请检查入参"),
    ERROR_HIT_MESSAGE_IS_NULL(106000006, "命中信息不能为空，请检查入参"),


    ERROR_EXEC_RULE(102000001, "规则执行失败"),
    ERROR_RULE_GROUP_CODE_DUPLICATE(102000002, "规则组编码重复"),
    ERROR_RULE_CODE_DUPLICATE(102000003, "规则编码重复"),


    ERROR_FLOW_BRANCH(103000001, "流程分支执行失败"),

    ERROR_NAME_BLANK(104000001, "申请用户姓名为空"),
    ERROR_MOBIL_BLANK(104000002, "申请用户手机号为空"),
    ERROR_IDCARD_BLANK(104000003, "申请用户身份证为空"),

    ERROR_BATCH_GET_FILE(105000001, "批处理文件获取失败"),
    ERROR_BATCH_UPLOAD_FILE(105000002, "批处理文件结果上传失败")
    ;

    public Integer code;

    public String msg;

    public static List<ResponseMessage> getArrayMessage(){
        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        for (ResponseCode statusEnum : ResponseCode.values()) {
            responseMessages.add(new ResponseMessageBuilder()
                    .code(statusEnum.code)
                    .message(statusEnum.msg)
                    .build());
        }
        return responseMessages;
    }

}
