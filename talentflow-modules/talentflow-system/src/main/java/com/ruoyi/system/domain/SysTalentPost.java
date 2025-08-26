package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;

/**
 * 人才职位分类对象 sys_talent_post
 *
 * @author kenmi
 * @date 2025-08-17
 */
@Setter
@Getter
public class SysTalentPost extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 职位id
     */
    @Excel(name = "职位id")
    private Long id;

    /**
     * 职位名称
     */
    @Excel(name = "职位名称")
    private String name;

    /**
     * 父节点id
     */
    @Excel(name = "父节点id")
    private Long parentId;

    /**
     * 类型
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("parentId", getParentId())
                .append("type", getType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
