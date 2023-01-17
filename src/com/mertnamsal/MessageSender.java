package com.mertnamsal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

class MessageSender implements Runnable {
	public final static int PORT = 5000;
	private DatagramSocket socket;
	private String hostName;
	private ClientWindow window;
	private String nickname;

	public MessageSender(DatagramSocket sock, String host, ClientWindow win,String nick) {
		socket = sock;
		hostName = host;
		window = win;
		nickname=nick;
	}

	private void sendMessage(String s) throws Exception {
		
		byte buffer[] = (s).getBytes();
		InetAddress address = InetAddress.getByName(hostName);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
		socket.send(packet);
	}

	public void run() {
		boolean connected = false;
		do {
			try {
				sendMessage(nickname+" connected - welcome!");
				connected = true;
			} catch (Exception e) {
				window.displayMessage(e.getMessage());
			}
		} while (!connected);
		while (true) {
			try {
				while (!window.message_is_ready) {
					Thread.sleep(100);
				}
				LocalTime now = LocalTime.now();
				String time = now.getHour()+":"+now.getMinute()+":"+now.getSecond();
				String messg = time+" || "+nickname+" : "+window.getMessage();  //
				sendMessage(messg);
				window.setMessageReady(false);
			} catch (Exception e) {
				window.displayMessage(e.getMessage());
			}
		}
	}
}