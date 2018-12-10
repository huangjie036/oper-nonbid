package com.yyc.oper.nobid.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
public class YycFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(YycFileUtils.class);

	public static void download(String downloadName, String fileName, HttpServletResponse response) throws Exception {
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + new String(downloadName.getBytes("UTF-8"), "iso-8859-1"));
		try {
			String path = YycFileUtils.class.getResource("/templates/" + fileName).getPath();
			// 打开本地文件流
			InputStream inputStream = new FileInputStream(path);
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

	public static String uploadFile(CommonsMultipartFile file) throws Exception {
		String oldName = file.getOriginalFilename();// 得到原文件名
		String suffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());// 得到后缀名
		// 用当前时间毫秒生成新名字，预防重名
		String newName = System.currentTimeMillis() + suffix;
		// 根据日前分文件夹
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(new Date());
		String uploadPath = "/opt/apache-tomcat-jxfzb/upload" + File.separator + nowDate;
		try {
			File path = new File(uploadPath);
			// 判断文件夹是否存在，不存在则创建
			if (!path.exists()) {
				path.mkdir();
			}
			String realPath = uploadPath + File.separator + newName;// 文件上传的路径
			file.transferTo(new File(realPath));
			return realPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean delFile(String filePath) {
		// 删除文件
		File file = new File(filePath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.info("删除单个文件" + filePath + "完成");
			} else {
				logger.info("删除单个文件" + filePath + "失败");
				return false;
			}
		} else {
			logger.info("文件" + filePath + "不存在");
		}
		return true;
	}

	/**
	 * 压缩并导出文件
	 * 
	 * @param zipPath
	 *            压缩文件临时路径 路径最后不要有 /
	 * @param zipName
	 *            压缩为文件名 **.zip
	 * @param createFilesPath
	 *            需要压缩的文件列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadZip(String zipPath, String zipName, List<String> createFilesPath,
			HttpServletRequest request, HttpServletResponse response) {
		byte[] buffer = new byte[1024];
		String strZipPath = zipPath + "/" + zipName;
		try {
			File tmpZip = new File(zipPath);
			if (!tmpZip.exists())
				tmpZip.mkdirs();
			File tmpZipFile = new File(strZipPath);
			if (!tmpZipFile.exists())
				tmpZipFile.createNewFile();
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
			File[] file1 = new File[createFilesPath.size()];
			for (int i = 0; i < createFilesPath.size(); i++) {
				file1[i] = new File(createFilesPath.get(i));
			}
			for (int i = 0; i < file1.length; i++) {
				FileInputStream fis = new FileInputStream(file1[i]);
				out.putNextEntry(new ZipEntry(file1[i].getName()));
				// 设置压缩文件内的字符编码，不然会变成乱码
				//out.set("UTF-8");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();

			downloadFile(zipPath, zipName, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 以压缩文件导出
	 * 
	 * @param fileName
	 * @param filePath
	 * @param response
	 */
	public static void downloadFile(String filePath, String fileName, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			File file = new File(filePath, fileName);
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据文件大小转换为B、KB、MB、GB单位字符串显示
	 * 
	 * @param filesize
	 *            文件的大小（long型）
	 * @return 返回 转换后带有单位的字符串
	 */
	public static String GetLength(long filesize) {
		if(!YycStringUtils.isNotBlank(filesize)){
			return "0B";
		}
		String strFileSize = null;
		if (filesize < 1024) {
			strFileSize = filesize + "B";
			return strFileSize;
		}
		DecimalFormat df = new DecimalFormat("######0.00");
		if ((filesize >= 1024) && (filesize < 1024 * 1024)) {// KB
			strFileSize = df.format(((double) filesize) / 1024) + "KB";
		} else if ((filesize >= 1024 * 1024) && (filesize < 1024 * 1024 * 1024)) {// MB
			strFileSize = df.format(((double) filesize) / (1024 * 1024)) + "MB";
		} else {// GB
			strFileSize = df.format(((double) filesize) / (1024 * 1024 * 1024)) + "GB";
		}
		return strFileSize;
	}

}
