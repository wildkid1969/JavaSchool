import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
*主界面的
*/
public class TestDownloadTTS6 extends JFrame implements Runnable{
   private  JLabel[] label;
   private static JLabel time;
   public static JCheckBox[] cbox=new JCheckBox[15];;
   public static JCheckBox cbox_dms;
   private static JButton button;
   public static JTextField text;
   public static JTextArea jta=new JTextArea();
   private JScrollPane scroll;
   private static Color fontColor=new Color(200,100,50);
   private static Color bgColor=new Color(173,216,230);
   private static Font font=new Font(Font.DIALOG,Font.BOLD,16);
   public TestDownloadTTS6() {
       init();
   }
   public static void main(String[] args) throws Exception{
       final TestDownloadTTS6 a=new TestDownloadTTS6();
       
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int sum=0;
               for(JCheckBox box:cbox){
                   if(!box.isSelected())sum++;
               }
               if(sum==cbox.length&&!cbox_dms.isSelected()){
                   JOptionPane.showMessageDialog(null,"请选择!","错误提示",0);
               }else if(text.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"存放位置不能为空！!","错误提示",0);
               }else{
                   try {
                       new Thread(a).start();
                   } catch (Exception e1) {}
                   final  Timer timer=new Timer();
                   timer.schedule(new TimerTask() {
                       int startTime=0;
                       public void run() {
                           time.setText(""+startTime+++" s");
                           if(Png.timeStop){
                               timer.cancel();
                               Png.timeStop=false;
                           }
                       }
                   }, 0, 1000);
               }
           }
       });
       cboxClick();
   }

   public void init() {
       Container p=getContentPane();
       p.setSize(500, 610);
       p.setLayout(null);//布局为空
       JFrame jf=new JFrame();
       jf.setSize(500, 630);//窗体大小
       jf.add(p);
       jf.setLayout(null);//布局为空
       jf.setLocationRelativeTo(null);//居中显示
       jf.setTitle("一键下载PPT&经典案例&项目文档");
       p.setBackground(bgColor);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       label=new JLabel[14];
       JLabel address=new JLabel("存放位置：");
       address.setBounds(220, 50, 200, 30);
       address.setFont(font);
       address.setForeground(fontColor);
       p.add(address);
       text=new JTextField("/home/tarena/AllPPT");
       text.setBounds(220, 90, 200, 30);
       text.setFont(font);
       text.setForeground(fontColor);
       p.add(text);
       for(int i=0;i<label.length;i++){
           label[i]=new JLabel();
           label[i].setBounds(20, 10+i*40, 120, 30);
           label[i].setFont(font);
           label[i].setForeground(fontColor);
           cbox[i]=new JCheckBox();
           cbox[i].setBounds(150, 10+i*40, 30, 30);
           cbox[i].setOpaque(false);
           p.add(label[i]);
           p.add(cbox[i]);
       }
       label[0].setText("Java语言基础");
       label[1].setText("Java面向对象");
       label[2].setText("Java核心 I");
       label[3].setText("Java核心 II");
       label[4].setText("数据库");
       label[5].setText("JDBC/XML");
       label[6].setText("PL/SQL");
       label[7].setText("HTML/CSS/JS");
       label[8].setText("Servlet/JSP");
       label[9].setText("Ajax&Jquery");
       label[10].setText("Struts");
       label[11].setText("Hibernate");
       label[12].setText("Spring");
       label[13].setText("高级项目实践");
       JLabel label_dms=new JLabel("项目的文档");
       label_dms.setBounds(20, 570, 120, 30);
       label_dms.setFont(font);
       label_dms.setForeground(fontColor);
       p.add(label_dms);
       cbox[14]=new JCheckBox("全选");
       cbox[14].setBounds(220, 10, 80, 30);
       cbox[14].setFont(font);
       cbox[14].setForeground(fontColor);
       cbox[14].setOpaque(false);
       p.add(cbox[14]);
       cbox_dms=new JCheckBox();
       cbox_dms.setBounds(150, 570, 80, 30);
       cbox_dms.setOpaque(false);
       p.add(cbox_dms);
       button=new JButton("下载");
       button.setBounds(220, 140, 80, 30);
       button.setFont(font);
       button.setForeground(fontColor);
       p.add(button);
       jta.setBounds(220, 200, 250, 350);
       jta.setBackground(bgColor);
       jta.setFont(font);
       jta.setForeground(fontColor);
       jta.setEditable(false);
       p.add(jta);
       text.setBorder(new LineBorder(fontColor));
       text.setBackground(bgColor);
       scroll=new JScrollPane(jta);
       scroll.setBounds(220,220,250,350);
       scroll.setBorder(new LineBorder(fontColor));
       p.add(scroll);
       time=new JLabel("时间");
       time.setFont(font);
       time.setForeground(fontColor);
       time.setBounds(420, 190, 100, 30);
       p.add(time);
       jf.setVisible(true);
   }
   public static void cboxClick(){
       cbox[14].addActionListener(new ActionListener() {//全选  的监听事件
           public void actionPerformed(ActionEvent e) {
               for(int i=0;i<cbox.length-1;i++){
                   if(cbox[14].isSelected()){
                       cbox[i].setSelected(true);
                       cbox_dms.setSelected(true);
                   }else{
                       cbox[i].setSelected(false);
                       cbox_dms.setSelected(false);
                   }
               }
           }
       });
       for( int i=0;i<cbox.length-1;i++){
           cbox[i].addActionListener(new ActionListener() {//其它复选按钮  的监听事件
               public void actionPerformed(ActionEvent e) {
                   cbox[14].setSelected(false);
               }
           });
       }
   }
   public void run() {
       try {
           Png.Download();
       } catch (Exception e) {
           System.out.println("有异常");
       }
   }
}
/****************************************下载项目文档的******************************************************************************************************/
class DMS_NetCTOSS{
   private static String[] name1={"FUNDAMENTAL","OOP","SE01","SE02","JDBC&XML","ORACLE","HTMLCSSJS"};
   private static String[] name2={"DEPLOYMENT","REQUIREMENT","DESIGN"};//布署，需求，设计
   private static String[] name3={"系统布署说明书","系统需求说明书","系统设计说明书"};
   private static String[] name4={"系统需求说明书","数据库设计说明书","2.0页面布局设计说明书"};
   
