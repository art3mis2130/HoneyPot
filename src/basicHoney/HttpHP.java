package basicHoney;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpHP
{
    public static void main(String[] args)
    {
        int port=8080;
        try(ServerSocket serverS=new ServerSocket(port))
        {
            System.out.println("Acknowledge ->"+port);
            while(true)
            {
                Socket sysS=serverS.accept();
                System.out.println("Address : "+sysS.getInetAddress());
                handleSys(sysS);
            }
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    private static void handleSys(Socket sysS)
    {
        try(InputStream inp=sysS.getInputStream();
            OutputStream outp = sysS.getOutputStream())
        {
            byte[] buffer=new byte[1024];
            int bytesRead=inp.read(buffer);
            String recData=new String(buffer, 0, bytesRead);
            System.out.println("Receive : "+recData);
            String resp="HTTP/1.1 200 OK \nContent Length: 0\n\n";
            outp.write(resp.getBytes());
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }finally
        {
            try
            {
                sysS.close();
            }
            catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
