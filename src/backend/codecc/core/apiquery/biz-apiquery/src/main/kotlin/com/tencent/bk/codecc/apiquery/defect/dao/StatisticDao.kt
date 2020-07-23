package com.tencent.bk.codecc.apiquery.defect.dao

import com.mongodb.BasicDBObject
import com.tencent.bk.codecc.apiquery.defect.model.CCNStatisticModel
import com.tencent.bk.codecc.apiquery.defect.model.CLOCStatisticModel
import com.tencent.bk.codecc.apiquery.defect.model.CommonStatisticModel
import com.tencent.bk.codecc.apiquery.defect.model.DUPCStatisticModel
import com.tencent.bk.codecc.apiquery.defect.model.LintStatisticModel
import com.tencent.bk.codecc.apiquery.utils.PageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository

@Repository
class StatisticDao @Autowired constructor(
    private val defectMongoTemplate: MongoTemplate
) {

    /**
     * 通过任务id清单查询缺陷类告警统计信息
     */
    fun findCommonByTaskIdInAndToolName(
        taskIds: List<Long>,
        toolName: String?,
        filterFields: List<String>?,
        pageable: Pageable?
    ): List<CommonStatisticModel> {
        val fieldsObj = BasicDBObject()
        PageUtils.getFilterFields(filterFields, fieldsObj)
        val query = BasicQuery(BasicDBObject(), fieldsObj)
        val criteria = if (toolName.isNullOrBlank()) {
            Criteria.where("task_id").`in`(taskIds)
        } else {
            Criteria.where("task_id").`in`(taskIds).and("tool_name").`is`(toolName)
        }
        query.addCriteria(criteria)
        if (null != pageable) {
            query.with(pageable)
        }
        return defectMongoTemplate.find(query, CommonStatisticModel::class.java, "t_statistic")
    }

    /**
     * 通过任务id清单查询lint类告警统计信息
     */
    fun findLintByTaskIdInAndToolName(
        taskIds: List<Long>,
        toolName: String?,
        filterFields: List<String>?,
        pageable: Pageable?
    ): List<LintStatisticModel> {
        val fieldsObj = BasicDBObject()
        PageUtils.getFilterFields(filterFields, fieldsObj)
        val query = BasicQuery(BasicDBObject(), fieldsObj)
        val criteria = if (toolName.isNullOrBlank()) {
            Criteria.where("task_id").`in`(taskIds)
        } else {
            Criteria.where("task_id").`in`(taskIds).and("tool_name").`is`(toolName)
        }
        query.addCriteria(criteria)
        if (null != pageable) {
            query.with(pageable)
        }
        return defectMongoTemplate.find(query, LintStatisticModel::class.java, "t_lint_statistic")
    }


    /**
     * 通过任务id清单查询圈复杂度告警统计信息
     */
    fun findCCNByTaskIdInAndToolName(
        taskIds: List<Long>,
        filterFields: List<String>?,
        pageable: Pageable?
    ): List<CCNStatisticModel> {
        val fieldsObj = BasicDBObject()
        PageUtils.getFilterFields(filterFields, fieldsObj)
        val query = BasicQuery(BasicDBObject(), fieldsObj)
        query.addCriteria(Criteria.where("task_id").`in`(taskIds))
        if (null != pageable) {
            query.with(pageable)
        }
        return defectMongoTemplate.find(query, CCNStatisticModel::class.java, "t_ccn_statistic")
    }


    /**
     * 通过任务id清单查询重复率告警统计信息
     */
    fun findDUPCByTaskIdInAndToolName(
        taskIds: List<Long>,
        filterFields: List<String>?,
        pageable: Pageable?
    ): List<DUPCStatisticModel> {
        val fieldsObj = BasicDBObject()
        PageUtils.getFilterFields(filterFields, fieldsObj)
        val query = BasicQuery(BasicDBObject(), fieldsObj)
        query.addCriteria(Criteria.where("task_id").`in`(taskIds))
        if (null != pageable) {
            query.with(pageable)
        }
        return defectMongoTemplate.find(query, DUPCStatisticModel::class.java, "t_dupc_statistic")
    }

    /**
     * 通过任务id清单查询CLOC告警统计信息
     */
    fun findCLOCByTaskIdInAndToolName(
        taskIds: List<Long>,
        filterFields: List<String>?,
        pageable: Pageable?
    ): List<CLOCStatisticModel> {
        val fieldsObj = BasicDBObject()
        PageUtils.getFilterFields(filterFields, fieldsObj)
        val query = BasicQuery(BasicDBObject(), fieldsObj)
        query.addCriteria(Criteria.where("task_id").`in`(taskIds))
        if (null != pageable) {
            query.with(pageable)
        }
        return defectMongoTemplate.find(query, CLOCStatisticModel::class.java, "t_cloc_statistic")
    }



}