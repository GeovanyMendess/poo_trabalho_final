package trabalhofinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LimiteExemplar extends JFrame implements ActionListener {

    //label com os indicadores dos campos
    private JLabel label1, label2;
    //campos para serem preenchidos 
    private JTextField ISBN, preco;
    //botões para cadastrar exemplar
    private JButton cadastrar, cancelar;
    //painel para inserir elementos
    private JPanel p;
    //controle exemplar como referência
    private CtrlExemplar cAuxExemplar;
    //controle publicação para gerar relatório

    public LimiteExemplar() {

        super("Cadastra Exemplar");


        p = new JPanel();
        SpringLayout spring = new SpringLayout();
        p.setLayout(spring);
        //inserção de labels e campos de texto         
        label1 = new JLabel("ISBN");
        ISBN = new JTextField(20);
        //cria botões e adiciona o Listener
        cadastrar = new JButton("Cadastrar");
        cancelar = new JButton("Cancelar");

        cadastrar.addActionListener(this);
        cancelar.addActionListener(this);

        p.add(label1);
        p.add(ISBN);
        spring.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, ISBN, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, ISBN, 35, SpringLayout.EAST, label1);
        label2 = new JLabel("Preço");
        preco = new JTextField(20);
        p.add(label2);
        p.add(preco);
        spring.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label2, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, preco, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, preco, 28, SpringLayout.EAST, label2);

        p.add(cadastrar);
        p.add(cancelar);
        spring.putConstraint(SpringLayout.WEST, cadastrar, 85, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, cancelar, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, cancelar, 20, SpringLayout.EAST, cadastrar);

        this.add(p);

        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
            
            cAuxExemplar = new CtrlExemplar();
            int aISBN;
            float aPreco;
            if(validaNumero(ISBN,preco)){
                aISBN = Integer.parseInt(ISBN.getText());
                aPreco = Float.parseFloat(preco.getText());
                cAuxExemplar.cadastraExemplar(aISBN, aPreco);
                this.setVisible(false);
                ISBN.setText("");
                preco.setText("");
        }
        }
        if (e.getSource() == cancelar) {
            this.setVisible(false);
            ISBN.setText("");
            preco.setText("");
        }
    }
     public boolean validaNumero(JTextField Numero, JTextField Numero2) {
        int valor;
        float valor2;
        try {
            valor = Integer.parseInt(Numero.getText());
            valor2 = Float.parseFloat(Numero2.getText());
        } catch (NumberFormatException numberError) {
            JOptionPane.showMessageDialog(null, "Os campos só aceitam números", "Informação", JOptionPane.INFORMATION_MESSAGE);
            Numero.grabFocus();
            Numero.setText("");
            return (false);
        }
        return (true);
    }
    
}


