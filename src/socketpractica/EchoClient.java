
package socketpractica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class EchoClient {
    
  private Socket sock121 = null;
  private Socket sock122 = null;
  private Socket sock221 = null;
  private Socket sock521 = null;
  private Socket sockGlobal = null;
  private BufferedReader br = null;
  private PrintWriter pw = null;
  private String data;
  private String data1;
  private String ip;
  private  final int port=23;
  private InputStream in;
  
  
public void conexion121(String ip, JTextArea area121) {
    this.ip = ip;
    
  try {

    this.sock121 = new Socket(this.ip,this.port);   
    this.sockGlobal = this.sock121;
    in = sock121.getInputStream();
    this.pw = new PrintWriter(this.sock121.getOutputStream());
    
    
    this.data ="zte\r\n";
    this.data1 ="zte\r\n";
    this.sock121.getOutputStream().write(this.data.getBytes());
    this.sock121.getOutputStream().flush();
    
    sock121.getOutputStream().write(this.data1.getBytes());
    sock121.getOutputStream().write("term length 0\n".getBytes());
    area121.append(receive());
    sock121.getOutputStream().flush(); 
  }
  catch(IOException e)
  {
    e.printStackTrace();
  }
}
/* CONEXION 122*/
public void conexion122(String ip, JTextArea area122)
{
    this.ip = ip;
  try
  {
    this.sock122 = new Socket(this.ip,this.port);   
    this.sockGlobal = this.sock122;
    in = sock122.getInputStream();
   
    
    
    this.data ="zte\r\n";
    this.data1 ="zte\r\n";
    this.sock122.getOutputStream().write(this.data.getBytes());
    this.sock122.getOutputStream().flush();
    
    sock122.getOutputStream().write(this.data1.getBytes());
    sock122.getOutputStream().write("term length 0\n".getBytes());
    area122.append(receive());
    sock122.getOutputStream().flush(); 
  }
  catch(IOException e)
  {
    e.printStackTrace();
  }
}
/*CONEXION 221*/
public void conexion221(String ip, JTextArea area221)
{
    this.ip = ip;
  try
  {
    this.sock221 = new Socket(this.ip,this.port);   
    this.sockGlobal = this.sock221;
    in = sock221.getInputStream();
    
    
    this.data ="zte\r\n";
    this.data1 ="zte\r\n";
    this.sock221.getOutputStream().write(this.data.getBytes());
    this.sock221.getOutputStream().flush();
    
    sock221.getOutputStream().write(this.data1.getBytes());
    sock221.getOutputStream().write("term length 0\n".getBytes());
    area221.append(receive());
    sock221.getOutputStream().flush(); 
  }
  catch(IOException e)
  {
    e.printStackTrace();
  }
}

// CONEXION 521
public void conexion521(String ip, JTextArea area521)
{
    this.ip = ip;
  try
  {
    this.sock521 = new Socket(this.ip,this.port);   
    this.sockGlobal = this.sock521;
    in = sock521.getInputStream();
    
    
    this.data ="zte\r\n";
    this.data1 ="zte\r\n";
    this.sock521.getOutputStream().write(this.data.getBytes());
    this.sock521.getOutputStream().flush();
    
    sock521.getOutputStream().write(this.data1.getBytes());
    sock521.getOutputStream().write("term length 0\n".getBytes());
    area521.append(receive());
    sock521.getOutputStream().flush(); 
  }
  catch(IOException e)
  {
    e.printStackTrace();
  }
}


public void comando(String command, JTextArea resultado){  
    
    try {
        this.sockGlobal.getOutputStream().write(command.getBytes());
        resultado.append(receive());      
    } catch (IOException e) {}
}

public boolean estadoOlt121() throws IOException{
    return this.sock121.isConnected();

}
public boolean estadoOlt122() throws IOException{
    return this.sock122.isConnected();
}
public boolean estadoOlt221() throws IOException{
    return this.sock221.isConnected();
}
public boolean estadoOlt521() throws IOException{
    return this.sock521.isConnected();
}

/*DESCONECTAR OLTS*/
public void disconectOlt121(){
      try {
          this.sock121.close();
      } catch (IOException ex) {
          Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void disconectOlt122(){
      try {
          this.sock122.close();
      } catch (IOException ex) {
          Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void disconectOlt221(){
      try {
          this.sock221.close();
      } catch (IOException ex) {
          Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
      }
}

public void disconectOlt521(){
      try {
          this.sock521.close();
      } catch (IOException ex) {
          Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
      }
}

// Procesamiento del Buffer
private  String receive() {
    StringBuilder strBuffer = null;
    try {
        strBuffer = new StringBuilder();
        byte[] buf = new byte[2048];
        int len = 0;
        Thread.sleep(750L);
        while ((len = this.sockGlobal.getInputStream().read(buf)) != 0) { 
            strBuffer.append(new String(buf, 0, len));
            //prueba = new String(buf, 0, len);
            
           /* if (prueba.contains("--More--")) {
                this.sockGlobal.getOutputStream().write("\n".getBytes());    
            }  
            nueva = prueba.replaceAll("--More--", ""); */
           // strBuffer.append(strBuffer.append(new String(buf, 0, len)));

            Thread.sleep(140L);
            if (this.sockGlobal.getInputStream().available() == 0)
                break;
        }
        return strBuffer.toString();  
    } catch (Exception e) {}
    
    return null;
}

}
