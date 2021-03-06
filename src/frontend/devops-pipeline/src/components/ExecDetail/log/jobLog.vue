<template>
    <section class="job-log">
        <bk-log-search :down-load-link="downLoadLink" :execute-count="executeCount" class="log-tools"></bk-log-search>
        <bk-multiple-log ref="multipleLog"
            class="bk-log"
            :log-list="pluginList"
            @open-log="openLog"
            @tag-change="tagChange"
        >
            <template slot-scope="log">
                <status-icon :status="log.data.status" class="multiple-log-status"></status-icon>
                {{ log.data.name }}
            </template>
        </bk-multiple-log>
    </section>
</template>

<script>
    import { mapActions } from 'vuex'
    import statusIcon from '../status'
    import { hashID } from '@/utils/util.js'

    export default {
        components: {
            statusIcon
        },

        props: {
            buildId: {
                type: String
            },
            pluginList: {
                type: Array
            },
            downLoadLink: {
                type: String
            }
        },

        data () {
            return {
                executeCount: 1,
                logPostData: {},
                closeIds: []
            }
        },

        beforeDestroy () {
            this.closeLog()
        },

        methods: {
            ...mapActions('atom', [
                'getInitLog',
                'getAfterLog'
            ]),

            tagChange (tag, id) {
                const ref = this.$refs.multipleLog
                const postData = this.logPostData[id]
                clearTimeout(postData.timeId)
                this.closeIds.push(postData.hashId)
                ref.changeExecute(id)
                postData.lineNo = 0
                postData.subTag = tag
                this.getLog(id, postData)
            },

            closeLog () {
                Object.keys(this.logPostData).forEach(key => {
                    const postData = this.logPostData[key]
                    this.closeIds.push(postData.hashId)
                    clearTimeout(postData.timeId)
                })
            },

            openLog (plugin) {
                const id = plugin.id
                let postData = this.logPostData[id]
                if (!postData) {
                    postData = this.logPostData[id] = {
                        projectId: this.$route.params.projectId,
                        pipelineId: this.$route.params.pipelineId,
                        buildId: this.buildId,
                        tag: id,
                        currentExe: plugin.executeCount,
                        lineNo: 0
                    }

                    this.$nextTick(() => {
                        this.getLog(id, postData)
                    })
                }
            },

            getLog (id, postData) {
                const hashId = postData.hashId = hashID()
                let logMethod = this.getAfterLog
                if (postData.lineNo <= 0) logMethod = this.getInitLog
                const ref = this.$refs.multipleLog

                logMethod(postData).then((res) => {
                    if (this.closeIds.includes(hashId)) return

                    res = res.data || {}
                    if (res.status !== 0) {
                        let errMessage
                        switch (res.status) {
                            case 1:
                                errMessage = this.$t('history.logEmpty')
                                break
                            case 2:
                                errMessage = this.$t('history.logClear')
                                break
                            case 3:
                                errMessage = this.$t('history.logClose')
                                break
                            default:
                                errMessage = this.$t('history.logErr')
                                break
                        }
                        ref.handleApiErr(errMessage, id)
                        return
                    }

                    const subTags = res.subTags
                    if (subTags && subTags.length > 0) {
                        const tags = subTags.map((tag) => ({ label: tag, value: tag }))
                        tags.unshift({ label: 'All', value: '' })
                        ref.setSubTag(tags, id)
                    }

                    const logs = res.logs || []
                    const lastLog = logs[logs.length - 1] || {}
                    const lastLogNo = lastLog.lineNo || postData.lineNo - 1 || -1
                    postData.lineNo = +lastLogNo + 1

                    if (res.finished) {
                        if (res.hasMore) {
                            ref.addLogData(logs, id)
                            postData.timeId = setTimeout(() => this.getLog(id, postData), 100)
                        } else {
                            ref.addLogData(logs, id)
                        }
                    } else {
                        ref.addLogData(logs, id)
                        postData.timeId = setTimeout(() => this.getLog(id, postData), 1000)
                    }
                }).catch((err) => {
                    this.$bkMessage({ theme: 'error', message: err.message || err })
                    if (ref) ref.handleApiErr(err.message, id)
                })
            }
        }
    }
</script>

<style lang="scss" scoped>
    .job-log {
        height: calc(100% - 59px);
    }

    .log-tools {
        position: absolute;
        right: 20px;
        top: 13px;
        display: flex;
        align-items: center;
        line-height: 30px;
        user-select: none;
        background: none;
    }

    .multiple-log-status {
        width: 14px;
        height: 15px;
        margin: 0 9px;
        padding: 1px 0;
        /deep/ svg {
            width: 14px;
            height: 14px;
        }
    }
</style>
