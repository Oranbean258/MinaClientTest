package com.ex.net.mina;

import com.ex.net.client.MapleClient;
import com.ex.net.transform.NetTransform;
import com.ex.net.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

import java.util.Arrays;


@Slf4j
public class MinaClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        log.info("Connected to server: "+ioSession.getRemoteAddress()+" ...");
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        log.info("Connected closed!!!");
        ioSession.closeNow();
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        log.info("wrong session at: "+throwable);
    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        IoBuffer ioBuffer = (IoBuffer) o;
        byte[] msg = new byte[ioBuffer.limit()];
        ioBuffer.get(msg);
        float[] array = ByteUtil.byteArrayToFloatArray(msg);
        log.info(Arrays.toString(array));
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {
//        IoBuffer ioBuffer = (IoBuffer) o;
//        byte[] msg = new byte[ioBuffer.limit()];
//        ioBuffer.get(msg);
//        float[] array = ByteUtil.byteArrayToFloatArray(msg);
//        log.info("send msg to "+ioSession.getRemoteAddress()+" "+ Arrays.toString(array)+" ...");
    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void event(IoSession ioSession, FilterEvent filterEvent) throws Exception {

    }
}
