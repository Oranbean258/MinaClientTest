package com.ex.net.transform;


import com.ex.net.client.MapleClient;
import com.ex.net.utils.ByteUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class NetTransform {
    private static NetTransform instance = null;
    public static NetTransform getInstance() {
        if (instance == null) {
            instance = new NetTransform();
        }
        return instance;
    }

    private float[] lastMyPosition = new float[2];
    private float[] otherPosition = new float[2];

    public void sendMyPosition(){
        IoSession ioSession = MapleClient.getInstance().getSession();
        ioSession.write(IoBuffer.wrap(ByteUtil.floatArrayToByteArray(lastMyPosition)));
    }

    public void update(){
        if(lastMyPosition != MapleClient.getInstance().getMapleCharacter().getPosition()){
            lastMyPosition = MapleClient.getInstance().getMapleCharacter().getPosition();
            sendMyPosition();
        }

    }

    public float[] getOtherPosition() {
        return otherPosition;
    }
    public void setOtherPosition(float[] otherPosition) {
        this.otherPosition = otherPosition;
    }
}
