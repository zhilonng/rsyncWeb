package com.scut.rsyncWeb.entity;

public class FileInfo {

	private String fileName;
	private String fileOwner;
	private String fileCreateTIme;
	
	public FileInfo(){}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOwner() {
		return fileOwner;
	}
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}
	public String getFileCreateTIme() {
		return fileCreateTIme;
	}
	public void setFileCreateTIme(String fileCreateTIme) {
		this.fileCreateTIme = fileCreateTIme;
	}
}
