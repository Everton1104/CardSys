import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

	public static void main(String[] args) {
		SerialPort comPort = SerialPort.getCommPorts()[0];
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		InputStream in = comPort.getInputStream();
		try
		{
			while(true){//manter o projeto aberto.
				for(int i=0; i <10; i++) {
					//chamar classes a pertir desta.
				}
				in.close();
			}
		} catch (Exception e) { e.printStackTrace(); }
		comPort.closePort();
	}
}
