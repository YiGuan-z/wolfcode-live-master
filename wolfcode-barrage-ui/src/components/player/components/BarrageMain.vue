<template>
  <div id="barrage"></div>
</template>

<script>
import "../css/player-global.css";

export default {
  props: {
    barrageList: {
      type: Array,
      default: null,
    },
  },
  data() {
    return {
      barrage: [],
      danTunnel: {
        row: [], //轨道结束的时间
        top: [],
        bottom: [],
      },
      currentTime: 0,
      paused: false,
    };
  },
  methods: {
    Pause() {
      for (let i = 0; i < this.barrage.length; i++) {
        this.barrage[i].style.animationPlayState = "paused";
      }
      this.paused = true;
    },
    Start() {
      for (let i = 0; i < this.barrage.length; i++) {
        this.barrage[i].style.animationPlayState = "running";
      }
      this.paused = false;
    },
    //更新时间
    TimeUpdate(currTime) {
      if (Math.round(currTime) !== this.currentTime) {
        this.currentTime = Math.round(currTime);
        //绘制弹幕
        if (this.barrageList == null) {
          return;
        }
        let len = this.barrageList.length;
        for (let i = 0; i < len; i++) {
          if (this.barrageList[i].time === this.currentTime) {
            this.Drawbarrage(this.barrageList[i])
          }
        }
      }
    },
    //获取滚动弹道
    GetRowTunnel(text) {
      //当前弹幕结束时间
      let duration = 30 - text.length * 0.5;
      let width = document.getElementById("barrage").offsetWidth;
      duration =
          Math.ceil((width + text.length * 20) / (3000 / duration)) +
          this.currentTime;
      //计算弹道数量
      let tunnnel = (document.getElementById("barrage").offsetHeight - 40) / 26;
      tunnnel = Math.floor(tunnnel);
      //遍历轨道
      for (let i = 0; i < this.danTunnel.row.length; i++) {
        //如果当前轨道结束时间小于新弹幕的结束时间
        //说明弹幕可以放在该弹道
        if (this.danTunnel.row[i] < duration) {
          this.danTunnel.row[i] = duration;
          return i;
        }
      }
      //如果没有则尝试新增加弹道
      if (this.danTunnel.row.length < tunnnel) {
        this.danTunnel.row.push(duration);
        return this.danTunnel.row.length - 1;
      }
      //如果不可以新增弹道，则使用随机弹道
      return Math.round(Math.random() * tunnnel);
    },
    //获取固定弹道
    GetFixedTunnel(type) {
      //当前弹幕结束时间
      let duration = this.currentTime + 5;
      //计算弹道数量
      let tunnnel = (document.getElementById("barrage").offsetHeight - 40) / 26;
      tunnnel = Math.floor(tunnnel);
      switch (type) {
        case 1:
          //遍历轨道
          for (let i = 0; i < this.danTunnel.row.length; i++) {
            //如果当前轨道结束时间小于新弹幕的结束时间
            //说明弹幕可以放在该弹道
            if (this.danTunnel.top[i] < duration) {
              this.danTunnel.top[i] = duration;
              return i;
            }
          }
          //如果没有则尝试新增加弹道
          if (this.danTunnel.top.length < tunnnel) {
            this.danTunnel.top.push(duration);
            return this.danTunnel.top.length - 1;
          }
          break;
        case 2:
          //遍历底部弹幕轨道
          for (let i = 0; i < this.danTunnel.bottom.length; i++) {
            //如果当前轨道结束时间小于新弹幕的结束时间
            //说明弹幕可以放在该弹道
            if (this.danTunnel.bottom[i] < duration) {
              this.danTunnel.bottom[i] = duration;
              return i;
            }
          }
          //如果没有则尝试新增加弹道
          if (this.danTunnel.bottom.length < tunnnel) {
            this.danTunnel.bottom.push(duration);
            return this.danTunnel.bottom.length - 1;
          }
          break;
      }
      //如果不可以新增弹道，则使用随机弹道
      return Math.round(Math.random() * tunnnel);
    },
    //清除弹幕
    Clearbarrage() {
      this.danTunnel.row = [];
      this.danTunnel.top = [];
      this.danTunnel.bottom = [];
      document.getElementById("barrage").innerHTML = "";
    },
    DrawBarrage({text, color, type}, send = false) {
      let width = document.getElementById("barrage").offsetWidth;
      var item = document.createElement("span");
      var content = document.createTextNode(text);
      item.style.color = color;
      item.appendChild(content);
      item.className = "barrage-item";
      if (send) {
        item.style.border = "1px solid red";
      }
      //滚动弹幕
      if (type === 0) {
        //设置轨道
        item.style.top = String(this.GetRowTunnel(text) * 26) + "px";
        item.classList.add("barrage-row");
        item.style.transform = `translateX(-${width}px)`;
        this.barrage.push(item);
        document.getElementById("barrage").appendChild(item);
        item.addEventListener("animationend", () => {
          this.barrage.splice(item);
          document.getElementById("barrage").removeChild(item);
        });
        item.classList.add("barrage-row-move");
      } else if (type === 1) {
        item.style.width = "100%";
        item.style.textAlign = "center";
        item.style.top = String(this.GetFixedTunnel(1) * 26) + "px";
        this.barrage.push(item);
        document.getElementById("barrage").appendChild(item);
        item.addEventListener("animationend", () => {
          this.barrage.splice(item);
          document.getElementById("barrage").removeChild(item);
        });
        item.classList.add("barrage-center-move");
      } else if (type === 2) {
        item.style.width = "100%";
        item.style.textAlign = "center";
        item.style.bottom = String(this.GetFixedTunnel(1) * 26) + "px";
        this.barrage.push(item);
        document.getElementById("barrage").appendChild(item);
        item.addEventListener("animationend", () => {
          this.barrage.splice(item);
          document.getElementById("barrage").removeChild(item);
        });
        item.classList.add("barrage-center-move");
      }
      if (this.paused) {
        item.style.animationPlayState = "paused";
      }
    },
    //设置弹幕不透明度
    SetOpaque(opacity) {
      let barrage = document.getElementById("barrage");
      if (barrage != null) {
        barrage.style.opacity = parseFloat(opacity) * 0.01;
      }
    },
  },
};
</script>

<style scoped>
#barrage {
  z-index: 1;
  position: absolute;
  overflow: hidden;
  width: 100%;
  height: 100%;
}
</style>

