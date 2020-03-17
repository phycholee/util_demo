package com.llf.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/12/12 23:11
 */
public class TimeClient {


    public static void main(String[] args) {
        new Thread(new TimeClientHandle("127.0.0.1", 8080), "NIO-TimeClient-001").start();
    }

}


class TimeClientHandle implements Runnable{

    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public TimeClientHandle(String host, int port){
        this.host = host;
        this.port = port;

        try {
            selector = Selector.open();

            socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stop){
            try {
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

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            //判断连接是否成功
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()){
                if (socketChannel.finishConnect()){
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                } else {
                    //连接失败，退出
                    System.exit(1);
                }
            }

            if (key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int read = socketChannel.read(readBuffer);
                if (read > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("Now is : " +  body);
                    this.stop = true;
                } else if (read < 0){
                    key.cancel();
                    socketChannel.close();
                }

            }
        }

    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))){

            socketChannel.register(selector, SelectionKey.OP_READ);

            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        if (!writeBuffer.hasRemaining()){
            System.out.println("Send order to server success");
        }

    }
}