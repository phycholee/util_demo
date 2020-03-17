package com.llf.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/12/11 23:06
 */
public class TimeServer {

    public static void main(String[] args) {
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(8080);

        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}

class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port){
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is start in port: " + port);
        } catch (Exception e){
          e.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {

        while (!stop){

            try{
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> it = selectionKeys.iterator();

                SelectionKey key = null;

                while (it.hasNext()){

                    key = it.next();
                    it.remove();

                    try{
                        handleInput(key);
                    } catch (Exception e){
                        if (key != null){
                            key.cancel();
                            if (key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }


            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if (selector != null){
            try{
                selector.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {

        if (key.isValid()){
            //处理新接入的请求消息
            if (key.isAcceptable()){

                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                int read = sc.read(readBuffer);
                if (read > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);

                    System.out.println("The time server receive order : " + body);

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";

                    doWrite(sc, currentTime);
                } else if (read < 0){
                    key.cancel();
                    sc.close();
                }

            }

        }

    }

    private void doWrite(SocketChannel channel, String response) throws IOException {

        if (response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }

    }
}
