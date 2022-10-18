<!--
  一部分: 当前页面/组件的模板部分, 在这里面编写页面需要的模板数据
    1. 一定是使用 <template> 标签作为根标签
    2. 在 <template> 中有且仅有一个根标签
 -->
<template>
  <div class="app-container">
    <div class="body">
      <el-row>
        <el-col :span="24">
          <!-- 查询表单 -->
          <el-row>
            <el-form :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
              <el-form-item label="关键字">
                <el-input v-model="searchForm.keyword" placeholder="请输入被举报的用户名/姓名" />
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
                <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- 表格 -->
          <el-row>
            <el-table stripe :data="tableData">
              <el-table-column prop="id" label="ID" />
              <el-table-column prop="userId.nickname" label="被举报的用户" />
              <el-table-column prop="reportDay" label="举报时间" />
              <el-table-column prop="reportContent" label="举报内容" />
              <el-table-column prop="status" label="选项">
                <template v-slot="scope">
                  <div v-if="scope.row.status===0||scope.row.status===1">
                    <el-button
                      title="审核"
                      type="danger"
                      plain
                      @click="handleStatusChange(scope.row)"
                    >审核
                    </el-button>
                  </div>
                  <div v-else>
                    <el-tag>
                      已审核
                    </el-tag>
                  </div>
                </template>
              </el-table-column>
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" width="40%">
      <el-form ref="editForm" :model="showForm" :rules="rules" status-icon label-width="80px" class="demo-ruleForm">
        <el-form-item label="弹幕内容">
          <el-input v-model="showForm.content" disabled />
        </el-form-item>
        <el-form-item label="举报内容">
          <el-input v-model="showForm.reportContent" disabled />
        </el-form-item>
        <el-form-item label="举报时间">
          <el-input v-model="showForm.reportDay" disabled />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitForm(3)">不处理</el-button>
        <el-button type="primary" @click="submitForm(2)">处 理</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import { listData, deleteById, reportStatus, selectInfoById } from '@/api/report'

export default {
  name: 'Report',
  filters: {
    handleSwitch({ row }) {
      const { status } = row
      console.log(status)
    }
  },
  data() {
    return {
      status: '0',
      dialogTitle: '',
      dialogFormVisible: false,
      showForm: {
        content: undefined,
        reportContent: undefined,
        reportDay: undefined
      },
      subForm: {
        id: undefined,
        stateus: undefined
      },
      filterText: '',
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      total: 0,
      tableData: [],
      searchForm: {
        beginTime: undefined,
        endTime: undefined,
        current: 1,
        limit: 3
      },
      statusList:
        [
          { value: 0, label: '已提交' },
          { value: 1, label: '已处理' }
        ],
      rules: {}
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
    // 获取用户数据
    this.fetchData(this.searchForm)
  },

  methods: {

    handleStatusChange(row) {
      this.$modal.confirm(`确定要启动审核程序吗？`).then(function() {
        return selectInfoById({ id: 1 })
      }).then((res) => {
        // this.$modal.msgSuccess(`${text}成功`)
        // 使用id查询需要更新状态的数据
        // TODO
        const { bulletId, userId, id, reportContent, reportDay } = res.data
        this.showForm = {
          reportContent,
          reportDay,
          content: bulletId.content
        }
        this.subForm = { id }
        this.dialogFormVisible = true
      }).catch(function() {
        row.status = row.status === '0' ? '1' : '0'
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    reset() {
      this.editForm = {}
      this.resetForm('editForm')
    },
    fetchData(params) {
      listData(params).then(res => {
        const { data } = res
        this.searchForm.current = data.current
        this.searchForm.limit = data.limit
        this.total = data.total
        this.tableData = data.list
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
    onSubmit() {
      // 提交查询表单
      this.searchForm.current = 1
      this.fetchData(this.searchForm)
    },
    async submitForm(state) {
      this.subForm.stateus = state
      const res = await reportStatus(this.subForm)
      if (res['code'] !== 200) {
        this.$message.error('服务器异常')
      } else {
        this.$message.success('审批成功')
      }
      this.dialogFormVisible = false
      await this.fetchData(this.searchForm)
    },
    handleSave() {
      // 1. 修改标题为 新增用户
      this.dialogTitle = '新增敏感词'
      // 2. 将表达的数据清空
      this.reset()
      // 3. 显示对话框
      this.dialogFormVisible = true
    },
    handleEdit(data) {
      // 点击编辑按钮触发
      // 1. 修改标题为 编辑用户
      this.dialogTitle = '编辑敏感词'
      console.log(data)
      // 2. 将表单的数据清空
      this.editForm = data
      // 3. 显示对话框
      this.dialogFormVisible = true
    },
    handleDelete(id) {
      // 点击删除按钮触发
      // 1. 先弹出确认框, 确认是否删除
      // 2. 点击确认按钮时, 才发送请求删除数据
      deleteById(id)
        .then(data => {
          // 3. 如果删除成功, 提示删除成功
          this.$message.success('删除成功')
          // 4. 刷新页面的数据
          this.fetchData()
        })
    },
    handleQuery() {
      this.searchForm.current = 1
      this.fetchData(this.searchForm)
    },
    resetQuery() {
      this.resetForm(this.searchForm)
      this.handleQuery()
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

