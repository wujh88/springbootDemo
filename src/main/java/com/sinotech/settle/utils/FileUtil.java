package com.sinotech.settle.utils;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.sinotech.settle.config.ConfigConsts;
import com.sinotech.settle.exception.CommonIOException;
import com.sinotech.settle.exception.ServiceException;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;


/**
 * 文件操作工具类
 * 
 * @author zx
 * 
 */
@SuppressWarnings("rawtypes")
@Component
public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);

	@Autowired
	private FastFileStorageClient storage;
	// 图片服务器的封装接口
	private static FastFileStorageClient storageClient;

	@PostConstruct
	public void init(){
		storageClient = storage;
	}


	/**
	 * 文件上传
	 * 
	 * @param request
	 *            请求对象
	 * @param path
	 *            上传文件保存路径
	 * @throws Exception
	 */
	public static void fileUpLoad(HttpServletRequest request, String path,
                                  int fileMaxSize) throws Exception {
		try {
			File file = new File(path);
			// 如果路径不存在就创建一个路径
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			// 创建一个DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置上传文件大小最大为
			upload.setSizeMax(fileMaxSize);
			// 设置上传文件编码
			upload.setHeaderEncoding("UTF-8");
			List fileItems;
			fileItems = upload.parseRequest(new ServletRequestContext(request));
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					// 获取上传文件名
					String name = item.getName();
					item.write(new File(path
							+ name.substring(name.lastIndexOf("\\") + 1)));
					item.delete();
				}
			}
			// 文件超过最大限制
		} catch (FileSizeLimitExceededException e1) {
			throw new ServiceException("上传文件过大");
			// 找不到上传路径
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			throw new ServiceException("上传路径不存在");
		} 
	}
	/**
	 * MultipartFile 上传
	 * @param file 源文件流
	 * @param filePath 要存放的地址
	 * @param fileName 文件名字
	 * @throws Exception 
	 * @throws Exception
	 */
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{ 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out=null;
		try {
			out = new FileOutputStream(filePath+fileName);
			out.write(file);
	        out.flush();
	        out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceException("上传路径不存在");
		} finally{
			if(out!=null){
				out.close();
			}
		}
    }
	/**
	 * 
	 * @param
	 * @param path 要下载得文件路径
	 * @param fileName 要下载的文件名字
	 * @throws Exception 
	 */
	public static void DownloadByPath(HttpServletResponse response,
		String path, String fileName) throws Exception {
		//输出流
		ServletOutputStream out = response.getOutputStream();
		//输入流
		FileInputStream fileInputStream = null;
		File file= new File((path + fileName));
		if (!file.exists()) {
			throw new ServiceException("文件不存在");
		}
		try {
			// 读取文件流
			fileInputStream = new FileInputStream(file);
			if (fileName != null && fileName.length() > 0) {
				response.setContentType("application/x-msdownload");
				response.reset();
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fileName, "UTF-8"));
				if (fileInputStream != null) {
					byte[] b = new byte[1024];
					int i = 0;
					while ((i = fileInputStream.read(b)) > 0) {
						out.write(b, 0, i);
					}
					out.flush();
				}
				fileInputStream.close();
				out.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fileInputStream != null){
				fileInputStream.close();
			}
		}

	}


	/**
	 * FastDFS 全路径转短路径
	 * @param fullpath
	 * @return
	 */
	public static String fullPathToShortPath(String fullpath){
        // 图片服务器ip路径
		String server = ConfigConsts.FASTDFS_NGINX_SERVER;
		// 分组路径，FASTDFS_DEFAULT_GROUP的值为group1
		String group = ConfigConsts.FASTDFS_DEFAULT_GROUP;
		// 字符串fullpath中的（server+group+"/"）替换为""。
		return fullpath.replace(server+group+"/","");
	}

	/**
	 * Description: 根据不同浏览器处理中文字符
	 * @param request
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeChineseDownloadFileName(HttpServletRequest request, String fileName)
			throws UnsupportedEncodingException {
		String resultFileName;
		String agent = request.getHeader("User-Agent").toUpperCase();
		if(null == agent){
			resultFileName = fileName;
		}else{
			//firefox, chrome
			if(agent.contains("FIREFOX") || agent.contains("CHROME")){
				resultFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}else{  //ie7+
				resultFileName =  URLEncoder.encode(fileName, "UTF-8");
				//替换空格
				resultFileName = replace(resultFileName, "+", "%20");
			}
		}
		return resultFileName;
	}


	/**
	 * 上传文件到fastDFS服务器
	 * @param file
	 * @return
	 */
	public static Map<String,String> uploadFileToFastDFS(MultipartFile file){
		HashMap fileInfo = new HashMap();
		InputStream inputStream;
		//获取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.indexOf(".")+1);
		try {
			//获取上传文件的输入流
			inputStream = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错"+e.getMessage());
			throw new CommonIOException("获取上传文件出错");
		}
		//上传文件到fastDFS服务器
		StorePath storePath = storageClient.uploadFile(inputStream, file.getSize(), extName, null);
		fileInfo.put("fileName",originalFilename);
		fileInfo.put("fileSize",file.getSize());
		//文件的绝对路径
		fileInfo.put("filePath",ConfigConsts.FASTDFS_NGINX_SERVER + storePath.getFullPath());
		fileInfo.put("fileType",file.getContentType());
		fileInfo.put("extName",extName);
		closeStream(inputStream);
		return fileInfo;
	}

	public static void closeStream(InputStream inputStream) {
		if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                inputStream = null;
                e.printStackTrace();
            }
        }
	}
}