   public static void dms_NetCTOSS(){
       String a_r="http://pdf5.tarena.com.cn/tts6materials/TTS6/JAVA/JSD_V01/";
       String address="";
       for(int i=0;i<6;i++){//SE02之前都有3个文档
           int j=0;
           if(i==4)j=1;
           if(i==5){
               for(;j<3;j++){//布署，需求，设计 3个文档
                   if(j<2){
                       address=a_r+name1[i]+"/PRJDOC/"+name2[j+1]+"/index.pdf";//下载NetCTOSS前2个文档
                   }else{
                       address=a_r+name1[i+1]+"/PRJDOC/"+name2[j]+"/index.pdf";//下载NetCTOSS页面布局设计说明书
                   }
                   new Threa1(i,j,address).start();
               }
           }else{
               for(;j<3;j++){//布署，需求，设计 3个文档
                   address=a_r+name1[i]+"/PRJDOC/"+name2[j]+"/index.pdf";
                   new Threa1(i,j,address).start();
               }
           }
       }
   }
   private static class Threa1 extends Thread{
       private String addressSub;
       private int i,j;
       public Threa1(int i,int j,String s){
           addressSub=s;
           this.j=j;
           this.i=i;
       }
       public void run(){
           InputStream is=null;
           RandomAccessFile raf=null;
           try {
                   URL url=new URL(addressSub);//获取网络流
                   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                   conn.setRequestProperty("RANGE", "bytes="+0+"-");//文件下载范围
                   is=conn.getInputStream();//获取网络流
                   File localFile=null;
                   File dmsDir=new File(Png.fileAddress+"/DMS&NetCTOSS");
                   //File dmsDir=new File("/home/tarena/DMS&NetCTOSS");//下载的路径
                   if(!dmsDir.exists())dmsDir.mkdirs();
                   if(i==5){
                        localFile=new File(dmsDir+"/NetCTOSS"+name4[j]+".pdf");
                   }else{
                        localFile=new File(dmsDir+"/DMS"+(i+1)+".0"+name3[j]+".pdf");
                   }
                   localFile.createNewFile();
                   raf = new RandomAccessFile(localFile, "rw");
                   byte[] buffer=new byte[8*1024];
                   int b=-1;
                   while((b=is.read(buffer))!=-1){
                       raf.write(buffer,0,b);
                   }
           } catch (IOException e) {
           }finally{
               try {
                   if(is!=null)is.close();
                   if(raf!=null)raf.close();
               } catch (Exception e2) {
               }
           }
       }
   }
}
/*********************************下载经典案例的***************************************************************************************/
class ClassicCase {
   private static String[] javaName={"FUNDAMENTAL","OOP","SE01","SE02","ORACLE","JDBC&XML","PLSQL","HTMLCSSJS","SERVLET&JSP","AJAX&JQUERY","STRUTS","HIBERNATE","SPRING"};
   private static String image="index.files/image0";
   private static boolean timeOut;//判断是否连接超时 ture为超时
   private static String[] css={"index.html","index.files/jquery.min.js","index.files/jquery.snippet.js","index.files/index.js","index.files/index.css","index.files/jquery.snippet.css"};
   private static String com_a_r="http://pdf5.tarena.com.cn/tts6materials/TTS6/JAVA/JSD_V01/";//第一个公用地址
   public void Case(int i)throws Exception {
       //for(int i=0;i<javaName.length;i++){//最外层，就是左边导航栏，
           if(i==0)load(javaName[i],7);//FUNDAMENTAL---load(地址中的一部分，天数)
           if(i==1)load(javaName[i],6);//OOP
           if(i==2)load(javaName[i],6);//SE01
           if(i==3)load(javaName[i],6);//SE02
           if(i==4)load(javaName[i],7);//ORACLE
           if(i==5)load(javaName[i],4);//JDBC&XML
           if(i==6)load(javaName[i],4);//PLSQL
           if(i==7)load(javaName[i],6);//HTMLCSSJS
           if(i==8)load(javaName[i],11);//SERVLET&JSP
           if(i==9)load(javaName[i],5);//AJAX&JQUERY
           if(i==10)load(javaName[i],6);//Struts
           if(i==11)load(javaName[i],6);//Hibernate
           if(i==12)load(javaName[i],6);//Spring
       //}
   }
   //load(地址中的一部分(or左边导航栏文件夹)，天数)
   public static void load(String sub_a_r,int maxDay) throws Exception{
       TestDownloadTTS6.jta.append("正在下载经典案例。。。"+"\n"+"。。需要点时间。。"+"\n");
       TestDownloadTTS6.jta.selectAll();
       for(int j=1;j<maxDay;j++){//第二层，day01....day05....
           String com_a_r2=com_a_r+sub_a_r+"/DAY0"+j+"/CASE/01/";//第二个公用地址
           if(j==10){
               com_a_r2=com_a_r2.substring(0,com_a_r2.indexOf("010"))+com_a_r2.substring(com_a_r2.indexOf("10/"));
           }
           new Threa(1,com_a_r2+css[0],sub_a_r,j).start();//1代表下载index.html
           Thread.sleep(100);
           //下载image
           for(int k=1;k<100;k++){
               String png="";
               if(timeOut){
                   timeOut=false;
                   break;//连接超时退出内层循环
               }else if(k<=9){
                   png="0"+k+".png";
               }else{
                   png=k+".png";
               }
               Threa threa=new Threa(2,com_a_r2+image+png,sub_a_r,j);//2代表下载imges
               threa.setDaemon(true);
               threa.start();
               Thread.sleep(200);
           }
           //下载css
           for(int c=1;c<6;c++){
               new Threa(3,com_a_r2+css[c],sub_a_r,j).start();//3代表下载css
               Thread.sleep(100);
           }
       }
   }
   private static class Threa extends Thread{
       private  String address,left_dir;
       private int day_case,n;
       //代表，网络路径,左边导航栏文件夹,具体某一天案例文件夹
       public Threa(int n,String address,String left_dir,int day_case){
           this.address=address;
           this.left_dir=left_dir;
           this.day_case=day_case;
           this.n=n;//代表，1--下载index.html  2--下载image  3--下载css样式
       }
       public void run(){
           InputStream is=null;
           RandomAccessFile raf=null;
           File file=null;
           try {
               URL url=new URL(address);//获取网络流
               HttpURLConnection conn=(HttpURLConnection)url.openConnection();
               conn.setRequestProperty("RANGE", "bytes="+0+"-");//文件下载范围
               conn.setConnectTimeout(500);
               is=conn.getInputStream();//获取网络流
                                    //文件夹格式：AllPPT/OOP/OOPDAY01--CASE
               File case_dir=new File("/home/tarena/AllPPT/"+left_dir+"/"+left_dir+"DAY0"+day_case+"--CASE");
               if(!case_dir.exists())case_dir.mkdirs();//如果没有，就创建案例文件夹
               if(n==1){
                    file=new File(case_dir+"/"+css[0]);//案例主页index.html
               }else {
                   File case_dir_in=new File(case_dir+"/index.files");
                   if(!case_dir_in.exists())case_dir_in.mkdirs();//index.file文件夹
                   if(n==2){
                       file=new File(case_dir+address.substring(address.length()-25));//image
                   }else if(n==3){
                       file=new File(case_dir+address.substring(address.indexOf("/index.files/"), address.lastIndexOf("s"))+"s");//css
                   }
               }
               file.createNewFile();
               raf = new RandomAccessFile(file,"rw");
               byte[] buffer=new byte[8*1024];
               int b=-1;
               while((b=is.read(buffer))!=-1){
                   raf.write(buffer,0,b);
               }
           } catch (IOException e) {
               timeOut=true;
           }finally{
               try {
                   if(is!=null)is.close();
                   if(raf!=null)raf.close();
               } catch (Exception e2) {
               }
           }
       }
   }
}
/*********************************************下载图片的********************************************************************/
class Png {
   private static File inDir;//最里面的文件夹
   private static boolean timeOut;//判断是否连接超时 ture为超时
   public static boolean timeStop;//计时停止
   public static String fileAddress=TestDownloadTTS6.text.getText();//获取存放文件的路径
   private static File outDir=new File(fileAddress);//存放文件的外层文件夹--AllPPT
   private static String address;
   private static String[] name={"FUNDAMENTALDAY0","OOPDAY0","SE01DAY0","SE02DAY0","ORACLEDAY0","JDBC&XMLDAY0","HTMLCSSJSDAY0","SERVLET&JSPDAY0","AJAX&JQUERYDAY0","STRUTSDAY0","HIBERNATEDAY0","SPRINGDAY0"};
   public static void Download() throws Exception{
       ClassicCase cc=new ClassicCase();
       outDir.mkdir();
       for(int i=0;i<13;i++){
           if(TestDownloadTTS6.cbox[i].isSelected()){
               switch(i){
                   case 0:
                       load(1,7,name[0]);
                       cc.Case(0);
                       break;
                   case 1:
                       load(1,6,name[1]);
                       cc.Case(1);
                       break;
                   case 2:
                       load(1,6,name[2]);
                       cc.Case(2);
                       break;
                   case 3:
                       load(1,6,name[3]);
                       cc.Case(3);
                       break;
                   case 4:
                       load(1,7,name[4]);
                       cc.Case(4);
                       break;
                   case 5:
                       load(1,4,name[5]);
                       cc.Case(5);
                       break;
                   case 6:
                       load(7,10,name[4]);
                       cc.Case(6);
                       break;
                   case 7:
                       load(1,6,name[6]);
                       cc.Case(7);
                       break;
                   case 8:
                       load(1,11,name[7]);
                       cc.Case(8);
                       break;
                   case 9:
                       load(1,5,name[8]);
                       cc.Case(9);
                       break;
                   case 10:
                       load(1,6,name[9]);
                       cc.Case(10);
                       break;
                   case 11:
                       load(1,6,name[10]);
                       cc.Case(11);
                       break;
                   case 12:
                       load(1,6,name[11]);
                       cc.Case(12);
                       break;
               }
           }
       }
       if(TestDownloadTTS6.cbox_dms.isSelected()){//文档选中则下载
           TestDownloadTTS6.jta.append("。。正在下载项目文档！。。"+"\n");
           TestDownloadTTS6.jta.selectAll();
           DMS_NetCTOSS.dms_NetCTOSS();
       }
       for(int k=7,j=1;k<10;k++,j++){
           moveDir(outDir+"/ORACLE/ORACLEDAY0"+k, outDir+"/PLSQL/PLSQLDAY0"+j);
       }
       timeStop=true;
       TestDownloadTTS6.jta.append("。。。下载完成！。。。"+"\n");
       TestDownloadTTS6.jta.selectAll();
   }
   public static void load(int days,int maxDay,String bigName) throws Exception{
       File f=new File(outDir+"/"+bigName.substring(0, bigName.length()-4));//outDir进来的文件夹
       f.mkdir();
       for(;days<maxDay;days++){
           address="http://pdf5.tarena.com.cn/tts6materials/TTS6/JAVA/JSD_V01/"+bigName.substring(0, bigName.length()-4)+"/"+"DAY0"+days+"/COURSE/JAVAJSD_V01"+bigName+days+"_0";
           if(days==10){
               address=address.substring(0,address.indexOf("010"))+address.substring(address.indexOf("10/"), address.lastIndexOf("010"))+"10_0";
           }
               
           inDir=new File(f+"/"+bigName+days);
           inDir.mkdir();
           for(int i=1;i<80;i++){
               if(timeOut){
                   timeOut=false;
                   break;//连接超时退出内层循环
               }
               new Threa(address,i).start();
               Thread.sleep(200);
           }
       }
   }
   private static class Threa extends Thread{
       private  int i;
       private  String ii;
       private  String addressSub;//网络图片路径
       public Threa(String addressSub,int i){
           this.i=i;
           this.addressSub=addressSub;
       }
       public void run(){
           InputStream is=null;
           RandomAccessFile raf=null;
           try {
                   if(i<=9){
                       ii="0"+i+".png";
                   }else{
                       ii=i+".png";
                   }
                   addressSub=addressSub+ii;//网络图片路径
                   
                   URL url=new URL(addressSub);//获取网络流
                   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                   conn.setRequestProperty("RANGE", "bytes="+0+"-");//文件下载范围
                   conn.setConnectTimeout(500);
                   is=conn.getInputStream();//获取网络流
                   
                   TestDownloadTTS6.jta.append(i+".png"+"\n");
                   TestDownloadTTS6.jta.selectAll();
                   File localFile=new File(inDir+"/"+ii);
                   localFile.createNewFile();
                   raf = new RandomAccessFile(localFile, "rw");
                   byte[] buffer=new byte[8*1024];
                   int b=-1;
                   while((b=is.read(buffer))!=-1){
                       raf.write(buffer,0,b);
                   }
           } catch (IOException e) {
               timeOut=true;
               TestDownloadTTS6.jta.append(inDir.getName()+"下载完成！"+"\n");
               TestDownloadTTS6.jta.selectAll();
           }finally{
               try {
                   if(is!=null)is.close();
                   if(raf!=null)raf.close();
               } catch (Exception e2) {
               }
           }
       }
   }
   /**
    * 移动文件夹ORACLE里面的ORACLEDAY07，08，09
    * 到PLSQL，并改名为PLSQLDAY01，02，03
    */
public static boolean moveDir(String srcDir,String destDir)throws IOException{
       File src=new File(srcDir);
       File dest=new File(destDir);
       if(!src.exists())return false;
       if(!dest.exists())dest.mkdir();//如果没有目标目录，则创建目录
       File[] subs=src.listFiles();//列出源文件夹里面的文件
           for(File f:subs){
                   BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f));
                   FileOutputStream fos=new FileOutputStream(dest.getPath()+"/"+f.getName());
                   BufferedOutputStream bos=new BufferedOutputStream(fos);
                   int b=-1;
                   while((b=bis.read())!=-1){
                       bos.write(b);
                   }
                   f.delete();
                   bis.close();
                   bos.close();
           }
       src.delete();
       return true;
   }
}