package trabalhofinal;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LimitePublicacao extends JFrame implements ActionListener {

    //botões de cadastro
    private JButton cadastrar, cancelar;
    //labels com o texto de cada campo
    private JLabel label1, label2, label3, label4;
    //campos de texto para inserção das informações
    private JTextField inISBN, inTitulo, inAutor, inEditora;
    //painel para os componentes
    private JPanel p;
    //controle publicacao para realizar o cadastro
    private CtrlPublicacao cAuxPublicacao;

    public LimitePublicacao() {

        super("Cadastramento de publicação");
        
        //cria o painel e seta o layout
        p = new JPanel();
        SpringLayout spring = new SpringLayout();
        p.setLayout(spring);

        //instancia os componentes
        inISBN = new JTextField(20);
        label1 = new JLabel("ISBN");
        inTitulo = new JTextField(20);
        label2 = new JLabel("Título");
        inAutor = new JTextField(20);
        label3 = new JLabel("Autor");
        inEditora = new JTextField(20);
        label4 = new JLabel("Editora");
        cadastrar = new JButton("Cadastrar");
        cancelar = new JButton("Cancelar");
        //adiciona os listeners
        cadastrar.addActionListener(this);
        cancelar.addActionListener(this);
        //adiciona os componentes
        p.add(label1);
        p.add(inISBN);
        spring.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inISBN, 25, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inISBN, 20, SpringLayout.EAST, label1);
        p.add(label2);
        p.add(inTitulo);
        spring.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label2, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inTitulo, 50, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inTitulo, 16, SpringLayout.EAST, label2);
        p.add(label3);
        p.add(inAutor);
        spring.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label3, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inAutor, 75, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inAutor, 16, SpringLayout.EAST, label3);
        p.add(label4);
        p.add(inEditora);
        spring.putConstraint(SpringLayout.WEST, label4, 10, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, label4, 100, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, inEditora, 100, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, inEditora, 7, SpringLayout.EAST, label4);
        p.add(cadastrar);
        p.add(cancelar);
        spring.putConstraint(SpringLayout.WEST, cadastrar, 60, SpringLayout.WEST, p);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, 135, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.NORTH, cancelar, 135, SpringLayout.NORTH, p);
        spring.putConstraint(SpringLayout.WEST, cancelar, 7, SpringLayout.EAST, cadastrar);

        this.add(p);

        this.setSize(310, 225);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
             cAuxPublicacao = new CtrlPublicacao();
            if (ValidaNumero(inISBN)) {
                int isbn = Integer.parseInt(inISBN.getText());
                String titulo = inTitulo.getText(), autor = inAutor.getText(), editora = inEditora.getText();
                cAuxPublicacao.cadastraPublicacao(isbn, titulo, autor, editora);
                this.setVisible(false);
                inTitulo.setText("");
                inAutor.setText("");
                inEditora.setText("");
                inISBN.setText("");

            }
        } 
        if (e.getSource()==cancelar){
            this.setVisible(false);
            inTitulo.setText("");
            inAutor.setText("");
            inEditora.setText("");
            inISBN.setText("");
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
            return (false);
        }
        return (true);
    }
}


