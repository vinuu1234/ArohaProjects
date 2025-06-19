package com.demo.aroha.day12;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Set;

public class NIOClientExample {
    public static void main(String[] args) {
        try {
            // 1. Open a SocketChannel (Connection)
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false); // Non-blocking mode

            // 2. Connect to Server (localhost:5000)
            socketChannel.connect(new InetSocketAddress("localhost", 6000));

            // 3. Open a Selector
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            // 4. Buffer to send/receive data
            ByteBuffer buffer = ByteBuffer.allocate(256);

            while (true) {
                selector.select(); // Blocking call, waits for an event (connect, read, write)

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (sc.isConnectionPending()) {
                            sc.finishConnect();
                            System.out.println("Connected to server!");
                        }
                    }

                    if (key.isWritable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        buffer.clear();
                        buffer.put("Hello Server!".getBytes());
                        buffer.flip();
                        sc.write(buffer);
                        System.out.println("Message sent to server.");
                        
                        // After writing, we don't need to write immediately again
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        buffer.clear();
                        int numRead = sc.read(buffer);
                        if (numRead > 0) {
                            System.out.println("Received from server: " + new String(buffer.array()).trim());
                        }
                        sc.close(); // Close after reading for this simple example
                        return;
                    }

                    keyIterator.remove(); // Important: Remove the handled key
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}