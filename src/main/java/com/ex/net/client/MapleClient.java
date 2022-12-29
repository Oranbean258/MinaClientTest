package com.ex.net.client;

import com.ex.net.mina.MinaClientHandler;
import com.ex.net.mina.codec.TestCodecFactory;
import com.ex.net.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class MapleClient {
    private static MapleClient instance = null;

    private static int serverPort = 8080;
    private static String serverHost = "127.0.0.1";

    private MapleCharacter mapleCharacter;

    private IoSession session;

    public static MapleClient getInstance() {
        if (instance == null) {
            instance = new MapleClient();
        }
        return instance;
    }

    public void init() {
        ThreadManager.getInstance().start();
        try {
            IoConnector connector = new NioSocketConnector();
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TestCodecFactory()));
            connector.setHandler(new MinaClientHandler());
            ConnectFuture connectFuture = connector.connect(new InetSocketAddress(serverHost, serverPort));
            connectFuture.awaitUninterruptibly();
            log.info("Client init over!");

            this.session = connectFuture.getSession();
            test111();
        }catch (Exception e) {
            log.info("wrong connect to "+serverHost+":"+serverPort+" at:"+e);
        }
    }
    private void test111(){
        float[] ttt = {1.0f,2.0f,3.0f};
        session.write(ByteUtil.floatArrayToByteArray(ttt));
    }

    public static String getServerHost() {
        return serverHost;
    }
    public static int getServerPort() {
        return serverPort;
    }
    public static void setServerPort(int sp) {
        serverPort = sp;
    }
    public static void setServerHost(String host){
        serverHost = host;
    }

    public MapleCharacter getMapleCharacter() {
        return mapleCharacter;
    }
    public void setMapleCharacter(MapleCharacter mapleCharacter) {
        this.mapleCharacter = mapleCharacter;
    }
    public IoSession getSession() {
        return session;
    }
    private void setSession(IoSession session) {
        this.session = session;
    }
}
