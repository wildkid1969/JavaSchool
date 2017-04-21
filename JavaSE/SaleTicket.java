package com.edufound.web.action.product;

public class SaleTicket {
	public static void main(String args[]){
		MyThread mt = new MyThread() ;	// 定义线程对象
		Thread t1 = new Thread(mt) ;	// 定义Thread对象
		Thread t2 = new Thread(mt) ;	// 定义Thread对象
		Thread t3 = new Thread(mt) ;	// 定义Thread对象
		Thread t4 = new Thread(mt) ;	// 定义Thread对象
		Thread t5 = new Thread(mt) ;
		t1.start() ;
		t2.start() ;
		t3.start() ;
		t4.start() ;
		t5.start() ;
		}
}
class MyThread implements Runnable{
private int ticket = 100 ;	// 假设一共有100张票
public void run(){
for(int i=0;i<100;i++){
this.sale() ;	// 调用同步方法
}
}
public synchronized void sale(){	// 声明同步方法
if(ticket>0){	// 还有票
try{
Thread.sleep(300) ;	// 加入延迟
}catch(InterruptedException e){
e.printStackTrace() ;
}
System.out.println(Thread.currentThread().getName() + "卖票：ticket = " + ticket-- );
}

}
};
