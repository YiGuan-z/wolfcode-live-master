<template>
  <div class="app-container">
    <div class="body">
      <el-row>
        <el-col :span="24">
          <!-- 查询表单 -->
          <el-row>
            <el-form ref="searchForm" :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
              <el-form-item label="关键字">
                <el-input v-model="searchForm.keyword" placeholder="请输入内容关键字" />
              </el-form-item>
              <el-form-item label="状态">
                <el-select v-model="searchForm.status" clearable>
                  <el-option
                    v-for="item in statusList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
              </el-form-item>
              <el-form-item>
                <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- 表格 -->
          <el-row>
            <el-table v-loading="loading" stripe :data="tableData">
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="video.title" label="关联视频">
                <template v-slot="scope">
                  <span class="link-type">{{ scope.row.video.title }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="author.nickname" label="发布人">
                <template v-slot="scope">
                  <span class="link-type">{{ scope.row.author.nickname }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="videoTime" label="视频时间">
                <template v-slot="scope">
                  {{ scope.row.videoTime | secondsFormat }}
                </template>
              </el-table-column>
              <el-table-column prop="content" label="弹幕内容" :show-overflow-tooltip="true" />
              <el-table-column prop="position" label="弹幕位置">
                <template v-slot="scope">
                  {{ scope.row.position | positionFilter }}
                </template>
              </el-table-column>
              <el-table-column prop="color" label="弹幕颜色">
                <template v-slot="scope">
                  <el-color-picker v-model="scope.row.color" size="mini" />
                </template>
              </el-table-column>
              <el-table-column prop="fontSize" label="字体大小" />
              <el-table-column prop="likes" label="点赞数" />
              <el-table-column prop="status" label="状态">
                <template v-slot="scope">
                  <el-switch
                    v-model="scope.row.status"
                    active-value="0"
                    inactive-value="1"
                    @change="handleStatusChange(scope.row)"
                  />
                </template>
              </el-table-column>
              <el-table-column prop="releaseTime" label="发布时间" />
            </el-table>
          </el-row>
          <!-- 分页 -->
          <el-row>
            <el-pagination
              :current-page="searchForm.current"
              :page-sizes="[3, 5, 10, 20]"
              :page-size="searchForm.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </el-row>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { changeStatus, listData } from '@/api/bulletMsg'

export default {
  name: 'User',
  filters: {
    secondsFormat(seconds) {
      if (!seconds) {
        return '00:00:00'
      }

      let h = parseInt(seconds / 60 / 60 % 24)
      h = h < 10 ? '0' + h : h
      let m = parseInt(seconds / 60 % 60)
      m = m < 10 ? '0' + m : m
      let s = parseInt(seconds % 60)
      s = s < 10 ? '0' + s : s
      return `${h}:${m}:${s}`
    },
    positionFilter(position) {
      if (position === 0) {
        return '滚动弹幕'
      } else if (position === 1) {
        return '顶部'
      } else {
        return '底部'
      }
    }
  },
  data() {
    return {
      statusList: [{ value: 0, label: '正常' }, { value: 1, label: '禁用' }],
      total: 0,
      tableData: [],
      loading: true,
      searchForm: {
        keyword: '',
        status: undefined,
        current: 1,
        limit: 3
      }
    }
  },
  created() {
    // 获取用户数据
    this.fetchData(this.searchForm)
  },
  methods: {
    handleStatusChange(row) {
      const text = row.status === '0' ? '启用' : '禁用'
      this.$modal.confirm(`确认要"${text}"这条弹幕吗？`).then(function() {
        return changeStatus(row.id, row.status)
      }).then(() => {
        this.$modal.msgSuccess(`${text}成功`)
      }).catch(function() {
        row.status = row.status === '0' ? '1' : '0'
      })
    },
    fetchData(params) {
      this.loading = true
      listData(params).then(res => {
        const { data } = res
        this.searchForm.current = data.current
        this.searchForm.limit = data.limit
        this.total = data.total
        this.tableData = data.list
        this.loading = false
      })
    },
    handleCurrentChange(current) {
      this.searchForm.current = current
      // 当每页显示数发生变动时触发
      this.fetchData(this.searchForm)
    },
    handleSizeChange(size) {
      this.searchForm.limit = size
      // 当前页发生变动时触发
      this.fetchData(this.searchForm)
    },
    handleQuery() {
      // 提交查询表单
      this.searchForm.current = 1
      this.fetchData(this.searchForm)
    },
    resetQuery() {
      this.resetForm('searchForm')
      this.handleQuery()
    }
  }
}
</script>

<style lang="css" scoped>
  .line {
    text-align: center;
  }

  .el-pagination {
    text-align: right;
  }
</style>
