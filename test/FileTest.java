package com.huangjin.fileop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) {
		String filename = "F:\\test\\JDK_API_1_6_zh_CN.CHM";
		String filespath = "F:\\test";
		File file = new File(filename);
		try {
			splitFile(file,filespath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 需求：一个100M以下的文件 并且大于10M 分割成若干个文件 分析： 输入是个大文件 输出是多个文件 file 需要处理的大文件
	 * filespath 保存分割文件的文件夹路径
	 * 
	 * @throws IOException
	 */
	public static void splitFile(File file, String filespath) throws IOException {
		long len = 1024 * 1024 * 8;// 当前每个文件分为8M
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int dot = file.getName().lastIndexOf(".");
		String str = file.getName().substring(0, dot);// 获取文件名后缀名前的字符串
		System.out.println(str);
		if (file.exists() && file.isFile()) {// 判断是否存在 是否为文件
			long size = file.length();
			if (size > 1024 * 1024 * 8 && size < 1024 * 1024 * 100) {
				int index = (int) (size / len) + 1;// 指定应该拆分的文件个数 肯定大于2
				byte data[] = new byte[1024 * 1024];
				int n = 0;
				for (int i = 0; i < index - 1; i++) {
					File tempfile = new File(filespath + File.separator + str + i + ".hj");
					tempfile.createNewFile();
					FileOutputStream fos = new FileOutputStream(tempfile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					for (int j = 0; j < len / (1024 * 1024); j++) {
						n = bis.read(data, 0, data.length);
						bos.write(data, 0, n);
						bos.flush();
					}
					bos.close();
				}
				File lastfile = new File(filespath + File.separator + str + (index - 1) + ".hj");
				lastfile.createNewFile();
				FileOutputStream fos = new FileOutputStream(lastfile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				while ((n = bis.read(data, 0, data.length)) > 0) {
					bos.write(data, 0, n);
					bos.flush();
				}
				bos.close();
			}
		}
	}
}
