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
//import com.sun.glass.events.KeyEvent;
import java.util.*;
public class Buffer extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form Buffer
     */
    int inventario=0;
    boolean bandProductosQueComer=false;
    boolean bandSalir=false;
    boolean bandVacio=false;
    boolean bandLleno=false;
    boolean bandDisponibles=false;
    Vector<Casilla> buffer=new Vector<Casilla>();
    Vector<Casilla> listos=new Vector<Casilla>();
    public void buscaDisponibles()
    {
        listos.clear();
        for(int i=0;i<buffer.size();i++)
        {
            if(buffer.elementAt(i).producto.equals("")==false)
               listos.add(buffer.elementAt(i));
        }
    }
    public void muestraMensaje(String msj)
    {
        jTextArea1.setText(msj);
    }
    public void ingresaProducto(String a,int pos)
    {
        buffer.elementAt(pos).producto=a;
        //jTable1.setValueAt(buffer.elementAt(pos).producto, buffer.elementAt(pos).y, buffer.elementAt(pos).x);
    }
    public void quitaProducto(int pos)
    {
        buffer.elementAt(pos).producto="";
        //jTable1.setValueAt(buffer.elementAt(pos).producto, buffer.elementAt(pos).y, buffer.elementAt(pos).x);
    }
    public void run()
    {
        
    }
    public void checaBuffer()
    {
        inventario=0;
        int productos=0;
        int contEspaciosVacios=0;
        for(int i=0;i<buffer.size();i++)
        {
            if(buffer.elementAt(i).producto.equals("")==false)
            {
                productos++;
                inventario++;
            }
        }
        for(int i=0;i<buffer.size();i++)
        {
            if(buffer.elementAt(i).producto.equals("")==true)
            {
                contEspaciosVacios++;
            }
        }
        if(productos>0)
            bandProductosQueComer=true;
        if(contEspaciosVacios==buffer.size())
        {
            bandVacio=true;
            bandLleno=false;
            bandDisponibles=true;
        }
            
        if(contEspaciosVacios>0)
        {
            bandDisponibles=true;
            bandLleno=false;
            bandVacio=false;
        }
            
        if(contEspaciosVacios==0)
        {
             bandLleno=true;
             bandVacio=false;
             bandDisponibles=false;
        }
           
        
        
    }
    
    public void imprimeBuffer()
    {
        for(int i=0;i<buffer.size();i++)
        {
            jTable1.setValueAt(buffer.elementAt(i).num+".-"+buffer.elementAt(i).producto, buffer.elementAt(i).y, buffer.elementAt(i).x);
        
        }
        jTable1.setEnabled(false);
        jTextArea1.setEditable(false);
    }
    public void generaBuffer()
    {
        int cont=0;
        for(int i=0;i<5;i++)
            for(int j=0;j<8;j++)
            {
                Casilla aux=new Casilla();
                aux.x=i;
                aux.y=j;
                aux.producto="";
                aux.num=cont;
                buffer.add(aux);
                cont++;
                
                
            }
        
    }
    public Buffer() {
        initComponents();
        this.setBounds(500,200,400,301);
    }

    public void prueba()
    {
        generaBuffer();
        for(int i=0;i<buffer.size();i++)
        jTable1.setValueAt(buffer.elementAt(i).producto, buffer.elementAt(i).y, buffer.elementAt(i).x);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buffer");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Buffer", "Buffer", "Buffer", "Buffer ", "Buffer"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mensajes:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        int key=evt.getKeyCode();
        System.out.println(key);
        if(key==KeyEvent.VK_ESCAPE)
        {
            System.out.println("Ok :D");
            bandSalir=true;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        int key=evt.getKeyCode();
        System.out.println(key);
        if(key==KeyEvent.VK_ESCAPE)
        {
            System.out.println("Ok....................");
            bandSalir=true;
        }
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Buffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buffer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}