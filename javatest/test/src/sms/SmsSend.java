package sms;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsSend extends JFrame implements ActionListener{
	
	private Container c;
	private JLabel la1, la2;
	private JTextField tf1, tf2;
	private JButton btn1, btn2;
	
	static void 문자전송(String to, String text) {
		String api_key = "NCSHBCRTWCIETZ3M";
	    String api_secret = "ROMRVRND4R4XE7GSIPX7DA4CVRNIKRSU";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);
	    params.put("from", "01030552463");
	    params.put("type", "SMS");
	    params.put("text", text);
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	}
	
	public void initObject() {
		la1 = new JLabel("메시지");
		tf1 = new JTextField();
		la2 = new JLabel("전화번호");
		tf2 = new JTextField();
		btn1 = new JButton("보내기");
		btn2 = new JButton("초기화");
	}
	
	public void initSetting() {
		c = getContentPane();
		GridLayout grid = new GridLayout(3,2);
		grid.setVgap(5);
		c.setLayout(grid);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}
	
	public void initBatch() {
		c.add(la1);
		c.add(tf1);
		c.add(la2);
		c.add(tf2);
		c.add(btn1);
		c.add(btn2);
	}
	public SmsSend() {
		setTitle("메시지 보내기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		
		initObject();
		
		initSetting();
		
		initBatch();
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SmsSend();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("보내기")) {
			문자전송(tf2.getText(), tf1.getText());
		} else {
			tf1.setText("");
			tf2.setText("");
		}
		
	}
}