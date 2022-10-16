<template>
<div style="margin: 0 auto;  width: 1250px;">
  <div>
    <h1 class="videoTitle"> {{ videoTitle }} </h1>
    <span class="totalBarrage">{{ barrageTotalWatchCount }}&nbsp;次播放</span>
    <span class="totalBarrage">&nbsp;·&nbsp;</span>
    <span class="totalBarrage">总弹幕数&nbsp;{{ barrageTotalCount }}&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span class="totalBarrage">{{ videoCreateTime }}</span>
  </div>

  <div>
    <div style="float: left">
      <div ref="videoOuterLayer" class="uncheck" @mouseleave="Leave()" @mousemove="MouseMove()">
        <div id="video" ref="videoBox" @click="CloseMenuOrPlayVideo($event)" @contextmenu.prevent="OpenMenu($event)">
          <!--快捷键层-->
          <hot-key v-show="showHotKey"></hot-key>
          <!--视频信息层-->
          <video-info v-if="showVideoInfo"></video-info>
          <context-menu id="menu" ref="menu"></context-menu>
          <!--弹幕层-->
          <barrage v-if="showBarrage" :barrageList="barrageList" ref="barrage"></barrage>
          <!--Video层-->
          <video id="player" ref="video" :src="src" class="player" preload="auto" @canplay="VideoInit()" @timeupdate="TimeUpdate()" @ended="VideoEnd()"/>
          <!--消息层-->
          <div>
            <span v-if="message" class="msg">{{ msg }}</span>
          </div>
          <!--控制栏层-->
          <div class="control" v-show="control">
            <control ref="control"></control>
          </div>
        </div>
        <!--发送弹幕组件-->
        <sendBarrage></sendBarrage>
        <webSocket ref="webSocket"/>

      </div>
    </div>

    <div class="about">
      <div class="barrage-list">
        弹幕列表
      </div>
      <div class="barrage-title">
        <span class="barrage-title-time">时间</span>
        <span class="barrage-title-content">弹幕内容</span>
        <span class="barrage-title-send-time">发送时间</span>
      </div>
      <div style="overflow: auto; height: 480px;">

        <div v-for="(item, index) of barrageHistoryList" :key="index" class="barrage-content" v-on:click="changBackGroundColor()" v-bind:style="barrageContentStyle">
          <span class="barrage-content-time">{{ item.sendTime }}</span>
          <span class="barrage-content-content">{{ item.msg }}</span>
          <span class="barrage-content-send-time">{{ item.createTime }}</span>
        </div>

      </div>


    </div>
  </div>
</div>

</template>

<script>
import Hls from "hls.js";
import BarrageHotKey from "./components/BarrageHotKey.vue";
import BarrageControl from "./components/BarrageControl.vue";
import BarrageMain from "./components/BarrageMain.vue";
import BarrageSend from "./components/BarrageSend.vue";
import BarrageMenu from "./components/BarrageMenu.vue"
import BarrageVideoInfo from './components/BarrageVideoInfo.vue';
import WebSocket from "../websocket/WebSocket";

