
import protobuf from '../proto/proto.js'

function clientBaseBarrageMsgSend (lookupType, barrageData, msgType) {
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
  return encodeData;
}


export default {
  clientBaseBarrageMsgSend,
}
