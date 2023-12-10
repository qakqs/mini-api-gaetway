package com.api.session.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.api.session.BaseHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionServerHandler extends BaseHandler<FullHttpRequest> {
    private final Logger log = LoggerFactory.getLogger(SessionServerHandler.class);
    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        log.info("网关接受请求 uri:{} method:{}",request.uri(), request.method());
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.content().writeBytes(JSON.toJSONBytes("网关已被代理URI:"+request.uri(), SerializerFeature.PrettyFormat));

        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_TYPE, response.content().readableBytes());

    }
}
