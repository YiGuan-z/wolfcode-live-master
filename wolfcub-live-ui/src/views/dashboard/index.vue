<template>
  <div class="dashboard-container">
    <panel-group :stat="stat" :loading="loading" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from '@/components/PanelGroup'

export default {
  name: 'Dashboard',
  components: {
    PanelGroup
  },
  data() {
    return {
      stat: {
        register: 2837,
        barrage: 27920,
        tip_off: 489,
        online: 1387
      },
      loading: true
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
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
