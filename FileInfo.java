package com.huangjin.fileop;

//要分割的文件的信息
public class FileInfo {
	private String filename;// 文件名
	private long filesize;// 文件大小
	private String path;// 文件路径

	public FileInfo() {
	}

	public FileInfo(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
