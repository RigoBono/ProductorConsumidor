/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorconsumidor;

/**
 *
 * @author RigoBono
 */
import java.awt.event.KeyEvent;
import java.util.*;
public class DirectorOrquesta implements Runnable {
    public void run()
    {
        Productor productor=new Productor();
        productor.setVisible(true);
        Consumidor consumidor=new Consumidor();
        productor.setVisible(true);
        Buffer buffer=new Buffer();
        buffer.setVisible(true);
        escuchador esc=new escuchador();
        esc.setVisible(true);
        Thread hiloEsc=new Thread(esc);
        hiloEsc.start();
        consumidor.setVisible(true);
        Thread hiloBuffer=new Thread(buffer);
        Thread hiloProductor=new Thread(productor);
        Thread hiloConsumidor=new Thread(consumidor);
        hiloProductor.start();
        hiloConsumidor.start();
        hiloBuffer.start();
        buffer.generaBuffer();
        buffer.imprimeBuffer();
        buffer.checaBuffer();
        if(buffer.bandDisponibles==true)
            System.out.println("ok"+buffer.buffer.size());
        while(buffer.bandSalir==false)
        {
           buffer.bandSalir=esc.bandFin;
           if(esc.bandFin==true)
               break;
            try
            {
                Thread.sleep(1000);
            }catch(Exception e){}
            buffer.checaBuffer();
            if(buffer.bandDisponibles==true)
            {
                buffer.muestraMensaje("Productor intento entrar");
                Random rd=new Random();
                int cantProd=(int)(rd.nextDouble()*8+1);
                int tiempo=(int)(rd.nextDouble()*4+1);
                productor.muestraMensaje("Trabajando, produciendo "+cantProd+" productos...");
                try
                {
                Thread.sleep(tiempo*500);
                }catch(Exception e){}
                
                for(int i=0;i<cantProd;i++)
                {
                
                if(productor.ultimaPosicion==40)
                    productor.ultimaPosicion=0;
                if(buffer.buffer.elementAt(productor.ultimaPosicion).producto.equals("")==true)
                {
                    buffer.ingresaProducto(productor.produceCaracter(), productor.ultimaPosicion);
                    buffer.imprimeBuffer();
                }
                buffer.checaBuffer();
                if(buffer.bandDisponibles==false)
                    break;
                productor.ultimaPosicion++;
                }
                
                productor.muestraMensaje("Durmiendo...");
            }
            if(buffer.bandProductosQueComer==true)
            {
                buffer.muestraMensaje("Consumidor intento entrar");
                if(consumidor.ultimaPosicion>=40)
                    consumidor.ultimaPosicion=0;
                String aux=buffer.buffer.elementAt(consumidor.ultimaPosicion).producto.substring(buffer.buffer.elementAt(consumidor.ultimaPosicion).producto.indexOf("-")+1, buffer.buffer.elementAt(consumidor.ultimaPosicion).producto.length());
                System.out.println("Asi esta aux"+aux);
                if(aux.equals("")==true)
                {
                    System.out.println("avanzo");
                    consumidor.ultimaPosicion++;
                }  
                else
                {
                    buffer.checaBuffer();
                    Random rd=new Random();
                    int cantCom=(int)(rd.nextDouble()*8+1);
                    int tiempo=(int)(rd.nextDouble()*4+1);
                    consumidor.muestraMensaje("Consumiendo "+cantCom+" productos de "+buffer.inventario+"...");
                    buffer.checaBuffer();
                    if(buffer.inventario>=cantCom)
                    {
                        try
                    {
                     Thread.sleep(tiempo*500);
                    }catch(Exception e){}
                    buffer.checaBuffer();
                    buffer.buscaDisponibles();
                     for(int i=0;i<cantCom;i++)
                    {
                        
                       
                       if(consumidor.ultimaPosicion==40)
                           consumidor.ultimaPosicion=0;
                       buffer.checaBuffer();
                       
                       
                       if(buffer.buffer.elementAt(consumidor.ultimaPosicion).producto.equals("")==true)
                       {
                           
                           boolean bandOk=false;
                           for(int j=consumidor.ultimaPosicion;j<buffer.buffer.size();j++)
                           {
                               if(buffer.buffer.elementAt(j).producto.equals("")==false)
                               {
                                   consumidor.ultimaPosicion=j;
                                   bandOk=true;
                                   break;
                               }
                           }
                           if(bandOk==false)
                           {
                               for(int j=0;j<consumidor.ultimaPosicion;j++)
                           {
                               if(buffer.buffer.elementAt(j).producto.equals("")==false)
                               {
                                   consumidor.ultimaPosicion=j;
                                   bandOk=true;
                                   break;
                               }
                           }
                               
                           }
                           if(consumidor.ultimaPosicion>=40)
                               consumidor.ultimaPosicion=0;
                          
                       }
                       else
                       {
                       if(buffer.bandProductosQueComer==true)
                       {
                           
                           consumidor.ultimaPosicion++;
                           System.out.println("Aqui iniciia");
                         buffer.quitaProducto(consumidor.ultimaPosicion); 
                         buffer.imprimeBuffer();
                         
                       }
                       else
                           break; 
                       }
                       
                       
                    }   
                    }
                    else
                    {
                        consumidor.muestraMensaje("No hay productos suficientes para saciar orden...");
                        try
                    {
                     Thread.sleep(500);
                    }catch(Exception e){}
                        
                    }
                        
                    System.out.println("comio");
                    consumidor.muestraMensaje("Durmiendo...");
                }
                    
                consumidor.ultimaPosicion++;
            }
            if(buffer.bandDisponibles==false)
                System.out.println("se acabo");
            if(productor.ultimaPosicion==40)
                productor.ultimaPosicion=0;
            if(consumidor.ultimaPosicion==40)
                consumidor.ultimaPosicion=0;
        }
        consumidor.setVisible(false);
        productor.setVisible(false);
        buffer.setVisible(false);
        esc.setVisible(false);
    }
    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                
        // TODO add your handling code here:
        
        int key=evt.getKeyCode();
        System.out.println(key);
        if(key==KeyEvent.VK_ESCAPE)
        {
            System.out.println("Ok :D");
         
        }
    }
}
