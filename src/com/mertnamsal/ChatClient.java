package com.mertnamsal;
import java.net.DatagramSocket;

public class ChatClient {

	public static void main(String args[]) throws Exception {
			
		ClientWindow window = new ClientWindow();
		String host = window.getHostName();
		String nickname = window.getNickname();
		window.setTitle("UDP CHAT - "+nickname);
		DatagramSocket socket = new DatagramSocket();
		MessageReceiver receiver = new MessageReceiver(socket, window,nickname);
		MessageSender sender = new MessageSender(socket, host, window,nickname);
		Thread receiverThread = new Thread(receiver);
		Thread senderThread = new Thread(sender);
		receiverThread.start();
		senderThread.start();
	}
}