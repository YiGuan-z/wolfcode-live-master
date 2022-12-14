package cn.wolfcode.core.service;

import cn.wolfcode.core.netty.proto.BarrageProto;
import io.netty.channel.ChannelHandlerContext;

public interface IBarrageMsgTypeService {
    /**
     * 处理上送的弹幕信息
     *
     * @param barrage {@link BarrageProto}
     * @param ctx     通道上下文信息
     */
    void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx);
}
