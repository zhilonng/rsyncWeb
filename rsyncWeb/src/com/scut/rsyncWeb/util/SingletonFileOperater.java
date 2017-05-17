package com.scut.rsyncWeb.util;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
	private static ExecutorService threadpool;
	private static Thread fixedThread = null;
	private final int STOP =-1;
	private final int SUSPEND = 0;
	public static int[] RUNNING = {1,1,1,1,1}; //公共的检测因子 1代表空闲，0代表已 忙碌
	public static int[] fileInfoId = {0,0,0,0,0};//fileinfo的id，用来标识当前任务占用的RUNNING的num数
	private static int[] status = {1,1,1,1,1};
	private static Future[] future = new Future[5];
	private long[] count = {0,0,0,0,0};
	/**
	 * 静态模块
	 */
	static{
		fileOperater = new  SingletonFileOperater();
		threadpool = Executors.newFixedThreadPool(3);
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
		 directory = new File("E:\\毕业");
		 System.out.println("start rsync");
		 
		 fixedThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					jrsync.syncDirectory(directory, "119.29.188.78", 2468, "/home/serverDir");
					System.out.println("ok");
					
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		 fixedThread.start();
	}
	public void syncDirectory1(String path , final int num){
		RUNNING[num] = 0; //num位标注为忙碌
		System.out.println(RUNNING[num]);
		final JsyncClient  jrsync = new JsyncClient();
		 System.out.println("这在单例模式中");
		 final File directory;
		 directory = new File("D:\\dayinji\\L455_Win64");
		 System.out.println("start rsync in threadpool");
		 future[num] = threadpool.submit(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(RUNNING[num] == 1){
						try {
							System.out.print("thread wait");
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						jrsync.syncDirectory(directory, "119.29.188.78", 2468, "/home/serverDir");
						System.out.println("ok,重置num值");
						RUNNING[num] = 1; //将num位置为空闲
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		 
	}
		public void stopSync1(int num){
			RUNNING[num] = 1; //将num位置为空闲
			System.out.println("线程"+num+"进入cancel");
			future[num].cancel(true);
		}
	/**
	    * 恢复
	    */
	   public synchronized void myResume(int position){
	      // 修改状态
	      status[position] = 0;
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