export default {
  components: {
    control: BarrageControl,
    barrage: BarrageMain,
    "sendBarrage": BarrageSend,
    "context-menu": BarrageMenu,
    "video-info": BarrageVideoInfo,
    "hot-key": BarrageHotKey,
    "webSocket": WebSocket,
  },
  data() {
    return {
      hls: null,
      msg: "",//消息内容
      message: false,
      control: false,
      showBarrage: true,
      showVideoInfo: false,
      showHotKey: false,
      currentTime: 0,
      barrageList: [],
      src: 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4',
      original: false,//是否为原始分辨率
      defaultRes: 720,//默认分辨率(之后可调整)
      maxRes: 1080,//最大分辨率
      currentRes: 720,//当前分辨率
      barrageHistoryList: [],
      barrageTotalCount: 0,
      barrageOnlineCount: 0,
      barrageTotalWatchCount: 0,
      barrageContentStyle: {
        backgroundColor: ""
      },
      pause:true,
      temp:0,
      videoTitle: '叩丁狼：高性能弹幕系统实现',
      videoCreateTime: '2022-10-03 18:32:46'
    };
  },
  computed: {
    //参数
    params() {
      return Object.assign({
        type: 'mp4',
        resource: {}
      }, this.options)
    },
    //播放器设置
    playerConfig: {
      get() {
        let config = localStorage.getItem("player-config");
        return JSON.parse(config);
      },
      set(val) {
        let config = JSON.parse(localStorage.getItem("player-config"));
        config[val.key] = val.value;
        localStorage.setItem("player-config", JSON.stringify(config));
      }
    }
  },
  methods: {
    changBackGroundColor() {
    },
    VideoInit() {
      let video = this.$refs.video;
      this.$refs.control.Init(video.duration);
    },
    //更新进度时间
    TimeUpdate() {
      let video = this.$refs.video;
      if (video === undefined) {
        return;
      }
      this.currentTime = video.currentTime;
      let loaded = 0;
      if (video.buffered.length) {
        loaded = video.buffered.end(video.buffered.length - 1)
      }
      this.$refs.control.TimeUpdate(
          video.currentTime,
          loaded
      );
      if (this.$refs.barrage) {
        this.$refs.barrage.TimeUpdate(video.currentTime);
      }

      const number = parseInt(this.currentTime);
      if (this.temp !== parseInt(this.currentTime)) {
        this.$refs.webSocket.clientBarrageMsgRollingSend();
        this.temp =number
      }

    },
    VideoEnd() {
      this.$refs.control.VideoEnd();
      this.Clearbarrage();
    },
    ChangeVideoProgress(currTime) {
      this.$refs.video.currentTime = currTime;
    },
    //显示消息
    ShowMessage(msg) {
      this.msg = msg;
      this.message = true;
      //定时隐藏
      setTimeout(() => {
        this.message = false;
      }, 3000);
    },
    //清除弹幕
    Clearbarrage() {
      if (this.$refs.barrage) {
        this.$refs.barrage.Clearbarrage();
      }
    },
    //暂停弹幕
    PauseBarrage() {
      if (this.$refs.barrage) {
        this.$refs.barrage.Pause();
        this.pause=true;
      }
    },
    //开始弹幕
    StartBarrage() {
      if (this.$refs.barrage) {
        this.$refs.barrage.Start();
        this.pause=false;
      }
    },

    //发送弹幕
    Send({text, color, type}, selfFlag) {
      if (this.showBarrage) {
        let currentTime = this.$refs.video.currentTime;
        this.Draw({text, color, type}, selfFlag);
        this.$refs.webSocket.clientBarrageSend({text, color, type, currentTime})
        this.ShowMessage("发送成功");
      }
    },
    //绘制弹幕
    Draw({text, color, type}, selfFlag) {
      this.$refs.barrage.DrawBarrage({text, color, type}, selfFlag);
    },
    //设置弹幕不透明度
    SetOpaque(opacity) {
      this.$refs.barrage.SetOpaque(opacity);
    },
    //开启或关闭弹幕
    ShowBarrage(val) {
      this.showBarrage = val;
    },
    //显示/隐藏控制栏
    Leave() {
      if (!this.$refs.video.paused) {
        this.$refs.control.ShowMenu("");
        this.control = false;
      }
    },
    MouseMove() {
      if (!this.control) {
        this.control = true;
        if (!this.$refs.video.paused) {
          setTimeout(() => {
            this.$refs.control.ShowMenu("");
            this.control = false;
          }, 3000);
        }
      }
    },
    //开启右键菜单
    OpenMenu(e) {
      if (e.target.id === "menu") {
        return;
      }
      this.$refs.menu.OpenMenu(e);
    },
    //关闭右键菜单或者控制视频播放
    CloseMenuOrPlayVideo(e) {
      let id = e.target.id;
      if (id !== "menu") {
        //如果menu打开就关闭menu，否则进行播放暂停
        if (this.$refs.menu.MenuIsShow()) {
          this.$refs.menu.CloseMenu();
        } else {
          if (id === "barrage" || id === "player") {
            this.$refs.control.PlayOrPause();
          }
        }
      }
    },

    //初始化配置
    InitConfig() {
      let config = localStorage.getItem("player-config");
      if (!config) {
        localStorage.setItem("player-config", JSON.stringify({
          volume: 80,
          barrage: true,
        }));
      }
    },
    //读取配置
    ReadConfig() {
      this.$refs.video.volume = this.playerConfig.volume / 100;
    },
    //获取最大分辨率
    getMaxRes() {
      let resource = this.params.resource;
      if (resource.res1080) return 1080;
      if (resource.res720) return 720;
      if (resource.res480) return 480;
      if (resource.res360) return 360;
    },
    //获取默认的分辨率
    getDefaultRes(res) {
      this.maxRes = this.getMaxRes();
      this.currentRes = (this.maxRes >= res ? res : this.maxRes);
      return 'res' + this.currentRes;
    },
    //设置分辨率
    SetRes(res) {
      if (this.original) return;
      if (this.currentRes === res) return;
      this.currentRes = res;
      if (this.params.type === "hls") {

        //this.LoadHls(this.params.resource['https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4']);
      } else {
        if (this.original) {
          // this.$refs.video.src = 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4';
        } else {
          // let res = this.getDefaultRes(this.defaultRes);
          //this.$refs.video.src = 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4'
        }
      }
    },
    LoadHls(src) {
      if (this.hls != null) {
        this.hls.stopLoad();
        this.hls.destroy();
      }
      this.hls = new Hls();
      this.hls.loadSource(src);
      this.hls.attachMedia(this.$refs.video);
      this.hls.on(Hls.Events.ERROR, () => {
        console.log('加载失败');
      });
    }
  },
  mounted() {
    if (this.params.resource.original) this.original = true;
    if (this.params.type === "hls") {
      if (this.original) {
        this.LoadHls(this.params.resource.original);
      } else {
        //let res = 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4';
        // this.LoadHls(this.params.resource[res]);
      }
    } else {
      if (this.original) {
        // this.$refs.video.src = 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4';
      } else {
        //  let res = this.getDefaultRes(this.defaultRes);
        // this.$refs.video.src = 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4'
      }
    }
    this.control = true;
  },
  created() {
    this.InitConfig();
  },
  beforeDestroy() {
    if (this.hls != null) {
      this.hls.stopLoad();
      this.hls.destroy();
    }
  },

};
</script>

