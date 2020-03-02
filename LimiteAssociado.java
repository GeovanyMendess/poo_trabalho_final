package trabalhofinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LimiteAssociado extends JFrame implements Constantes, ActionListener {

    //campos para inserção do código, nome, endereço e e-mail do associado;
    private JTextField inCod, inName, inEnd, inEmail;
    //comboBox com os status disponíveis
    private JComboBox inStatus;
    //botões para chamar o método de cadastro ou fechar a janela
    private JButton cadastrar, cancelar;
    //labels com o que deve ser preenchido em cada campo
    private JLabel label1, label2, label3, label4, label5;
    //painel com botões, campos e informações
    private JPanel p;
    //controle do Associado usado como auxiliar
    private CtrlAssociado cAuxAssociado;

    public LimiteAssociado() {

        super("Campo para cadastro de associado.");

        p = new JPanel();
        SpringLayout spring = new SpringLayout();
        p.setLayout(spring);
        
        label1 = new JLabel("Código");
        inCod = new JTextField(30);
        

        label2 = new JLabel("Nome");
        inName = new JTextField(30);
       
        label3 = new JLabel("Endereço");
        inEnd = new JTextField(30);
       

        label4 = new JLabel("E-mail");
        inEmail = new JTextField(30);
       

        label5 = new JLabel("Status");

        inStatus = new JComboBox();
        inStatus.addItem(Constantes.GRADUACAO);
        inStatus.addItem(Constantes.POS);
        inStatus.addItem(Constantes.PROFESSOR);

        

        cadastrar = new JButton("Cadastrar");
        cadastrar.addActionListener(this);
       

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);
       
        //adiciona os campos de texto e labels ao painel e seta a posição
        p.add(label1);
        p.add(inCod);
        spring.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inCod, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inCod, 35, SpringLayout.EAST, label1);
        
        p.add(label2);
        p.add(inName);
        spring.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label2, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inName, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inName, 41, SpringLayout.EAST, label2);
        
        
        p.add(label3);
        p.add(inEnd);
        spring.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label3, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inEnd, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inEnd, 20, SpringLayout.EAST, label3);
        
        p.add(label4);
        p.add(inEmail);
        spring.putConstraint(SpringLayout.WEST, label4, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label4, 100, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inEmail, 100, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inEmail, 39, SpringLayout.EAST, label4);
        
        p.add(label5);
        p.add(inStatus);
        spring.putConstraint(SpringLayout.WEST, label5, 150, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label5, 140, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inStatus, 140, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inStatus, 20, SpringLayout.EAST, label5);
        
        p.add(cadastrar);
        p.add(cancelar);
        spring.putConstraint(SpringLayout.WEST, cadastrar, 110, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, 180, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, cancelar, 180, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, cancelar, 20, SpringLayout.EAST, cadastrar);
        
        this.add(p);

        this.setSize(450, 275);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void fechaJanela() {
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
            cAuxAssociado = new CtrlAssociado();
            if(ValidaNumero(inCod)){
            int cod = Integer.parseInt(inCod.getText());
            String nome = inName.getText(), endereco = inEnd.getText(), email = inEmail.getText();
            String status = inStatus.getSelectedItem().toString();
            cAuxAssociado.cadastraAssociado(cod, nome, endereco, email, status);
            this.setVisible(false);
            inName.setText("");
            inEnd.setText("");
            inEmail.setText("");
            inCod.setText("");
            }
        } 
        if (e.getSource()== cancelar){
            this.setVisible(false);
            inName.setText("");
            inEnd.setText("");
            inEmail.setText("");
            inCod.setText("");
        }
    }

    public boolean ValidaNumero(JTextField Numero) {
        int valor;    
        try {
                valor = Integer.parseInt(Numero.getText());
            } catch (NumberFormatException numberError) {
                JOptionPane.showMessageDialog(null, "O campo código só aceita números", "Informação", JOptionPane.INFORMATION_MESSAGE);
                Numero.grabFocus();
                Numero.setText("");
                return(false);
            }
        return(true);
    }

    public CtrlAssociado getcAuxAssociado() {
        return cAuxAssociado;
    }

}
