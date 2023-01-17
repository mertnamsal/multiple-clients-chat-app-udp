package com.mertnamsal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

class MessageReceiver implements Runnable {
	DatagramSocket socket;
	byte buffer[];
	ClientWindow window;
	String nickname;

	MessageReceiver(DatagramSocket sock, ClientWindow wind,String nick) {
		socket = sock;
		buffer = new byte[155];
		window = wind;
		nickname=nick;
	}

	public void run() {
		while (true) {
			try {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 1, packet.getLength() - 1).trim();
				window.displayMessage(received);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
