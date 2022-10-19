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
                <el-input v-model="searchForm.keyword" placeholder="请输入用户名/姓名" />
              </el-form-item>
              <el-form-item label="日期">
                <el-date-picker
                  v-model="searchForm.beginTime"
                  type="datetime"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd HH:mm:ss"
                />
                -
                <el-date-picker
                  v-model="searchForm.endTime"
                  type="datetime"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd HH:mm:ss"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
              </el-form-item>
              <el-form-item>
                <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
              </el-form-item>
              <el-form-item>
                <el-button type="success" icon="el-icon-circle-plus-outline" @click="handleSave()">新增</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- 表格 -->
          <el-row>
            <el-table stripe :data="tableData">
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="sensitiveMsg" label="敏感词" />
              <el-table-column prop="showMsg" label="展示词" />
              <el-table-column prop="createTime" label="创建时间" />
              <el-table-column prop="updateTime" label="更新时间" />
              <el-table-column label="操作">
                <template v-slot="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    style="margin-right: 10px;"
                    @click="handleEdit(scope.row)"
                  >编辑
                  </el-button>
                  <el-popconfirm
                    confirm-button-text="确认"
                    cancel-button-text="取消"
                    icon="el-icon-info"
                    icon-color="red"
                    title="这是一段内容确定要删除吗？"
                    @confirm="handleDelete(scope.row.id)"
                    @onConfirm="handleDelete(scope.row.id)"
                  >
                    <el-button
                      slot="reference"
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                    >删除
                    </el-button>
                  </el-popconfirm>
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
      <el-form ref="editForm" :model="editForm" :rules="rules" status-icon label-width="80px" class="demo-ruleForm">
        <el-input v-show="false" v-model="editForm.id" type="hidden" />
        <el-form-item label="敏感词" prop="sensitive_msg">
          <el-input v-model="editForm.sensitiveMsg" autocomplete="off" />
        </el-form-item>
        <el-form-item label="展示词" prop="show_msg">
          <el-input v-model="editForm.showMsg" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listData, saveOrUpdate, deleteById } from '@/api/bulletMsgSensitive'

export default {
  name: 'BulletMsgSensitive',
  data() {
    return {
      dialogTitle: '',
      dialogFormVisible: false,
      editForm: {
        sensitiveMsg: undefined,
        showMsg: undefined
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
        limit: 10
      },
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
    submitForm() {
      // 增加表单校验拦截, 如果表单校验不通过, 不应该关闭弹窗
      this.$refs.editForm.validate()
        .then(valid => {
          // 获取到当前的表单数据, 并发送请求到后台
          saveOrUpdate(this.editForm)
            .then(res => {
              // 保存成功后重新刷新表格
              this.fetchData(this.searchForm)
              // 提示用户保存成功
              this.$message.success('操作成功!')
              // 隐藏弹框
              this.dialogFormVisible = false
            })
        })
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
      Object.keys(this.searchForm).forEach(key => {
        if (key === 'beginTime' && key === 'endTime' && key === 'keyword') {
          this.searchForm[key] = undefined
        }
      })

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

