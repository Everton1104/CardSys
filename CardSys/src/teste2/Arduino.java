package teste2;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino {

	public String ler(){
		try {
			SerialPort comPort = SerialPort.getCommPorts()[0];
			comPort.setBaudRate(9600);
			comPort.openPort();
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			InputStream in = comPort.getInputStream();
			String res = "";
			for(int i = 0; i < 8; i++){
				res += (char)in.read();
			}
			in.close();
			comPort.closePort();
			System.out.println("Arduino: "+res);
			return res;
		} catch (Exception e) {return "erro";}
	}
}
