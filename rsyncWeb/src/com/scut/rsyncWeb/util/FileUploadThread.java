package com.scut.rsyncWeb.util;
/**
 * 自定义文件上传线程
 * 实现暂停、恢复、停止
 * 时间：2017/3/26
 * @author zhilong Huang
 *
 */
public class FileUploadThread extends Thread{

	private final int STOP =-1;
	private final int SUSPEND = 0;
	private final int RUNNING = 1;
	private int status = 1;
	private long count = 0;
	
	public FileUploadThread(String name){
		super(name);
	}
	
	@Override
	public synchronized void run(){
	// 判断是否停止
	while (status != STOP){
		// 判断是否挂起
	   if (status == SUSPEND){
	       try{
	        // 若线程挂起则阻塞自己
	        wait();
	       }catch (InterruptedException e){
	        System.out.println("线程异常终止...");
	       }
	      }else{
	        count++;
	        System.out.println(this.getName() + "第" + count + "次运行...");
	        try{
	        Thread.sleep(100);
	        }catch (InterruptedException e){
	        System.out.println("线程异常终止...");
	        }
	       }
	     }
	 }

   /**
    * 恢复
    */
   public synchronized void myResume(){
      // 修改状态
      status = RUNNING;
      // 唤醒
      notifyAll();
   }

   /**
    * 挂起
    */
   public void mySuspend(){
      // 修改状态
      status = SUSPEND;
   }

   /**
    * 停止
    */
   public void myStop(){
      // 修改状态
      status = STOP;
   }
}
