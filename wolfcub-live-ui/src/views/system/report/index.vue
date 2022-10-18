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
                <el-input v-model="searchForm.keyword" placeholder="请输 入被举报的用户名/姓名" />
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
              <!--          <el-table-column label="操作">
                <template v-slot="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    style="margin-right: 10px;"
                    @click="handleEdit(scope.row)">编辑
                  </el-button>
                  &lt;!&ndash;<el-popconfirm
                    confirm-button-text='确认'
                    cancel-button-text='取消'
                    @confirm="handleDelete(scope.row.id)"
                    @onConfirm="handleDelete(scope.row.id)"
                    icon="el-icon-info"
                    icon-color="red"
                    title="这是一段内容确定要删除吗？"
                  >
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      slot="reference">删除
                    </el-button>
                  </el-popconfirm>&ndash;&gt;
                </template>
              </el-table-column>-->
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">提 交</el-button>
      </div>
    </el-dialog>
  </div>
  <!-- 当 <template> 中有多个元素时, 会报错 -->
  <!--  <div></div>-->
</template>

<!--
  二部分: js 代码部分, 里面的语法默认是使用 es6模块化 的默认导出功能, 将 vue组件/页面 的配置信息进行导出
    1. 里面的内容可以按照之前写 new Vue({params}) 中的 params 对象的方式编写
    2. 在这里 data 属性必须是一个函数, 并且返回一个对象
 -->
<script>
import { listData, saveOrUpdate, deleteById, reportStatus } from '@/api/report'

export default {
  name: 'Report',
  data() {
    return {
      status: '0',
      dialogTitle: '',
      dialogFormVisible: false,
      editForm: {
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
      rules: {
      }
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
    // 获取部门数据
    // listAll().then(res => {
    //   this.departments = res.data
    // });
  },
  methods: {
    handleStatusChange(row) {
      const text = row.status === '0' ? '启用' : '禁用'
      this.$modal.confirm(`确认要"${text}"这条弹幕吗？`).then(function() {
        return reportStatus(row.id, row.status)
      }).then(() => {
        this.$modal.msgSuccess(`${text}成功`)
      }).catch(function() {
        row.status = row.status === '0' ? '1' : '0'
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    reset() {
      this.editForm = {
      }
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
              this.fetchData()
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
