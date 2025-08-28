package com.ruoyi.interview.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 面试节点详情对象 sys_interview_node_detail
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Data
public class InterviewNodeDetailVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 节点详情ID
     */
    private Long id;

    /**
     * 面试记录ID
     */
    @Excel(name = "面试记录ID")
    private Long interviewId;

    /**
     * 节点顺序
     */
    @Excel(name = "节点顺序")
    private Long nodeOrder;

    /**
     * 节点名称
     */
    @Excel(name = "节点名称")
    private String nodeName;

    /**
     * 节点类型：PHONE电话沟通,TECHNICAL技术面试,HR人事面试,MANAGER管理面试,FINAL终面
     */
    @Excel(name = "节点类型：PHONE电话沟通,TECHNICAL技术面试,HR人事面试,MANAGER管理面试,FINAL终面")
    private String nodeType;

    /**
     * 节点描述
     */
    @Excel(name = "节点描述")
    private String nodeDescription;

    /**
     * 面试官ID
     */
    @Excel(name = "面试官ID")
    private Long interviewerId;

    /**
     * 面试官姓名
     */
    @Excel(name = "面试官姓名")
    private String interviewerName;

    /**
     * 计划时长（分钟）
     */
    @Excel(name = "计划时长", readConverterExp = "分=钟")
    private Long scheduledDuration;

    /**
     * 实际时长（分钟）
     */
    @Excel(name = "实际时长", readConverterExp = "分=钟")
    private Long actualDuration;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 节点状态：PENDING待进行,IN_PROGRESS进行中,COMPLETED已完成,CANCELLED已取消
     */
    @Excel(name = "节点状态：PENDING待进行,IN_PROGRESS进行中,COMPLETED已完成,CANCELLED已取消")
    private String nodeStatus;

    /**
     * 节点结果：PASS通过,FAIL未通过,PENDING待定
     */
    @Excel(name = "节点结果：PASS通过,FAIL未通过,PENDING待定")
    private String nodeResult;

    /**
     * 综合评分(1-5)
     */
    @Excel(name = "综合评分(1-5)")
    private BigDecimal overallScore;

    /**
     * 面试反馈和评价
     */
    @Excel(name = "面试反馈和评价")
    private String feedback;

    /**
     * 优点总结
     */
    @Excel(name = "优点总结")
    private String strengths;

    /**
     * 不足之处
     */
    @Excel(name = "不足之处")
    private String weaknesses;

    /**
     * 推荐结果：HIRE推荐,NO_HIRE不推荐,PENDING待定
     */
    @Excel(name = "推荐结果：HIRE推荐,NO_HIRE不推荐,PENDING待定")
    private String recommendation;

    /**
     * 反馈提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackSubmittedTime;

    /**
     * 创建部门
     */
    @Excel(name = "创建部门")
    private Long createDept;
}
