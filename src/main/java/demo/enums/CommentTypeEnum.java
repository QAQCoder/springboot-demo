package demo.enums;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/7 15:17
 *
 * 回复所属的类型：1-回复的是问题，2-回复的是另一回复
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer typeId) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.type == typeId) return true;
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
