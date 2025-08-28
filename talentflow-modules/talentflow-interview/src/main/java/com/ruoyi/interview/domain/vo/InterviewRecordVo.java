package com.ruoyi.interview.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 面试记录主对象 sys_interview_record
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Data
public class InterviewRecordVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 面试记录ID
     */
    private Long id;

    /**
     * 候选人ID
     */
    @Excel(name = "候选人ID")
    private Long candidateId;

    /**
     * 岗位ID
     */
    @Excel(name = "岗位ID")
    private Long positionId;

    /**
     * 申请记录ID
     */
    @Excel(name = "申请记录ID")
    private Long applicationId;

    /**
     * 面试编号
     */
    @Excel(name = "面试编号")
    private String interviewCode;

    /**
     * 面试标题
     */
    @Excel(name = "面试标题")
    private String interviewTitle;

    /**
     * 计划开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /**
     * 计划结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledEndTime;

    /**
     * 实际开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndTime;

    /**
     * 面试方式：ONSITE现场,ONLINE线上,PHONE电话
     */
    @Excel(name = "面试方式：ONSITE现场,ONLINE线上,PHONE电话")
    private String interviewMode;

    /**
     * 面试地点
     */
    @Excel(name = "面试地点")
    private String location;

    /**
     * 在线会议链接
     */
    @Excel(name = "在线会议链接")
    private String onlineMeetingUrl;

    /**
     * 当前阶段
     */
    @Excel(name = "当前阶段")
    private String currentStage;

    /**
     * 整体状态：SCHEDULED已安排,IN_PROGRESS进行中,COMPLETED已完成,CANCELLED已取消
     */
    @Excel(name = "整体状态：SCHEDULED已安排,IN_PROGRESS进行中,COMPLETED已完成,CANCELLED已取消")
    private String overallStatus;

    /**
     * 整体结果：PASS通过,FAIL未通过,PENDING待定,CANCELLED取消
     */
    @Excel(name = "整体结果：PASS通过,FAIL未通过,PENDING待定,CANCELLED取消")
    private String overallResult;

    /**
     * 最终决策：HIRE录用,REJECT拒绝,HOLD保留
     */
    @Excel(name = "最终决策：HIRE录用,REJECT拒绝,HOLD保留")
    private String finalDecision;

    /**
     * 决策理由
     */
    @Excel(name = "决策理由")
    private String decisionReason;

    /**
     * 创建部门
     */
    @Excel(name = "创建部门")
    private Long createDept;

    /**
     * 面试节点详情信息
     */
    private List<SysInterviewNodeDetail> sysInterviewNodeDetailList;
}
