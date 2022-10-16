/*eslint-disable block-scoped-var, id-length, no-control-regex, no-magic-numbers, no-prototype-builtins, no-redeclare, no-shadow, no-var, sort-vars*/
"use strict";

var $protobuf = require("protobufjs/light");

var $root = ($protobuf.roots["default"] || ($protobuf.roots["default"] = new $protobuf.Root()))
.addJSON({
  framework: {
    nested: {
      Barrage: {
        fields: {
          msgType: {
            type: "string",
            id: 1
          },
          bytesData: {
            type: "bytes",
            id: 2
          }
        }
      },
      WebClientHeartBeatResp: {
        fields: {
          barrageTotalCount: {
            type: "int32",
            id: 1
          },
          barrageOnlineCount: {
            type: "int32",
            id: 2
          },
          barrageTotalWatchCount: {
            type: "int32",
            id: 4
          }
        }
      },
      WebClientHeartBeatReq: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          }
        }
      },
      WebClientSendBarrageReq: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          },
          msg: {
            type: "string",
            id: 3
          },
          msgColor: {
            type: "string",
            id: 4
          },
          msgPosition: {
            type: "int32",
            id: 5
          },
          msgVideoTime: {
            type: "int32",
            id: 6
          }
        }
      },
      WebClientSendBarrageResp: {
        fields: {
          msg: {
            type: "string",
            id: 1
          },
          msgColor: {
            type: "string",
            id: 2
          },
          msgPosition: {
            type: "int32",
            id: 3
          }
        }
      },
      WebClientLoginReq: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          }
        }
      },
      WebClientLoginResp: {
        fields: {
          barrageTotalCount: {
            type: "int32",
            id: 1
          },
          msgColorList: {
            rule: "repeated",
            type: "string",
            id: 2
          },
          barrageOnlineCount: {
            type: "int32",
            id: 3
          },
          barrageTotalWatchCount: {
            type: "int32",
            id: 4
          },
          videoTitle: {
            type: "string",
            id: 5
          },
          videoCreateTime: {
            type: "string",
            id: 6
          }
        }
      },
      WebClientLogoutReq: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          }
        }
      },
      BarrageHistoryMessage: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          },
          msg: {
            type: "string",
            id: 3
          },
          msgColor: {
            type: "string",
            id: 4
          },
          msgPosition: {
            type: "int32",
            id: 5
          },
          sendTime: {
            type: "string",
            id: 6
          },
          createTime: {
            type: "string",
            id: 7
          }
        }
      },
      WebClientBarrageHistoryListReq: {
        fields: {
          videoId: {
            type: "string",
            id: 1
          }
        }
      },
      WebClientBarrageHistoryListResp: {
        fields: {
          list: {
            rule: "repeated",
            type: "BarrageHistoryMessage",
            id: 1
          }
        }
      },
      WebClientBarrageMsgRollingReq: {
        fields: {
          userId: {
            type: "string",
            id: 1
          },
          videoId: {
            type: "string",
            id: 2
          },
          currentVideoTime: {
            type: "int32",
            id: 3
          }
        }
      },
      WebClientBarrageMsgRollingResp: {
        fields: {
          list: {
            rule: "repeated",
            type: "BarrageHistoryMessage",
            id: 1
          }
        }
      }
    }
  }
});

module.exports = $root;
