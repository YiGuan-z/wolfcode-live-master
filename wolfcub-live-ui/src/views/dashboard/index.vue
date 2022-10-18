<template>
  <div class="dashboard-container">
    <panel-group :stat="stat" :loading="loading" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from '@/components/PanelGroup'
import { todayData } from '@/api/user'

export default {
  name: 'Dashboard',
  components: {
    PanelGroup
  },
  data() {
    return {
      stat: {
        register: 0,
        barrage: 0,
        tip_off: 0,
        online: 0
      },
      loading: true,
      taskId: null
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  mounted() {
    // 这里模拟后台请求动态变化的数据，每2S改变一次数据
    this.fetchData()
    this.taskId = setInterval(this.fetchData, 2000)
  },
  beforeDestroy() {
    clearInterval(this.taskId)
  },
  methods: {
    fetchData() {
      todayData().then(res => {
        const { data } = res
        this.stat = data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
