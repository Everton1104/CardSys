import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

	public static void main(String[] args){
		
		
		new Thread() {
			@Override
			public void run(){
				try {
					SerialPort comPort = SerialPort.getCommPort("COM5");
					comPort.openPort();
					comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
					InputStream in = comPort.getInputStream();
					boolean loop = true;
					while (loop) {
						String res = "";
						for (int i = 0; i < 10; i++) {
							res += (char) in.read();
							Thread.sleep(10);
						}
						in.close();
						System.out.println(res);
						res = "";
					}
					comPort.closePort();
				} catch (Exception e) {}
			}
		}.start();
		
		
		
	}
}
