<template>
  <div class="navbar">
    <!-- 收缩左侧菜单栏的按钮组件 -->
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <!-- 收缩按钮旁边的导航条(面包屑)组件 -->
    <breadcrumb class="breadcrumb-container" />

    <!-- 右侧头像/菜单 -->
    <div class="right-menu">
      <!-- 鼠标移动后显示下拉框组件 -->
      <el-dropdown class="avatar-container" trigger="click">
        <!-- 头像 -->
        <div class="avatar-wrapper">
          <!-- 头像图片路径 -->
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <!-- 头像旁边的倒三角 -->
          <i class="el-icon-caret-bottom" />
        </div>
        <!-- 下拉菜单 -->
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <el-dropdown-item class="user" @click.native="opendialog">
            <span style="display: block">个人信息修改</span>
          </el-dropdown-item>
          <el-dropdown-item @click.native="logout">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      title="这里是员工个人信息修改"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="userInfo" label-width="80px">
        <el-form-item label="登陆名">
          <el-input v-model="userInfo.username" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="userInfo.name" />
        </el-form-item>
        <el-form-item label="用户头像">
          <el-input v-model="userInfo.avatar" placeholder="可以放置外部图片链接" />
        </el-form-item>
        <el-form-item label="用户头像选择">
          <el-upload
            class="avatar-uploader"
            action="dev-api/file/upload"
            :headers="upHeader"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :data="{id: userInfo.id}"
          >
            <el-avatar :src="userInfo.avatar" />
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleResetInfo">取 消</el-button>
        <el-button type="primary" @click="handleSaveInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { getToken } from '@/utils/auth'
import { listData } from '@/api/employee'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data() {
    return {
      dialogVisible: false,
      userInfo: {},
      upHeader: {
        'X-Token': getToken()
      }
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'username',
      'name',
      'id'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    opendialog() {
      const { avatar, username, name, id } = this
      this.userInfo = { avatar, username, name, id }
      this.dialogVisible = true
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleAvatarSuccess(res, file) {
      // this.imageUrl = URL.createObjectURL(file.raw)
      // this.fetchData(this.userInfo)
      console.log(res)
      const { data } = res
      this.$store.dispatch('user/setAvatar', `${data}`)
      this.userInfo.avatar = this.avatar
    },
    fetchData(params) {
      this.loading = true
      listData(params).then(res => {
        const { data } = res
        this.userInfo = data.list.map(value => {
          return {
            ...value,
            avatar: `/dev-api${value.avatar}`
          }
        })
        this.loading = false
      })
    },
    async handleResetInfo() {
      await this.$store.dispatch('user/getInfo')
      this.dialogVisible = false
    },
    async handleSaveInfo() {
      const code = await this.$store.dispatch('user/updateUserInfo', this.userInfo)
      if (code === 200) {
        this.$message.success('修改成功')
      } else {
        this.$message.warning('修改失败')
      }
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
