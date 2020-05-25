package tela;

import arduino.Comunicacao;
import java.awt.Color;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TelaPrincipal extends javax.swing.JFrame implements Runnable{

    private Thread thread;
    private Comunicacao comunicacao = new Comunicacao();
    private Robot robot;
    TableModel modeloPadrao;
    
    Boolean conectado = false;
    
    Color verde = new Color(85,163,105);
    Color verdeAzulado = new Color(14,121,120);
    Color conectar;
    Color vermelho = new Color(219,81,74);
    Color preto = new Color(1,1,1);
    Color cinza = new Color(246,243,241);
    Color branco = new Color(254,254,254);
    
    @Override
    public void run(){
        try {
            robot = new Robot();
            while (true) {
                String leitura = comunicacao.ler();

                if (leitura != null) {
                    int colunaBotao = 0;
                    int colunaComando = 0;

                    while (tabelaComandos.getColumnName(colunaBotao) != "Botão"){colunaBotao++;}
                    while (tabelaComandos.getColumnName(colunaComando) != "Comando"){colunaComando++;}
                    for (int i = 0; i < tabelaComandos.getRowCount(); i++) {
                        if (leitura.equals((String) tabelaComandos.getValueAt(i, colunaBotao))) {
                            int id = getIdLeitura(i, colunaComando);
                            if (id != 0) {
                                robot.keyPress(id);
                                robot.keyRelease(id);
                            }
                            break;
                        } else {
                        }
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (AWTException a) {
            
        }
    }
    
    public TelaPrincipal() {
        initComponents();
        conectar = new Color(14,121,120);
        caixaDispositivo.addItem("Selecione uma porta");
        
        for (String i: Comunicacao.listarPortas()) {
            caixaDispositivo.addItem(i);
        }
        
        TableColumn sportColumn = tabelaComandos.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(null);
        
        for(int i = 0; i < 600; i++){
            if (!(KeyEvent.getKeyText(i).length() >= 25)){
                comboBox.addItem(KeyEvent.getKeyText(i) + " (" + i + ")");
            }
        }
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        modeloPadrao = tabelaComandos.getModel();
        
        txtAviso.setVisible(false);
        botaoDialogoComandos.setEnabled(false);
        
    }
    
    private int getIdLeitura(int linha, int colunaComando) {
        String comando = (String) tabelaComandos.getValueAt(linha, colunaComando);
        String idS = "";
        
        if (comando != null) {
            int i = comando.length() - 1;
            char c = ' ';
            while( c != '(' ) {
                i--;
                c = comando.charAt(i);
            }
            while( comando.charAt(i + 1) != ')' ) {
                i++;
                idS += comando.charAt(i);
            }
            return Integer.parseInt(idS);
        } else {
            return 0;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogoComandos = new javax.swing.JDialog();
        painelComandos = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaComandos = new javax.swing.JTable();
        painelPrincipal = new javax.swing.JPanel();
        txtDispositivo = new javax.swing.JLabel();
        caixaDispositivo = new javax.swing.JComboBox<>();
        botaoDispositivo = new javax.swing.JButton();
        botaoDialogoComandos = new javax.swing.JButton();
        painelTitulo = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JLabel();
        txtVelocidade = new javax.swing.JLabel();
        campoVelocidade = new javax.swing.JFormattedTextField();
        botaoConectar = new javax.swing.JButton();
        txtStatus = new javax.swing.JLabel();
        txtStatus2 = new javax.swing.JLabel();
        txtCriador = new javax.swing.JLabel();
        txtAviso = new javax.swing.JLabel();

        dialogoComandos.setMinimumSize(new java.awt.Dimension(465, 390));
        dialogoComandos.setPreferredSize(null);
        dialogoComandos.setResizable(false);
        dialogoComandos.setSize(new java.awt.Dimension(465, 390));

        painelComandos.setMinimumSize(null);
        painelComandos.setPreferredSize(null);

        botaoFechar.setBackground(new java.awt.Color(246, 243, 241));
        botaoFechar.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        botaoFechar.setForeground(new java.awt.Color(1, 1, 1));
        botaoFechar.setText("Fechar");
        botaoFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoFecharMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoFecharMouseEntered(evt);
            }
        });
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        tabelaComandos.setFont(new java.awt.Font("DejaVu Serif", 0, 12)); // NOI18N
        tabelaComandos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Botão", "Comando"
            }
        ));
        jScrollPane1.setViewportView(tabelaComandos);

        javax.swing.GroupLayout painelComandosLayout = new javax.swing.GroupLayout(painelComandos);
        painelComandos.setLayout(painelComandosLayout);
        painelComandosLayout.setHorizontalGroup(
            painelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelComandosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelComandosLayout.setVerticalGroup(
            painelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelComandosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout dialogoComandosLayout = new javax.swing.GroupLayout(dialogoComandos.getContentPane());
        dialogoComandos.getContentPane().setLayout(dialogoComandosLayout);
        dialogoComandosLayout.setHorizontalGroup(
            dialogoComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelComandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialogoComandosLayout.setVerticalGroup(
            dialogoComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelComandos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogoComandos.getAccessibleContext().setAccessibleParent(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arduino Remoto");
        setMinimumSize(new java.awt.Dimension(441, 216));
        setPreferredSize(null);
        setResizable(false);
        setSize(new java.awt.Dimension(453, 197));

        painelPrincipal.setBackground(new java.awt.Color(246, 243, 241));
        painelPrincipal.setMinimumSize(null);

        txtDispositivo.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        txtDispositivo.setForeground(new java.awt.Color(1, 1, 1));
        txtDispositivo.setText("Dispositivo:");

        caixaDispositivo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        botaoDispositivo.setBackground(new java.awt.Color(246, 243, 241));
        botaoDispositivo.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        botaoDispositivo.setForeground(new java.awt.Color(1, 1, 1));
        botaoDispositivo.setText("Atualizar");
        botaoDispositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoDispositivoMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoDispositivoMouseEntered(evt);
            }
        });
        botaoDispositivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDispositivoActionPerformed(evt);
            }
        });

        botaoDialogoComandos.setBackground(new java.awt.Color(246, 243, 241));
        botaoDialogoComandos.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        botaoDialogoComandos.setForeground(new java.awt.Color(1, 1, 1));
        botaoDialogoComandos.setText("Editar comandos");
        botaoDialogoComandos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoDialogoComandosMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoDialogoComandosMouseEntered(evt);
            }
        });
        botaoDialogoComandos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDialogoComandosActionPerformed(evt);
            }
        });

        painelTitulo.setBackground(new java.awt.Color(246, 244, 241));

        txtTitulo.setFont(new java.awt.Font("DejaVu Serif", 1, 17)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(14, 121, 120));
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("Arduino Remoto");

        javax.swing.GroupLayout painelTituloLayout = new javax.swing.GroupLayout(painelTitulo);
        painelTitulo.setLayout(painelTituloLayout);
        painelTituloLayout.setHorizontalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelTituloLayout.setVerticalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulo)
                .addContainerGap())
        );

        txtVelocidade.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        txtVelocidade.setForeground(new java.awt.Color(1, 1, 1));
        txtVelocidade.setText("Velocidade:");

        campoVelocidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        campoVelocidade.setText("9600");

        botaoConectar.setBackground(new java.awt.Color(14, 121, 120));
        botaoConectar.setFont(new java.awt.Font("DejaVu Serif", 1, 15)); // NOI18N
        botaoConectar.setForeground(new java.awt.Color(254, 254, 254));
        botaoConectar.setText("Conectar");
        botaoConectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoConectarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoConectarMouseEntered(evt);
            }
        });
        botaoConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConectarActionPerformed(evt);
            }
        });

        txtStatus.setFont(new java.awt.Font("DejaVu Serif", 0, 16)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(1, 1, 1));
        txtStatus.setText("Status:");

        txtStatus2.setFont(new java.awt.Font("DejaVu Serif", 0, 15)); // NOI18N
        txtStatus2.setForeground(new java.awt.Color(219, 81, 74));
        txtStatus2.setText("Desconectado");

        txtCriador.setFont(new java.awt.Font("DejaVu Sans", 1, 8)); // NOI18N
        txtCriador.setForeground(new java.awt.Color(14, 121, 120));
        txtCriador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCriador.setText("Por Ordep Lenam");

        txtAviso.setFont(new java.awt.Font("DejaVu Sans", 1, 8)); // NOI18N
        txtAviso.setForeground(new java.awt.Color(14, 121, 120));
        txtAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAviso.setText("Feche o editor de comandos para continuar");

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addComponent(botaoDialogoComandos))
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addComponent(txtAviso)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCriador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoConectar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDispositivo)
                            .addComponent(txtVelocidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoVelocidade)
                            .addComponent(caixaDispositivo, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoDispositivo))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(txtStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStatus2)))
                .addGap(20, 20, 20))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(painelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDispositivo)
                    .addComponent(caixaDispositivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoDispositivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVelocidade)
                    .addComponent(campoVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStatus)
                    .addComponent(txtStatus2))
                .addGap(18, 18, 18)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoConectar)
                    .addComponent(botaoDialogoComandos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCriador)
                    .addComponent(txtAviso))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoDispositivoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDispositivoMouseEntered
        botaoDispositivo.setBackground(preto);
        botaoDispositivo.setForeground(cinza);
    }//GEN-LAST:event_botaoDispositivoMouseEntered

    private void botaoDispositivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDispositivoMouseExited
        botaoDispositivo.setBackground(cinza);
        botaoDispositivo.setForeground(preto);
    }//GEN-LAST:event_botaoDispositivoMouseExited

    private void botaoDialogoComandosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDialogoComandosMouseEntered
        botaoDialogoComandos.setBackground(preto);
        botaoDialogoComandos.setForeground(cinza);
    }//GEN-LAST:event_botaoDialogoComandosMouseEntered

    private void botaoDialogoComandosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDialogoComandosMouseExited
        botaoDialogoComandos.setBackground(cinza);
        botaoDialogoComandos.setForeground(preto);
    }//GEN-LAST:event_botaoDialogoComandosMouseExited

    private void botaoConectarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoConectarMouseEntered
        botaoConectar.setBackground(branco);
        botaoConectar.setForeground(conectar);
    }//GEN-LAST:event_botaoConectarMouseEntered

    private void botaoConectarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoConectarMouseExited
        botaoConectar.setBackground(conectar);
        botaoConectar.setForeground(branco);
    }//GEN-LAST:event_botaoConectarMouseExited

    private void botaoDialogoComandosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDialogoComandosActionPerformed
        dialogoComandos.setLocation(this.getLocation());
        dialogoComandos.setVisible(true);
        
        new Thread() {
            
            public void run() {
                while (dialogoComandos.isVisible()){
                    thread.suspend();
                    txtAviso.setVisible(true);
                    if (conectado && "Botão".equals(tabelaComandos.getColumnName(tabelaComandos.getSelectedColumn()))) {
                        String leitura = comunicacao.ler();
                            if (leitura != null) {
                                tabelaComandos.setValueAt(leitura , tabelaComandos.getSelectedRow(), tabelaComandos.getSelectedColumn());
                            }
                    }
                }
                thread.resume();
                txtAviso.setVisible(false);
            }
        }.start();
    }//GEN-LAST:event_botaoDialogoComandosActionPerformed

    private void botaoDispositivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDispositivoActionPerformed
    
        caixaDispositivo.removeAllItems();
        caixaDispositivo.addItem("Selecione uma porta");
        
        for (String i: Comunicacao.listarPortas()) {
            caixaDispositivo.addItem(i);
        }
    }//GEN-LAST:event_botaoDispositivoActionPerformed

    private void botaoConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConectarActionPerformed
        if ("Conectar".equals(botaoConectar.getText())) {
            comunicacao.desconectar(String.valueOf(caixaDispositivo.getSelectedItem()), Integer.parseInt(campoVelocidade.getText()));
            if (comunicacao.conectar(this, String.valueOf(caixaDispositivo.getSelectedItem()), Integer.parseInt(campoVelocidade.getText()))){
                txtStatus2.setText("Conectado");
                txtStatus2.setForeground(verde);
                
                botaoConectar.setText("Desconectar");
                conectar = vermelho;
                botaoConectar.setBackground(vermelho);
                
                caixaDispositivo.setEnabled(false);
                campoVelocidade.setEnabled(false);
                botaoDispositivo.setEnabled(false);
                
                conectado = true;
                botaoDialogoComandos.setEnabled(true);
                
                thread = new Thread(this);
                thread.start();
            }
        } else {
            comunicacao.desconectar(String.valueOf(caixaDispositivo.getSelectedItem()), Integer.parseInt(campoVelocidade.getText()));
            
            txtStatus2.setText("Desconectado");
            txtStatus2.setForeground(vermelho);
            
            botaoConectar.setText("Conectar");
            conectar = verdeAzulado;
            botaoConectar.setBackground(verdeAzulado);
            
            caixaDispositivo.setEnabled(true);
            campoVelocidade.setEnabled(true);
            botaoDispositivo.setEnabled(true);
            
            conectado = false;
            dialogoComandos.setVisible(false);
            botaoDialogoComandos.setEnabled(false);
            
            thread.stop();
        }
    }//GEN-LAST:event_botaoConectarActionPerformed

    private void botaoFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoFecharMouseExited
        botaoFechar.setBackground(cinza);
        botaoFechar.setForeground(preto);
    }//GEN-LAST:event_botaoFecharMouseExited

    private void botaoFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoFecharMouseEntered
        botaoFechar.setBackground(preto);
        botaoFechar.setForeground(cinza);
    }//GEN-LAST:event_botaoFecharMouseEntered

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        dialogoComandos.setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConectar;
    private javax.swing.JButton botaoDialogoComandos;
    private javax.swing.JButton botaoDispositivo;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JComboBox<String> caixaDispositivo;
    private javax.swing.JFormattedTextField campoVelocidade;
    private javax.swing.JDialog dialogoComandos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelComandos;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelTitulo;
    private javax.swing.JTable tabelaComandos;
    private javax.swing.JLabel txtAviso;
    private javax.swing.JLabel txtCriador;
    private javax.swing.JLabel txtDispositivo;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtStatus2;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtVelocidade;
    // End of variables declaration//GEN-END:variables
}
