<template>
  <div class="app-container">
    <div class="body">
      <el-row>
        <el-col :span="24">
          <el-form :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
            <el-form-item>
              <el-button type="success" icon="el-icon-circle-plus-outline" @click="handleSave()">新增</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-table stripe :data="tableData">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="sn" label="编号"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="parent.name" label="父部门"/>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  style="margin-right: 10px;"
                  @click="handleEdit(scope.row)">编辑
                </el-button>
                <el-popconfirm
                  confirm-button-text='确认'
                  cancel-button-text='取消'
                  @confirm="handleDelete(scope.row.id)"
                  @onConfirm="handleDelete(scope.row.id)"
                  icon="el-icon-info"
                  icon-color="red"
                  title="这是一段内容确定删除吗？"
                >
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    slot="reference">删除
                  </el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="searchForm.current"
            :page-sizes="[3, 5, 10, 20]"
            :page-size="searchForm.limit"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </el-col>
      </el-row>
    </div>
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" width="40%">
      <el-form :model="editForm" :rules="rules" status-icon ref="editForm" label-width="80px" class="demo-ruleForm">
        <el-input type="hidden" v-show="false" v-model="editForm.id"/>
        <el-form-item label="编号" prop="sn">
          <el-input v-model="editForm.sn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
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
  import {listData, saveOrUpdate, deleteById} from "@/api/department";

  export default {
    name: 'Department',
    data() {
      return {
        dialogTitle: '',
        dialogFormVisible: false,
        editForm: {},
        rules: {
          'sn': [{required: true, message: '编号不能为空'}],
          'name': [{required: true, message: '名称不能为空'}]
        },
        total: 0,
        tableData: [],
        searchForm: {
          current: 1,
          limit: 3
        }
      }
    },
    methods: {
      fetchData(params) {
        listData(params).then(res => {
          const {data} = res
          this.searchForm.current = data.current
          this.searchForm.limit = data.limit
          this.total = data.total
          this.tableData = data.list
        })
      },
      handleCurrentChange(current) {
        this.searchForm.current = current;
        // 当每页显示数发生变动时触发
        this.fetchData(this.searchForm)
      },
      handleSizeChange(size) {
        this.searchForm.limit = size;
        // 当前页发生变动时触发
        this.fetchData(this.searchForm)
      },
      onSubmit() {
        // 提交查询表单
        this.searchForm.current = 1;
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
                this.$message.success('操作成功!');
                // 隐藏弹框
                this.dialogFormVisible = false
              })
          })
      },
      handleSave() {
        // 1. 修改标题为
        this.dialogTitle = '新增部门'
        // 2. 将表达的数据清空
        this.editForm = {}
        // 3. 显示对话框
        this.dialogFormVisible = true
      },
      handleEdit(data) {
        // 点击编辑按钮触发
        // 1. 修改标题为
        this.dialogTitle = '编辑部门'
        // 2. 将表达的数据清空
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
            this.$message.success('删除成功');
            // 4. 刷新页面的数据
            this.fetchData()
          })
      }
    },
    created() {
      // 获取数据
      this.fetchData();
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
