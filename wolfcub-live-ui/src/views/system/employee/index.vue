<!--
  一部分: 当前页面/组件的模板部分, 在这里面编写页面需要的模板数据
    1. 一定是使用 <template> 标签作为根标签
    2. 在 <template> 中有且仅有一个根标签
 -->
<template>
  <div class="app-container">
    <div class="body">
      <el-row>
        <!-- 树形控件 -->
        <el-col :span="4">
          <div style="padding-right: 20px">
            <el-input
              v-model="filterText"
              size="mini"
              placeholder="输入关键字进行过滤"
            />
            <el-tree
              ref="tree"
              :default-expand-all="true"
              :filter-node-method="filterNode"
              :expand-on-click-node="false"
              node-key="id"
              :data="treeList"
              :props="defaultProps"
              @node-click="handleNodeClick"
            />
          </div>
        </el-col>
        <el-col :span="20">
          <!-- 查询表单 -->
          <el-row>
            <el-form :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
              <el-form-item label="关键字">
                <el-input v-model="searchForm.keyword" placeholder="请输入用户名/姓名" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
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
              <el-table-column prop="avatar" label="头像">
                <template v-slot="scope">
                  <el-avatar :src="scope.row.avatar || defaultImgUrl" />
                </template>
              </el-table-column>
              <el-table-column prop="username" label="用户名" />
              <el-table-column prop="name" label="姓名" />
              <el-table-column prop="age" label="年龄" />
              <el-table-column prop="email" label="邮箱" />
              <el-table-column prop="admin" label="用户类型">
                <template v-slot="scope">
                  <el-tag v-if="scope.row.admin">管理员</el-tag>
                  <el-tag v-else type="info">普通用户</el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="dept.name" label="部门" />
              <el-table-column prop="hireDate" label="入职时间" />
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
                    title="这是一段内容确定删除吗？"
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
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name" type="name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" type="username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="editForm.age" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email" />
        </el-form-item>
        <el-form-item label="管理员" prop="admin">
          <el-switch
            v-model="editForm.admin"
            active-color="#13ce66"
          />
        </el-form-item>
        <el-form-item label="所属部门">
          <el-select v-model="editForm.dept.id" placeholder="请选择部门">
            <el-option
              v-for="item in departments"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="入职时间" prop="hireDate">
          <el-date-picker v-model="editForm.hireDate" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
        </el-form-item>
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
import { listData, saveOrUpdate, deleteById } from '@/api/employee'
import { listAll, treeList } from '@/api/department'

const getDeptIds = (arr, dept) => {
  // 先将当前元素的 id 保存到数组中
  arr.push(dept.id)
  // 判断是否还有子节点(递归的出口)
  if (dept.children && dept.children.length > 0) {
    for (const i in dept.children) {
      // 递归获取元素的 id
      getDeptIds(arr, dept.children[i])
    }
  }
}

export default {
  name: 'Employee',
  data() {
    return {
      dialogTitle: '',
      dialogFormVisible: false,
      editForm: {
        dept: {}
      },
      departments: [
        { id: 1, name: 'AAA' },
        { id: 2, name: 'BBB' }
      ],
      treeList: [],
      filterText: '',
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      rules: {
        'username': [{ required: true, message: '用户名不能为空' }],
        'age': [
          { required: true, message: '年龄不能为空' },
          { type: 'number', message: '年龄必须为数字值' }
        ]
      },
      defaultImgUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      total: 0,
      tableData: [],
      searchForm: {
        username: '',
        deptId: null,
        endDate: null,
        current: 1,
        limit: 10
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
    listAll().then(res => {
      this.departments = res.data
    })
    // 获取部门树数据
    treeList().then(res => {
      this.treeList = res.data
    })
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    reset() {
      this.editForm = {
        dept: {}
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
      this.dialogTitle = '新增员工'
      // 2. 将表达的数据清空
      this.reset()
      // 3. 显示对话框
      this.dialogFormVisible = true
    },
    handleEdit(data) {
      // 点击编辑按钮触发
      // 1. 修改标题为 编辑用户
      this.dialogTitle = '编辑员工'
      if (!data.dept) {
        data.dept = {}
      }
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
    handleNodeClick(data) {
      // 以当前点击的部门的 id 作为查询条件 deptId 的值
      const deptIds = []
      getDeptIds(deptIds, data)

      // 重新查询员工数据
      this.searchForm.deptIds = deptIds
      this.fetchData(this.searchForm)
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
