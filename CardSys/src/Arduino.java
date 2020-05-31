import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino {

	public String ler(){
		try {
			SerialPort comPort = SerialPort.getCommPort("COM5");
			comPort.openPort();
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			InputStream in = comPort.getInputStream();
			String res = "";
			for (int i = 0; i < 10; i++) {
				res += (char) in.read();
				Thread.sleep(10);
			}
			in.close();
			comPort.closePort();
			return res;
		} catch (Exception e) {return "";}
	}
}
