package site.zhuanzhuan.enums;

/**
 * 枚举 - 角色
 */
public enum RoleEnum {

    ROLE_ADMIN(0, "管理员"),
    ROLE_CUSTOMER(1, "普通用户");

    private String value;
    private int code;

    RoleEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
