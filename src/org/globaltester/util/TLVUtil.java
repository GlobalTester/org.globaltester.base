package org.globaltester.util;


public class TLVUtil {
	
	/**
	 * Return TAG as byte array of length 2 
	 * 
	 * @param bs
	 *            byte array 
	 * @param offset
	 *            offset in bs to get the tag
	 */
	public static byte[] getTag(byte[] bs, int offset) {
		
		byte tagNumber = bs[offset];
		if ((tagNumber & 0x7F) == 0x7F) {
			return new byte[] {bs[offset], bs[offset+1]};
		} else {
			return new byte[] {bs[offset]};
		}
	
	}
	
	/**
	 * Return length of TLV structure
	 * 
	 * @param bs
	 *            byte array 
	 * @param offset
	 *            offset in bs to get the length bytes
	 */
	public static int getLength(byte[] bs, int offset) {
		//ToDo
		
		return 0;
	}
	
	public static byte[] getValue(byte[] bs, int offset) {
		
		return new byte[] {bs[offset], bs[offset+1]};
	}
	
	public static byte getNext(byte[] bs) {
		byte value = 0;
		//ToDo
		
		return value;
	}
	

	/**
	 * Return length of TLV structure according to ICAO LDS TR
	 * 
	 * @param ba
	 * 			Byte array
	 * @return length
	 */

	public static int checkLengthEncoding(byte[] ba) {
		int k, length = 0;
		
		if (ba.length >= 3) {
			
			// Three byte length
			if (ba[1] == (byte) 0x82) {
				length = (int) ba[3];
				if (length < 0)
					length += 256;
				k = (int) ba[2];
				if (k < 0)
					k += 256;
				length += (k << 8);
				
			// Two byte length
			} else if (ba[1] == (byte) 0x81) {
				length = (int) ba[2];
				if (length < 0)
					length += 256;
				
			// One byte length
			} else {
				length = (int) ba[1];
					assert length < 0 : "Invalid length in TLV structure";	
			}
		} else {
			assert ba.length < 3 : "Invalid TLV structure";
		}
		return length;

	}
	

	/**
	 * Return length of hex bytes necessary to code the TLV structure with given length
	 * 
	 * @param length
	 * @return length
	 */

	public static int getSizeHelper(int length) {
		int len = 0;
		if (length >= 0 && length <= 127) len = 1;
		if (length >= 128 && length <= 255) len = 2;
		if (length >= 256 && length <= 65536) len = 3;
		return len;
	}

}

