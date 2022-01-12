import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.*;
public  class AnaDigi extends JPanel implements Runnable
{
    int anglex,angley;
    int radius=200,i;
    String timeString="";
    int hours,minutes,seconds;
    JLabel l1=new JLabel();
    Date currentDate;
    Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("m", Locale.getDefault());
    static JFrame f=new JFrame("ANALOG AND DIGITAL CLOCK");
   
    AnaDigi()
    {
        JPanel panel=new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(600,250,500,100);
        JLabel l2=new JLabel("ANALOG CLOCK");
        l2.setBounds(200,430,150,150);
        JLabel l3=new JLabel("DIGITAL CLOCK");
        l3.setBounds(650,300,150,150);
        l1.setBounds(60,60,60,60);
        l1.setFont(new Font("courier", Font.BOLD,75));
                l1.setForeground(Color.red);
        panel.add(l1);
        f.add(panel);
        f.add(l2);
        f.add(l3);
    }
        void run(int a)
        {
        try
        {
            Calendar cal = Calendar.getInstance();//abstract class
                    hours = cal.get( Calendar.HOUR_OF_DAY );
                    minutes = cal.get( Calendar.MINUTE );
                    seconds = cal.get( Calendar.SECOND );
                    SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
                    Date date = cal.getTime();
                    timeString=formatter1.format(date);
                    printTime();
       
        }
        catch(Exception e){}
        }
    void printTime()
    {
        l1.setText(timeString);
    }
    public void drawStructure(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(0,0,480,480);
        g.setColor(Color.white);
        g.fillOval(10,10,460,460);
        g.setColor(Color.orange);
        g.fillOval(230,230,30,30);
        double line;
        for(int i=1;i<=12;i++)
        {
            line=i/12.0 * Math.PI*2;
            anglex=(int)((230)+(Math.sin(line)*radius));
            angley=(int)((248)-(Math.cos(line)*radius));
            String a=Integer.toString(i);
            g.setColor(Color.black);
            g.setFont(new Font("arial",Font.BOLD,33));
            g.drawString(a,anglex,angley);
        }
    }
    public void paint(Graphics g)
    {
        drawStructure(g);
        Graphics2D g2 = (Graphics2D) g;
        int xhour, yhour, xminute, yminute, xsecond, ysecond, second, minute, hour;
        currentDate = new Date();
        formatter.applyPattern("s");
        second = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("m");
        minute = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("h");
        hour = Integer.parseInt(formatter.format(currentDate));
        xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 160 + 243);
        ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 160 + 248);
        xminute = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * 140 + 243);
        yminute = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * 140 + 248);
        xhour = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 80 + 243);
        yhour = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 80 + 248);
        g2.setStroke(new BasicStroke(4));
        g.setColor(Color.black);
        g.drawLine(243, 248, xsecond, ysecond);
        g.setColor(Color.green);
        g2.setStroke(new BasicStroke(5));
        g.drawLine(243, 248 , xminute, yminute);
        g.drawLine(243 , 248, xminute, yminute);
        g.setColor(Color.red);
        g2.setStroke(new BasicStroke(7));
        g.drawLine(243, 248 - 1, xhour, yhour);
        g.drawLine(243 - 1, 248, xhour, yhour);
       
    }

          public void start()
        {
                if (thread == null)
            {
                      thread = new Thread(this);
                      thread.start();
                }
          }
        public void run()
        {
                 while (thread != null)
            {
                      try
                {
                        Thread.sleep(10);
                      } catch (InterruptedException e) {}
                run(2);
		repaint();
                }
		
          }

          public void update(Graphics g)
        {
                paint(g);


        }

    public static void main(String[] args)
    {
        AnaDigi e=new AnaDigi();
        e.start();
        f.add(e);
        f.setSize(1200,600);
        f.setVisible(true);
	f.repaint();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
