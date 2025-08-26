package com.ruoyi.system.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;

/**
 * 人才行业分类对象 sys_talent_industry
 *
 * @author kenmi
 * @date 2025-08-21
 */
@Setter
@Getter
public class SysTalentIndustry extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 行业id
     */
    private Long id;

    /**
     * 行业名称
     */
    private String name;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 类型
     */
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
