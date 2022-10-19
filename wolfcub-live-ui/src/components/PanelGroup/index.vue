<template>
  <div>
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" :loading="loading">
          <div class="card-panel-icon-wrapper icon-shopping">
            <svg-icon icon-class="apply" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当前在线
            </div>
            <count-to :start-val="0" :end-val="stat.online" :duration="1" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" :loading="stat.loading">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="peoples" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              今日注册
            </div>
            <count-to :start-val="0" :end-val="stat.register" :duration="1" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" :loading="loading">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="chart" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              今日弹幕
            </div>
            <count-to :start-val="0" :end-val="stat.barrage" :duration="1" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" :loading="loading">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="dict" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              今日举报
            </div>
            <count-to :start-val="0" :end-val="stat.tipOff" :duration="1" class="card-panel-num" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="40">
      <div ref="chartEl" style="height:400px;width: 100%" />
    </el-row>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import * as echarts from 'echarts/lib/echarts'

export default {
  components: {
    CountTo
  },
  props: {
    stat: {
      type: Object,
      required: true
    },
    loading: {
      type: Boolean,
      required: true,
      default: true
    }
  },
  data() {
    return {
      chart: null,
      taskId: null,
      option: {
        title: {
          text: '普通图表'
        },
        legend: {
          orient: 'vertical',
          bottom: 10,
          data: ['图表']
        },
        xAxis: {
          data: ['当前在线', '今日注册', '今日弹幕', '今日举报']
        },
        yAxis: {},
        series: [
          {
            name: '图表',
            type: 'bar',
            data: [0, 0, 0, 0]
          }
        ]
      }
    }
  },
  watch: {
    option: {
      handler(newValue, oldValue) {
        this.chart.setOption(newValue)
      },
      // 因为option是个对象，而我们对于echarts的配置项，要更改的数据往往不在一级属性里面
      // 所以这里设置了deep:true，vue文档有说明
      deep: true
    }
  },
  mounted() {
    // 监听 stat 值的变动
    this.$watch('stat', (n, o) => {
      // 每次 stat 变动前的值 = 0，变动后的值 = n
      this.option.series[0].data = [n.online, n.register, n.barrage, n.tipOff]
    })
    this.chart = echarts.init(this.$refs.chartEl)
    this.chart.setOption(this.option)
    // 这里模拟后台请求动态变化的数据，每2S改变一次数据
    this.changeOption()
    this.taskId = setInterval(this.changeOption, 5000)
  },
  beforeDestroy() {
    clearInterval(this.taskId)
  },
  methods: {
    changeOption() {
      // this.option.series[0].data = [this.stat.online, this.stat.register, this.stat.barrage, this.stat.tip_off]
      // console.log(this.option.series[0].data)
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  //margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width: 550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
