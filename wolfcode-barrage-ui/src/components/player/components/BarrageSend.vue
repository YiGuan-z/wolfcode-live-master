<template>
  <div class="bottom-bar">
    <span class="barrage-amount">{{$parent.barrageOnlineCount}}人正在看</span>
    <div class="barrage-switch">
      <w-switch :value="barrage" @change="ShowBarrage"></w-switch>
    </div>
    <!--弹幕设置-->
    <div v-show="menu" class="barrage-menu">
      <p class="barrage-menu-title">弹幕颜色</p>
      <div class="color-btn">
        <div @click="SetColor('#808080')"></div>
        <div @click="SetColor('#e54256')"></div>
        <div @click="SetColor('#ffe133')"></div>
        <div @click="SetColor('#64DD17')"></div>
        <div @click="SetColor('#39ccff')"></div>
        <div @click="SetColor('#D500F9')"></div>
      </div>
      <p class="barrage-menu-title">弹幕类型</p>
      <div class="barrage-type">
        <ul class="type-switch">
          <li class="type-item" :class="{'active':barrageFrom.type === 0}" @click="SetType(0)">滚动</li>
          <li class="type-item" :class="{'active':barrageFrom.type === 1}" @click="SetType(1)">顶部</li>
          <li class="type-item" :class="{'active':barrageFrom.type === 2}" @click="SetType(2)">底部</li>
        </ul>
      </div>
      <p class="barrage-menu-title">弹幕不透明度</p>
      <v-slider class="opacity" v-model="opacity" :max="100" @change="SetOpaque()"/>
    </div>
    <div class="barrage-setting" @click="menu = !menu">
      <svg-icon class="barrage-setting-icon" icon="setting"></svg-icon>
    </div>
    <input ref="send" class="input" v-model="barrageFrom.text" placeholder="发个友善的弹幕见证当下"/>
    <w-button :disabled="!barrage" class="send-btn" @click="Send()">发送</w-button>
  </div>
</template>

<script>
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";
import WSwitch from "./BarrageSwitch.vue";
import svgIcon from "./BarrageIcon.vue";
import WButton from "./BarrageButton.vue";
export default {
  data(){
    return{
      barrageFrom:{
        text:"",
        color:"#fff",
        type:0,
      },
      menu:false,
      opacity:100,
      barrage:true,
    }
  },
  methods:{
    SetType(type){
      this.barrageFrom.type = type;
    },
    //设置弹幕颜色
    SetColor(color) {
      this.barrageFrom.color = color;
      this.$refs.send.style.color = color;
    },
    //设置弹幕不透明度
    SetOpaque(){
      this.$parent.SetOpaque(this.opacity);
    },
    //开启或关闭弹幕
    ShowBarrage(val){
      this.barrage = val;
      this.$parent.ShowBarrage(val);
      this.$parent.playerConfig = { key: "barrage", value: val };
    },
    //发送弹幕
    Send(){
      if(this.barrageFrom.text === ""){
        this.$parent.ShowMessage("弹幕内容不能为空");
        return;
      }
      this.$parent.Send(this.barrageFrom, true);
      this.barrageFrom.text = "";
      this.menu = false;
    }
  },
  components: {
    "w-switch": WSwitch,
    "svg-icon": svgIcon,
    "w-button": WButton,
    "v-slider": VueSlider,
  },
  created(){
    this.ShowBarrage(this.$parent.playerConfig.barrage);
  }
};
</script>

<style scoped>
.bottom-bar {
  position: relative;
  background: #fff;
  display: flex;
  width: 100%;
  height: 50px;
  align-items: center;
  border-bottom: 1px solid #ebebeb;
}

.barrage-amount {
  padding-left: 12px;
  text-align: left;
  font-size: 12px;
  color: #999999;
  line-height: 40px;
  width: 120px;
}

.barrage-switch {
  width: 46px;
}

.barrage-setting{
  width: 25px;
  height: 25px;
  margin: 0 16px;
  cursor: pointer;
}

.barrage-setting-icon {
  width: 25px;
  height: 25px;
}

.input {
  height: 33px;
  width: calc(100% - 200px);
  border: 0;
  outline: none;
  padding: 0 2px;
  border-radius: 6px;
  background: #ebebeb;
  font-size: 12px;
}

.send-btn {
  width: 90px;
  height: 33px;
  font-size: 12px;
  margin-left: 10px;
}

.barrage-menu {
  position: absolute;
  z-index: 20;
  bottom: 60px;
  background: rgba(12, 12, 12, 0.8);
  width: 240px;
  /*height: 240px;*/
  margin-left: 90px;
}

.barrage-menu-title {
  color: #fff;
  margin: 12px 0 12px 10px;
}

.color-btn {
  display: flex;
  flex-wrap: nowrap;
}

.color-btn>div {
  width: 30px;
  height: 30px;
  margin: 0 0 5px 8px;
  border-radius: 50%;
  cursor: pointer;
}

.color-btn>div:nth-child(1) {
  background-color: white;
}

.color-btn>div:nth-child(2) {
  background-color: #e54256;
}

.color-btn>div:nth-child(3) {
  background-color: #ffe133;
}

.color-btn>div:nth-child(4) {
  background-color: #64dd17;
}

.color-btn>div:nth-child(5) {
  background-color: #39ccff;
}

.color-btn>div:nth-child(6) {
  background-color: #d500f9;
}

/**切换弹幕类型 */
.barrage-type{
  margin-left: 16px;
}

.type-switch{
  padding: 0;
  margin: 0;
  display: flex;
  list-style: none;
  width: 200px;
  border: 1px solid #fff;
  border-radius: 6px;
  overflow: hidden;
}

.type-item{
  flex: 1;
  color: #fff;
  padding: 10px;
  text-align: center;
  padding: 6px 6px;
}

.active{
  transition: all .3s;
  background-color: #1890ff;
}

.opacity{
  width: 90%!important; 
  margin: 0 auto;
}
.opacity:hover {
  cursor: pointer;
}
.barrage-type:hover {
  cursor: pointer;
}
</style>