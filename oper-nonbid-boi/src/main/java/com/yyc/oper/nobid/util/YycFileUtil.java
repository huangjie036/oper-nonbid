package com.yyc.oper.nobid.util;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class YycFileUtil {

	public static void download(String downloadName, String fileName, HttpServletResponse response) throws Exception {
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + new String(downloadName.getBytes("UTF-8"), "iso-8859-1"));
		try {
			// 打开本地文件流
			InputStream inputStream = YycFileUtil.class.getResourceAsStream("/templates/" + fileName);
			// 激活下载操作
			OutputStream os = response.getOutputStream();
			// 循环写入输出流
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (Exception e) {
			throw e;
		}
	}

}
