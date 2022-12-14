package cn.wolfcode.core.netty;

import cn.wolfcode.core.consts.BarrageWebSocketConst;
import cn.wolfcode.core.netty.proto.BarrageProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Desc 配置netty pipeline
 **/
public class WebSocketNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(BarrageWebSocketConst.MAX_CONTENT_LENGTH));
        pipeline.addLast(new IdleStateHandler(BarrageWebSocketConst.READER_IDLE_TIME, BarrageWebSocketConst.WRITE_IDLE_TIME, BarrageWebSocketConst.ALL_IDLE_TIM, TimeUnit.SECONDS));
        pipeline.addLast(new WebSocketServerProtocolHandler(BarrageWebSocketConst.WEB_SOCKET_PATH));
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new WebSocketNettyServerMessageDecoder());
        pipeline.addLast(new ProtobufDecoder(BarrageProto.Barrage.getDefaultInstance()));
        pipeline.addLast(new WebSocketNettyServerMessageEncoder());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new WebSocketNettyServerHandler());

    }
}
