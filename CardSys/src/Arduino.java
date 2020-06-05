import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino {

	public String ler(){
		try {
			SerialPort comPort = SerialPort.getCommPorts()[0];
			comPort.setBaudRate(115200);
			comPort.openPort();
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			InputStream in = comPort.getInputStream();
			String res = "";
			for(int i = 0; i < 10; i++){
				res += in.read();
			}
			in.close();
			comPort.closePort();
			System.out.println(res);
			return res;
		} catch (Exception e) {return "erro";}
	}
}
