package com.tencent.devops.store.pojo.image.response

import com.tencent.devops.store.pojo.common.StoreUserCommentInfo
import com.tencent.devops.store.pojo.image.enums.ImageAgentTypeEnum
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @Description
 * @Date 2019/9/17
 * @Version 1.0
 */
@ApiModel("镜像详情")
data class ImageDetail(

    @ApiModelProperty("镜像ID", required = true)
    val imageId: String,

    @ApiModelProperty("镜像ID（兼容多种解析方式）", required = true)
    val id: String,

    @ApiModelProperty("镜像代码", required = true)
    val imageCode: String,

    @ApiModelProperty("镜像代码（兼容多种解析方式）", required = true)
    val code: String,

    @ApiModelProperty("镜像名称", required = true)
    val imageName: String,

    @ApiModelProperty("镜像名称（兼容多种解析方式）", required = true)
    val name: String,

    @ApiModelProperty("研发来源")
    val rdType: String,

    @ApiModelProperty("镜像适用的Agent类型")
    var agentTypeScope: List<ImageAgentTypeEnum>,

    @ApiModelProperty("镜像logo", required = true)
    val logoUrl: String,

    @ApiModelProperty("镜像图标", required = true)
    val icon: String,

    @ApiModelProperty("镜像简介", required = true)
    val summary: String,

    @ApiModelProperty("镜像说明文档链接", required = false)
    val docsLink: String?,

    @ApiModelProperty("镜像调试项目Code", required = true)
    val projectCode: String,

    @ApiModelProperty("星级评分", required = true)
    val score: Double,

    @ApiModelProperty("下载量", required = true)
    val downloads: Int,

    @ApiModelProperty("所属镜像分类代码", required = true)
    val classifyCode: String,

    @ApiModelProperty("所属镜像分类名称", required = true)
    val classifyName: String,

    @ApiModelProperty("镜像来源 BKDEVOPS:蓝盾，THIRD:第三方", required = true)
    val imageSourceType: String,

    @ApiModelProperty("镜像仓库Url", required = true)
    val imageRepoUrl: String,

    @ApiModelProperty("镜像仓库名称", required = true)
    val imageRepoName: String,

    @ApiModelProperty("凭证Id", required = true)
    val ticketId: String,

    @ApiModelProperty("镜像tag", required = true)
    val imageTag: String,

    @ApiModelProperty("镜像大小（MB字符串）", required = true)
    val imageSize: String,

    @ApiModelProperty("镜像大小数值（字节）", required = true)
    val imageSizeNum: Long,

    @ApiModelProperty(
        "镜像状态，INIT：初始化|AUDITING：审核中|AUDIT_REJECT：审核驳回|RELEASED：已发布|GROUNDING_SUSPENSION：上架中止|UNDERCARRIAGED：已下架",
        required = true
    )
    val imageStatus: String,

    @ApiModelProperty("镜像描述", required = true)
    val description: String,

    @ApiModelProperty("Label数组", required = true)
    val labelList: List<Label>,

    @ApiModelProperty("需要指定Agent类型的范畴code")
    var needAgentTypeCategorys: List<String>,

    @ApiModelProperty("范畴code", required = true)
    val category: String,

    @ApiModelProperty("范畴名称", required = true)
    val categoryName: String,

    @ApiModelProperty("是否为最新版本镜像 true：最新 false：非最新", required = true)
    val latestFlag: Boolean,

    @ApiModelProperty("发布者", required = true)
    val publisher: String,

    @ApiModelProperty("发布时间", required = false)
    val pubTime: Long? = null,

    @ApiModelProperty("是否为公共镜像 true：是 false：否", required = true)
    val publicFlag: Boolean,

    @ApiModelProperty("是否可安装 true：可以 false：不可以", required = true)
    val flag: Boolean,

    @ApiModelProperty("是否有处于上架状态的版本 true：可以 false：不可以", required = true)
    val releaseFlag: Boolean,

    @ApiModelProperty("是否推荐 true：推荐 false：不推荐", required = true)
    val recommendFlag: Boolean,

    @ApiModelProperty("是否官方认证 true：是 false：否", required = true)
    val certificationFlag: Boolean,

    @ApiModelProperty("CommentInfo数组", required = true)
    val userCommentInfo: StoreUserCommentInfo,

    @ApiModelProperty("版本号", required = true)
    val version: String,

    @ApiModelProperty(
        "发布类型，NEW：新上架 INCOMPATIBILITY_UPGRADE：非兼容性升级 COMPATIBILITY_UPGRADE：兼容性功能更新 COMPATIBILITY_FIX：兼容性问题修正",
        required = true
    )
    val releaseType: String,

    @ApiModelProperty("版本日志内容", required = true)
    val versionContent: String,

    @ApiModelProperty("创建人", required = true)
    val creator: String?,

    @ApiModelProperty("修改人", required = true)
    val modifier: String?,

    @ApiModelProperty("创建时间", required = true)
    val createTime: Long,

    @ApiModelProperty("修改时间", required = true)
    val updateTime: Long,

    @ApiModelProperty("是否已安装", required = true)
    var isInstalled: Boolean? = null
)