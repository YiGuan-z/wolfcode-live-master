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
              <el-form-item label="是否删除">
                <el-select v-model="searchForm.deleted">
                  <el-option
                    v-for="item in deletedList"
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
              <el-form-item>
                <el-button type="success" plain icon="el-icon-plus" @click="handleSave()">新增</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- 表格 -->
          <el-row>
            <el-table v-loading="loading" stripe :data="tableData">
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="avatar" label="头像">
                <template v-slot="scope">
                  <el-avatar :src="scope.row.avatar || defaultImgUrl" />
                </template>
              </el-table-column>
              <el-table-column prop="username" label="用户名" />
              <el-table-column prop="nickname" label="昵称" />
              <el-table-column prop="gender" label="性别">
                <template v-slot="scope">
                  {{ scope.row.gender | genderFilter }}
                </template>
              </el-table-column>
              <el-table-column prop="signature" label="个性签名" :show-overflow-tooltip="true" />
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
              <el-table-column prop="createdTime" label="创建时间" />
              <el-table-column prop="updatedTime" label="更新时间" />
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
                    :title="`确定${scope.row.deleted ? '恢复' : '删除'}这个用户吗？`"
                    @confirm="handleDelete(scope.row.id, !scope.row.deleted)"
                    @onConfirm="handleDelete(scope.row.id, !scope.row.deleted)"
                  >
                    <el-button
                      slot="reference"
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                    >{{ scope.row.deleted ? '恢复' : '删除' }}
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" width="30%">
      <el-form ref="editForm" :model="editForm" :rules="rules" status-icon label-width="80px" class="demo-ruleForm">
        <el-input v-show="false" v-model="editForm.id" type="hidden" />
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editForm.nickname" type="name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" type="username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="editForm.gender" size="small">
            <el-radio-button label="0">保密</el-radio-button>
            <el-radio-button label="1">男</el-radio-button>
            <el-radio-button label="2">女</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-switch
            v-model="editForm.status"
            active-value="0"
            inactive-value="1"
          />
        </el-form-item>
        <el-form-item label="个性签名" prop="signature">
          <el-input v-model="editForm.signature" type="textarea" :rows="2" />
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
import { changeUserStatus, deleteById, listData, saveOrUpdate } from '@/api/user'

export default {
  name: 'User',
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
      editForm: {
        nickname: '',
        dept: {},
        gender: 0,
        status: '0'
      },
      statusList: [{ value: 0, label: '正常' }, { value: 1, label: '禁用' }],
      deletedList: [{ value: 0, label: '正常' }, { value: 1, label: '删除' }],
      rules: {
        'username': [{ required: true, message: '用户名不能为空' }]
      },
      defaultImgUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      total: 0,
      tableData: [],
      loading: true,
      searchForm: {
        keyword: '',
        status: undefined,
        deleted: 0,
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
    reset() {
      this.editForm = {
        dept: {},
        gender: 0,
        status: '0'
      }
      this.resetForm('editForm')
    },
    handleStatusChange(row) {
      const text = row.status === '0' ? '启用' : '禁用'
      this.$modal.confirm('确认要"' + text + '""' + (row.nickname || row.username) + '"用户吗？').then(function() {
        return changeUserStatus(row.id, row.status)
      }).then(() => {
        this.$modal.msgSuccess(text + '成功')
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
      this.editForm = data
      // 3. 显示对话框
      this.dialogFormVisible = true
    },
    handleDelete(id, deleted) {
      // 点击删除按钮触发
      // 1. 先弹出确认框, 确认是否删除
      // 2. 点击确认按钮时, 才发送请求删除数据
      deleteById(id, deleted)
        .then(data => {
          // 3. 如果删除成功, 提示删除成功
          this.$message.success('操作成功')
          // 4. 刷新页面的数据
          this.searchForm.deleted = 0
          this.fetchData(this.searchForm)
        })
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
