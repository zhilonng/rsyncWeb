package com.scut.rsyncWeb.test;

import java.io.File;

import com.scut.jrsync.JsyncClient;

public class FileUploadText {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
		final JsyncClient  jrsync = new JsyncClient();
		final File directory;
		directory = new File("E:\\毕业设计");
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("start rsync");
				 try {
					jrsync.syncDirectory(directory, "119.29.188.78", 2466, "/home/serverDir");
					System.out.println("ok");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		thread.start();
		try {
			System.out.println("线程睡眠");
			thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt(); 
        try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println("线程已经退出!"); 
	}
}
