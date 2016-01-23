package com.huangjin.fileop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {
	public static void main(String[] args) {
		String fiename = "huangjin.zip";
		String temp_path="F:\\music";
		List<File> list = new ArrayList<File>();
		File file = new File("F:\\music\\黄进.txt");
		list.add(file);
		createZip(fiename,temp_path,list);
	}
	/**
	 * 实现将多个文件进行压缩，生成指定目录下的指定名字的压缩文件 Parameters: filename ：指定生成的压缩文件的名称
	 * temp_path ：指定生成的压缩文件所存放的目录 list ：List集合：用于存放多个File（文件）
	 * */
	public static void createZip(String filename, String temp_path, List<File> list) {
		File file = new File(temp_path);
		System.err.println(temp_path);
		File zipFile = new File(temp_path + File.separator + filename);
		System.out.println(temp_path + File.separator + filename);
		InputStream input = null;
		try {
			// ZipOutputStream:此类为以 ZIP 文件格式写入文件实现输出流过滤器。包括对已压缩和未压缩条目的支持。
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

			/*
			 * setComment(String comment)设置 ZIP 文件注释。 参数： comment - 注释字符串
			 */
			zipOut.setComment(file.getName());
			if (file.isDirectory()) {
				for (int i = 0; i < list.size(); ++i) {
					input = new FileInputStream(list.get(i));
					System.out.println(i);
					zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + list.get(i).getName()));
					/*
					 * putNextEntry(ZipEntry e): 开始写入新的 ZIP
					 * 文件条目并将流定位到条目数据的开始处。如果仍处于活动状态，则关闭当前条目。
					 * 如果没有为条目指定压缩方法，则使用默认的压缩方法；如果没有为条目设置修改时间，则使用当前时间。
					 * 
					 * ZipEntry(String name) 使用指定名称创建新的 ZIP 条目。
					 * 
					 * ZipEntry:此类用于表示 ZIP 文件条目。 (文件条目：个人理解为zip压缩文件中的每一个文件)
					 */
					// zipOut.putNextEntry(new ZipEntry(list.get(i).getName()));
					int temp = 0;
					while ((temp = input.read()) != -1) {
						zipOut.write(temp);
					}
					input.close();
				}
			}
			zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
