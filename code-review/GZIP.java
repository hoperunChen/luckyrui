/**
 * 
 */
package com.longdai.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <p>GZIP方式的解压缩</p>
 * @author chenshaorong
 * @version 1.0.0 
 */
public class GZIP {

	/**
	 * 压缩
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public byte[] compress(byte[] data) throws Exception {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(baos);
        gos.write(data, 0, data.length);
        gos.finish();
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        return output;
    }
	
	/**
	 * 解压
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public byte[] uncompress(byte[] data) throws Exception { 
		BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));
        GZIPInputStream gos = new GZIPInputStream(bis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int count;
		byte data1[] = new byte[1024];
		while ((count = gos.read(data1, 0, 1024)) != -1) {
			baos.write(data1, 0, count);
		}
		bis.close();
        return baos.toByteArray();
    }
}
