package com.ex.net.mina.codec;

import com.ex.net.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.util.Arrays;

@Slf4j
public class TestEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput out) throws Exception {
        byte[] input = (byte[])message;
        log.info(Arrays.toString(input));
        float[] array = ByteUtil.byteArrayToFloatArray(input);
        log.info(Arrays.toString(array));
        IoBuffer buffer = IoBuffer.allocate(input.length);
        buffer.setAutoExpand(true);
        buffer.putInt(input.length);
        buffer.put(input);
        buffer.flip();
        log.info("encode-----------------------");
        out.write(buffer);
    }
}
