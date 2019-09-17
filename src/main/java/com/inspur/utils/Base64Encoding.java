package com.inspur.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Encoding 
{
	public static String base64(byte[] data)
	{
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(data);
	}
	
	//生成图片
		public static boolean BytesToImage(String imgStr,String imgFilePath)
		{
			if (imgStr == null) //图像数据为空 
			      return false; 
			    BASE64Decoder decoder = new BASE64Decoder(); 
			    try 
			    { 
			    	//Base64解码 
				      byte[] b = decoder.decodeBuffer(imgStr); 
				      for(int i=0;i<b.length;++i) 
				      { 
				        if(b[i]<0) 
				        {//调整异常数据 
				          b[i]+=256; 
				        } 
				      } 
				      OutputStream out = new FileOutputStream(imgFilePath);   
				      out.write(b); 
				      out.flush(); 
				      out.close(); 
				      return true; 
			    }catch(Exception e)
			    {
			    	return false;
			    }
			    
		}
	
}
