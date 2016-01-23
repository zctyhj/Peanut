package com.huangjin.fileop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件分割工具
 * 
 * @author Administrator 需求描述：将一个大于10M，小于15M的文件分割成三个文件 1、5M 5M nM
 */
public class FileSplit {
	public static void main(String[] args) {
		System.out.println("ok");
		String path = "F:\\资料下载\\w3c.chm";
		File file = new File(path);
		FileInfo fileinfo = new FileInfo();
		fileinfo = getfInfo(fileinfo, path);
		System.out.println(fileinfo.getFilesize());
		split(file, fileinfo);
		merge();
	}

	/**
	 * 合并刚才分割的三个文件 看效果
	 */
	public static void merge() {
		try {
			File file_1 = new File("F:\\资料下载\\file_1.data");
			File file_2 = new File("F:\\资料下载\\file_2.data");
			File file_3 = new File("F:\\资料下载\\file_3.data");

			FileInputStream fis_1 = new FileInputStream(file_1);
			BufferedInputStream bis_1 = new BufferedInputStream(fis_1);
			FileInputStream fis_2 = new FileInputStream(file_2);
			BufferedInputStream bis_2 = new BufferedInputStream(fis_2);
			FileInputStream fis_3 = new FileInputStream(file_3);
			BufferedInputStream bis_3 = new BufferedInputStream(fis_3);

			File file = new File("F:\\资料下载\\file.chm");
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte data[] = new byte[1024 * 1024];
			int len = 0;
			while ((len = bis_1.read(data, 0, data.length)) > 0) {
				bos.write(data, 0, len);
				bos.flush();
			}
			while ((len = bis_2.read(data, 0, data.length)) > 0) {
				bos.write(data, 0, len);
				bos.flush();
			}
			while ((len = bis_3.read(data, 0, data.length)) > 0) {
				bos.write(data, 0, len);
				bos.flush();
			}
			bis_1.close();
			bis_2.close();
			bis_3.close();
			bos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个大于10M，小于15M的文件分割成三个文件
	 * 
	 * @param file
	 */
	public static void split(File file, FileInfo fileinfo) {
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte data[] = new byte[1024 * 1024];
			int len = 0;
			// 保证文件长度在10M 15M 之间
			if (fileinfo.getFilesize() < 1024 * 1024 * 15 && fileinfo.getFilesize() > 1024 * 1024 * 10) {
				File file_1 = new File("F:\\资料下载\\file_1.data");
				File file_2 = new File("F:\\资料下载\\file_2.data");
				File file_3 = new File("F:\\资料下载\\file_3.data");
				file_1.createNewFile();
				file_2.createNewFile();
				file_3.createNewFile();
				FileOutputStream fos_1 = new FileOutputStream(file_1);
				BufferedOutputStream bos_1 = new BufferedOutputStream(fos_1);
				FileOutputStream fos_2 = new FileOutputStream(file_2);
				BufferedOutputStream bos_2 = new BufferedOutputStream(fos_2);
				FileOutputStream fos_3 = new FileOutputStream(file_3);
				BufferedOutputStream bos_3 = new BufferedOutputStream(fos_3);
				for (int i = 0; i < 5; i++) {
					bis.read(data, 0, data.length);
					bos_1.write(data);
					bos_1.flush();
				}
				bos_1.close();
				for (int i = 0; i < 5; i++) {
					bis.read(data, 0, data.length);
					bos_2.write(data);
					bos_2.flush();
				}
				bos_2.close();
				while ((len = bis.read(data, 0, data.length)) > 0) {
					bos_3.write(data, 0, len);
					bos_3.flush();
				}
				bos_3.close();
				bis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件信息
	 */
	public static FileInfo getfInfo(FileInfo fileinfo, String path) {
		File file = new File(path);
		long len = file.length();
		String realpath = file.getAbsolutePath();
		String filename = file.getName();

		fileinfo.setFilename(filename);
		fileinfo.setPath(realpath);
		fileinfo.setFilesize(len);
		/*
		 * try { FileInputStream fis = new FileInputStream(file);
		 * BufferedInputStream bis = new BufferedInputStream(fis); byte data[] =
		 * new byte[1024*1024]; int len = 0; while((len = bis.read(data, 0,
		 * data.length))!=0){
		 * 
		 * } } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		return fileinfo;
	}

	/**
	 * 需求：将一个大文件分成若干份 分析：这个文件多大？ 一、10M以下 二、100M以下 三、1G以下 四、10G以下 五、10G以上
	 * 
	 * 第二个问题：如何压缩文件
	 * 
	 */
	/**
	 * 实现将多个文件进行压缩，生成指定目录下的指定名字的压缩文件 Parameters: filename ：指定生成的压缩文件的名称
	 * temp_path ：指定生成的压缩文件所存放的目录 list ：List集合：用于存放多个File（文件）
	 * */
	public static void createZip(String filename, String temp_path, List<File> list) {
		File file = new File(temp_path);
		System.err.println(temp_path);
		File zipFile = new File(temp_path + File.separator + filename);
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

	/**
	 * 需求：一个100M以下的文件 并且大于10M 分割成若干个文件 分析： 输入是个大文件 输出是多个文件 file 需要处理的大文件
	 * filespath 保存分割文件的文件夹路径
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
				for (int i = 0; i < index-1; i++) {
					File tempfile = new File(filespath + File.separator + str + i + ".hj");//生成文件名
					tempfile.createNewFile();
					FileOutputStream fos = new FileOutputStream(tempfile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					for(int j = 0;j<len/(1024*1024);j++){
						n = bis.read(data, 0, data.length);
						bos.write(data, 0, n);
						bos.flush();
					}
					bos.close();
				}
				File lastfile = new File(filespath + File.separator + str + (index-1) + ".hj");
				lastfile.createNewFile();
				FileOutputStream fos = new FileOutputStream(lastfile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				while((n=bis.read(data, 0, data.length))!=0){
					bos.write(data, 0, n);
					bos.flush();
				}
				bos.close();
			}
		}
	}
	/**
	 * 需求：若干个文件  合并成一个
	 * 输入  合并后的文件名
	 * 输入 当前需要合并的文件名列表
	 */
	
}
