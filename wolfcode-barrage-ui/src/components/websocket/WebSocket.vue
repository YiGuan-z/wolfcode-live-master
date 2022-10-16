<template>
  <div>

  </div>
</template>


<script>

import protobuf from '../../proto/proto.js'
import config from "../../config";

export default {
  name: 'WebSocket',
  data() {
    return {
      websocket: null,
    }
  },
  created() {
    this.initWebSocket();
  },
  destroyed() {
    this.websocket.close()
  },
  methods: {
    initWebSocket() {
      const wsUri = config.wsUri;
      // console.log("this.websocket.state ", this.websocket.state)
      // if (!this.websocket.state === "Open") {
      this.websocket = new WebSocket(wsUri);
      this.websocket.binaryType = config.wsBinaryType;
      this.websocket.onmessage = this.webSocketMessage;
      this.websocket.onopen = this.webSocketOpen;
      this.websocket.onerror = this.webSocketError;
      this.websocket.onclose = this.webSocketClose;
      // }
    },
    webSocketOpen: function () {
      console.log("---------------WebSocket连接成功--------------")
      this.heartBeatSend();
      this.clientLoginSend();
      this.clientBarrageMsgHistorySend();
      this.clientBarrageMsgRollingSend();
    },

    webSocketError() {

    },

    webSocketMessage: function (event) {
      let barrage = protobuf.lookup("framework.Barrage");
      const barrageDecode = barrage.decode(new Uint8Array(event.data))
      if ('web.client.barrage.resp' === barrageDecode.msgType) {
        let WebClientSendBarrage = protobuf.lookup("framework.WebClientSendBarrageResp");
        let decode = WebClientSendBarrage.decode(barrageDecode.bytesData);
        let decodeJson = {
          text: decode.msg,
          color: decode.msgColor,
          type: decode.msgPosition,
        }
        if ((this.$parent.showBarrage)) {
          this.$parent.Draw(decodeJson, false);
        }

      } else if ('web.client.heartbeat.resp' === barrageDecode.msgType) {
        let WebClientHeartBeatResp = protobuf.lookup("framework.WebClientHeartBeatResp");
        let decode = WebClientHeartBeatResp.decode(barrageDecode.bytesData);
        this.$parent.barrageTotalCount = decode.barrageTotalCount;
        this.$parent.barrageTotalWatchCount = decode.barrageTotalWatchCount;
        this.$parent.barrageOnlineCount = decode.barrageOnlineCount;

      } else if ('web.client.login.resp' === barrageDecode.msgType) {
        let WebClientLoginResp = protobuf.lookup("framework.WebClientLoginResp");
        let decode = WebClientLoginResp.decode(barrageDecode.bytesData);
        this.$parent.barrageTotalCount = decode.barrageTotalCount;
        this.$parent.barrageTotalWatchCount = decode.barrageTotalWatchCount;
        this.$parent.barrageOnlineCount = decode.barrageOnlineCount;
        this.$parent.videoTitle = decode.videoTitle;
        this.$parent.videoCreateTime = decode.videoCreateTime;

      } else if ('web.client.barrage.history.resp' === barrageDecode.msgType) {
        let WebClientBarrageHistoryListResp = protobuf.lookup("framework.WebClientBarrageHistoryListResp");
        let decode = WebClientBarrageHistoryListResp.decode(barrageDecode.bytesData);
        this.$parent.barrageHistoryList = decode.list;

      } else if ('web.client.barrage.msg.rolling.resp' === barrageDecode.msgType) {
        let WebClientBarrageHistoryListResp = protobuf.lookup("framework.WebClientBarrageMsgRollingResp");
        let decode = WebClientBarrageHistoryListResp.decode(barrageDecode.bytesData);
          for (let index of decode.list) {
            let decodeJson = {
              text: index.msg,
              color: index.msgColor,
              type: index.msgPosition,
            }
            console.log(decodeJson)
            if (this.$parent.showBarrage && !this.$parent.pause) {
              this.$parent.Draw(decodeJson, false);
            }
          }
      }
    },
    webSocketSend: function (Data) {
      if (this.websocket.readyState === 1) {
        this.websocket.send(Data);
      }
    },

    webSocketClose(e) {
      console.log(e)
      console.log("---------------开始进行断网重连--------------")
      setTimeout(this.initWebSocket, 5000);
    },

    heartBeatSend: function () {
      const data = {
        userId: config.userId,
        videoId: config.videoId,
      }
      this.clientBaseBarrageMsgSend("framework.WebClientHeartBeatReq", data, "web.client.heartbeat.req");
      setTimeout(this.heartBeatSend, 5000);
    },

    clientLoginSend: function () {
      const data = {
        userId: config.userId,
        videoId: config.videoId,
      }
      this.clientBaseBarrageMsgSend("framework.WebClientLoginReq", data, "web.client.login.req");
    },

    clientBarrageSend: function (event) {
      const data = {
        userId: config.userId,
        videoId: config.videoId,
        msg: event.text,
        msgColor: event.color,
        msgPosition: event.type,
        msgVideoTime: parseInt(event.currentTime),
      }
      this.clientBaseBarrageMsgSend("framework.WebClientSendBarrageReq", data, "web.client.barrage.req");
    },

    clientBarrageMsgHistorySend: function () {
      const data = {
        videoId: config.videoId,
      }
      this.clientBaseBarrageMsgSend("framework.WebClientBarrageHistoryListReq", data, "web.client.barrage.history.req");
    },

    clientBarrageMsgRollingSend: function () {
      const data = {
        userId: config.userId,
        videoId: config.videoId,
        currentVideoTime: parseInt(this.$parent.currentTime),
      }
      this.clientBaseBarrageMsgSend("framework.WebClientBarrageMsgRollingReq", data, "web.client.barrage.msg.rolling.req");
    },

    clientBaseBarrageMsgSend: function (lookupType, barrageData, msgType) {
      let barrage = protobuf.lookup("framework.Barrage");
      let baseLookupType = protobuf.lookup(lookupType);
      let data = baseLookupType.create(barrageData);
      let sendData = baseLookupType.encode(data).finish();
      const baseData = {
        msgType: msgType,
        bytesData: sendData
      }
      let createData = barrage.create(baseData);
      let encodeData = barrage.encode(createData).finish();
      this.webSocketSend(encodeData);
    },

  },
}
</script>
<style lang='less'>

</style>
