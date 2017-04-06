package com.scut.rsyncWeb.util;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.scut.jrsync.JsyncClient;

/**
 * 单例模式
 * 上传文件操作
 * 时间：2017/3/26
 * @author zhilong Huang
 */
final public class SingletonFileOperater {
	private static SingletonFileOperater fileOperater = null;//单例类对象
	private static Thread fixedThread = null;
	private final int STOP =-1;
	private final int SUSPEND = 0;
	private final int RUNNING = 1;
	private static int[] status = {1,1,1,1,1};
	private long[] count = {0,0,0,0,0};
	/**
	 * 静态模块
	 */
	static{
		fileOperater = new  SingletonFileOperater();
	}
	/**
	 * 私有构造方法 防止外部创建类对象
	 */
	private SingletonFileOperater(){}
	/**
	 * 静态方法
	 * 返回单例对象
	 * @return
	 */
	public static SingletonFileOperater getOperater(){
		return fileOperater;
	}
	public void syncDirectory(String path){
		final JsyncClient  jrsync = new JsyncClient();
		 System.out.println("这在单例模式中");
		 final File directory;
		 directory = new File("E:\\fenxiao");
		 System.out.println("start rsync");
		 
		 fixedThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					jrsync.syncDirectory(directory, "119.29.188.78", 2466, "/home/serverDir");
					System.out.println("ok");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		 fixedThread.start();
	}
	
	/**
	    * 恢复
	    */
	   public synchronized void myResume(int position){
	      // 修改状态
	      status[position] = RUNNING;
	      // 唤醒
	      notifyAll();
	   }

	   /**
	    * 挂起
	    */
	   public void mySuspend(int position){
	      // 修改状态
	      status[position] = SUSPEND;
	   }

	   /**
	    * 停止
	    */
	   public void stopSync(int position){
	      // 修改状态
		   try{
			   System.out.println("thread stop");
			   fixedThread.interrupt();
		   }catch(Exception ev){
			   
		   }
		   
	   }
}
