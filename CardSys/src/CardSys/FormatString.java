package CardSys;

import javax.swing.text.MaskFormatter;

public class FormatString {
	public String fs(String value, String pattern) {
		try {
	        MaskFormatter mf;
	        mf = new MaskFormatter(pattern);
	        mf.setValueContainsLiteralCharacters(false);
	        return mf.valueToString(value);
		}catch(Exception e){
			return value;
		}
    }
}