<style scoped>
.giteeLink {
  text-decoration: none;
  color: #000000bf;
  font-size: 20px;
}
.giteeLink:hover {
  color: black;
}
.uncheck {
  width: 920px;
  /**禁止文字选中 */
  -moz-user-select: -moz-none;
  -moz-user-select: none;
  -o-user-select: none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

#video {
  height: 0;
  width: 100%;
  position: relative;
  padding-bottom: 56.25%;
}

/**镜像翻转 */
.player-mirror {
  will-change: transform;
  transform: rotateY(180deg);
  -webkit-transform: rotateY(180deg); /* Safari and Chrome */
  -moz-transform: rotateY(180deg); /* Firefox */
}

.player {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: black;
}

.control {
  z-index: 20;
  position: absolute;
  width: 100%;
  height: 50px;
  background: linear-gradient(rgba(0, 0, 0, 0), #000);
  bottom: 0;
  transition: opacity 1s;
  -moz-transition: opacity 1s; /* Firefox 4 */
  -webkit-transition: opacity 1s; /* Safari 和 Chrome */
  -o-transition: opacity 1s; /* Opera */
}

.msg {
  z-index: 10;
  position: absolute;
  text-align: center;
  background: rgba(0, 0, 0, 0.6);
  width: 160px;
  height: 26px;
  left: 10px;
  bottom: 60px;
  line-height: 26px;
  border-radius: 2px;
  color: aliceblue;
}
.videoTitle {
  cursor: pointer;
  font-weight: 500;
  color: #212121;
  line-height: 26px;
  font-size: 20px;
  margin-bottom: 4px;
}

.videoTitle:hover {
  color: #1890ff;
}

.totalBarrage {
  margin-bottom: 18px;
  display: inline-block;
  height: 16px;
  font-style: normal;
  color: #999;
  white-space: nowrap;
  line-height: 1.5;
  font-size: 12px;
}

#app {
  margin: 60px auto;
  width: 1300px;
  height: 600px;
}

.player {
  float: left;

  border-radius: 4px;
}

.about {
  margin-left: 4px;
  float: left;
  width: 320px;
  height: 576px;
  /*color: #838c9a;*/
  /*border: solid 1px #838c9a;*/
  /*border: solid 2px #eee;*/
  border-radius: 4px;
}

.barrage-list {
  background-color: #f4f4f4;
  color: #222;
  font-size: 16px;
  padding: 20px 0 0 20px;
  height: 40px;
}

.barrage-title {
  margin-bottom: 10px;
}

.barrage-title-time {
  margin-left: 15px;
  margin-top: 10px;
  width: 60px;
  font-size: 12px;
  color: #999999;
}

.barrage-title-content {
  width: 165px;
  margin-top: 10px;
  font-size: 12px;
  margin-left: 35px;
  color: #999999;
}

.barrage-title-send-time {
  width: 110px;
  margin-top: 10px;
  margin-left: 107px;
  font-size: 12px;
  color: #999999;
}

.barrage-content {
  height: 24px;
}

.barrage-content:hover {
  cursor: pointer;
}

.barrage-content-time {
  margin-left: 15px;
  line-height: 20px;
  width: 60px;
  font-size: 12px;
  display: inline-block;
  margin-top: 2px;
  float: left;
  color: #999999;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.barrage-content-content {
  line-height: 20px;
  margin-top: 2px;
  font-size: 12px;
  display: inline-block;
  color: #999999;
  float: left;
  width: 155px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.barrage-content-send-time {
  margin-top: 2px;
  /*width: 110px;*/
  line-height: 20px;
  font-size: 12px;
  display: inline-block;
  float: left;
  color: #999999;
}

</style>
