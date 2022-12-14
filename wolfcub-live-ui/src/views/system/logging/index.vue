<template>
  <div class="app-container">
    <div class="body">
      <el-row>
        <el-col :span="24">
          <!-- 查询表单 -->
          <el-row>
            <el-form ref="searchForm" :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
              <el-form-item label="关键字">
                <el-input v-model="searchForm.keyword" placeholder="请输入用户名/昵称" />
              </el-form-item>
              <el-form-item label="日志级别">
                <el-select v-model="searchForm.level" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.content"
                    :label="item.content"
                    :value="item.content"
                  />
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- 表格 -->
          <el-row>
            <el-table v-loading="loading" stripe :data="tableData">
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="userId.username" label="用户名" :show-overflow-tooltip="true" />
              <el-table-column prop="level" label="日志等级" :show-overflow-tooltip="true" />
              <el-table-column prop="msg" label="日志内容" :show-overflow-tooltip="true" />
              <el-table-column label="选项" :show-overflow-tooltip="true">
                <template v-slot="scope">
                  <el-button @click="handleViewScope(scope)">查看</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
          <!-- 分页 -->
          <el-row>
            <el-pagination
              :current-page="searchForm.current"
              :page-sizes="[5, 10, 20, 50]"
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" width="30%">
      <el-form ref="editForm" :model="showForm" status-icon label-width="80px" class="demo-ruleForm">
        <el-form-item label="日志编号" prop="id">
          <el-input v-model="showForm.id" disabled autocomplete="off" />
        </el-form-item>
        <el-form-item label="日志等级" prop="level">
          <el-input :value="showForm.level" disabled />
        </el-form-item>
        <el-form-item label="日志信息" prop="msg">
          <el-input type="textarea" :value="showForm.msg" />
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listData } from '@/api/logging'

export default {
  name: 'Logging',
  filters: {
    genderFilter(gender) {
      if (gender === 1) {
        return '男'
      } else if (gender === 2) {
        return '女'
      }

      return '保密'
    }
  },
  data() {
    return {
      dialogTitle: '',
      dialogFormVisible: false,
      showForm: {
        id: undefined,
        level: undefined,
        msg: undefined,
        time: undefined
      },
      options: [{ content: 'info' }, { content: 'error' }, { content: 'warn' }, { content: 'debug' }, { content: 'tarce' }],
      rules: {
        'title': [{ required: true, message: '标题不能为空' }]
      },
      total: 0,
      tableData: [],
      searchForm: {
        keyword: '',
        status: undefined,
        level: null,
        current: 1,
        limit: 10
      }
    }
  },
  created() {
    // 获取用户数据
    this.fetchData(this.searchForm)
  },
  methods: {
    handleViewScope({ row }) {
      console.log(row)
      this.showForm = {
        id: row.id,
        msg: row.msg.trim(),
        level: row.level,
        time: row.time
      }
      this.dialogFormVisible = true
    },
    reset() {
      this.showForm = {
        gender: 0,
        status: '0'
      }
      this.resetForm('editForm')
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
    },
    handleSave() {
      // 1. 修改标题为 新增用户
      this.dialogTitle = '新增用户'
      // 2. 将表达的数据清空
      this.reset()
      // 3. 显示对话框
      this.dialogFormVisible = true
    },
    handleEdit(data) {
      // 点击编辑按钮触发
      // 1. 修改标题为 编辑用户
      this.dialogTitle = '编辑用户'
      // 2. 将表单的数据清空
      this.showForm = data
      // 3. 显示对话框
      this.dialogFormVisible = true
    }
  }
}
</script>

<!--
  三部分: 写样式, 在这里面可以写普通 css 样式, 也可以写 less/sass/scss 等样式, 但是需要 css 预处理插件处理
    1. scoped: 表示这里面写的样式只会影响到这个 vue 文件中的模板, 不会影响到其他模板, 如果不写的话就属于全局样式, 会对其他模块造成影响
    2. lang: 可以描述里面的内容是 css/less/sass/scss
-->
<style lang="css" scoped>
.line {
  text-align: center;
}

.el-pagination {
  text-align: right;
}
</style>
