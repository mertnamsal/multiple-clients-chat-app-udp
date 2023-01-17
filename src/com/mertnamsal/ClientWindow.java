package com.mertnamsal;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


@SuppressWarnings("serial")
public class ClientWindow extends JFrame {

	String host_name = "localhost";
	JTextPane message_field;
	JTextPane chat_field;
	String nickname;

	String message = "";
	boolean message_is_ready = false;

	public ClientWindow() {
		setBackground(new Color(41, 41, 41));
		setAlwaysOnTop(true);
		setForeground(Color.DARK_GRAY);
		getContentPane().setBackground(new Color(41, 41, 41));
		//girerken nickname sorma
		JDialog hostNameDialog = new JDialog(this, "Enter your nickname: ", true);
		JTextField hostField = new JTextField("                                ");
		JButton ok = new JButton("OK");
		hostNameDialog.getContentPane().setLayout(new FlowLayout());
		hostNameDialog.getContentPane().add(hostField);
		hostNameDialog.getContentPane().add(ok);
		hostNameDialog.setLocationRelativeTo(null);
		hostNameDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		hostNameDialog.setSize(250, 100);
		hostNameDialog.setResizable(false);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nickname = hostField.getText().trim();
				hostNameDialog.dispose();
			}
		});
		hostNameDialog.setVisible(true);
		
		setSize(800, 600);
		setTitle("UDP Chat room");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		chat_field = new JTextPane();
		chat_field.setForeground(Color.WHITE);
		chat_field.setFont(new Font("Tahoma", Font.BOLD, 13));
		chat_field.setBackground(new Color(0, 0, 0));
		message_field = new JTextPane();
		message_field.setForeground(Color.WHITE);
		message_field.setFont(new Font("Tahoma", Font.BOLD, 11));
		message_field.setBackground(Color.BLACK);
		chat_field.setEditable(false);
		ScrollPane chatscroll = new ScrollPane();
		chatscroll.setBackground(Color.WHITE);
		chatscroll.setBounds(38, 45, 679, 373);
		chatscroll.add(chat_field);
		ScrollPane messageScroll = new ScrollPane();
		messageScroll.setBounds(38, 451, 679, 76);
		messageScroll.add(message_field);
		messageScroll.setPreferredSize(new Dimension(100, 100));
		getContentPane().setLayout(null);
		getContentPane().add(chatscroll);
		getContentPane().add(messageScroll);
		
		JLabel lblNick = new JLabel("");
		lblNick.setForeground(Color.WHITE);
		lblNick.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNick.setBounds(38, 11, 334, 21);
		getContentPane().add(lblNick);
		lblNick.setText(nickname);

		setVisible(true);
		message_field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
			
				if (e.getKeyCode() == 10 && !message_is_ready) {
					message = message_field.getText().trim();
					message_field.setText(null);
					displayMessage(message);
					if (!message.equals(null) && !message.equals("")) {
						message_is_ready = true;
					}
				}
			}
		});
	}

	public void displayMessage(String receivedMessage) {
		StyledDocument doc = chat_field.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), "-"+receivedMessage + "\n", null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	public boolean isMessageReady() {
		return message_is_ready;
	}

	public void setMessageReady(boolean messageReady) {
		this.message_is_ready = messageReady;
	}

	public String getMessage() {
		return message;
	}

	public String getHostName() {
		return host_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}