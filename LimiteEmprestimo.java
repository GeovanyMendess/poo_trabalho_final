package trabalhofinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LimiteEmprestimo extends JFrame implements ActionListener {

    //painel que contém os elementos
    private JPanel panel;
    //botões para cancelar e confirmar empréstimo
    private JButton cadastrar, cancelar;
    //labels para indicarem a função de cada campo
    private JLabel lISBN, lData, lNumero, lCodigo;
    //campos para inserir os elementos
    private JTextField isbn, numero, codigo;
    //campos para data 
    private JComboBox dia, mes, ano;
    //Controle emprestimo para realizar as operações por referência
    private CtrlEmprestimo cAuxEmprestimo;
    //Controle Exemplar para acessar os exemplares e registrá-los como emprestados
    private CtrlExemplar cAuxExemplar;

    //construtor para iniciar a janela
    public LimiteEmprestimo() {

        super("Realiza Empréstimo");

        //inicializa o painel
        panel = new JPanel();
        //cria o layout do painel
        SpringLayout spring = new SpringLayout();
        //seta o painel com o layout criado
        panel.setLayout(spring);

        //inicializa os labels
        lISBN = new JLabel("ISBN");
        lData = new JLabel("Data (dd/mm/aaaa)");
        lNumero = new JLabel("Nº de exemplar");
        lCodigo = new JLabel("Código do associado");

        //inicializa os botões e coloca o listener
        cadastrar = new JButton("Registrar");
        cancelar = new JButton("Cancelar");
        
        cadastrar.addActionListener(this);
        cancelar.addActionListener(this);

        //inicializa os ComboBox
        dia = new JComboBox();
        mes = new JComboBox();
        ano = new JComboBox();

        for (int i = 1; i < 32; i++) {
            dia.addItem(i);
        }
        for (int i = 1; i < 13; i++) {
            mes.addItem(i);
        }
        for (int i = 1950; i < 2020; i++) {
            ano.addItem(i);
        }

        //inicializa os JTextFields        
        isbn = new JTextField(15);
        numero = new JTextField(15);
        codigo = new JTextField(15);

        //adiciona os campos de texto e labels ao painel e seta a posição deles
        panel.add(lISBN);
        panel.add(isbn);

        spring.putConstraint(SpringLayout.WEST, lISBN, 10, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, lISBN, 25, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, isbn, 25, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, isbn, 102, SpringLayout.EAST, lISBN);

        panel.add(lData);
        panel.add(dia);
        panel.add(mes);
        panel.add(ano);

        spring.putConstraint(SpringLayout.WEST, lData, 10, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, lData, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, dia, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, mes, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, ano, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, dia, 22, SpringLayout.EAST, lData);
        spring.putConstraint(SpringLayout.WEST, mes, 14, SpringLayout.EAST, dia);
        spring.putConstraint(SpringLayout.WEST, ano, 14, SpringLayout.EAST, mes);

        panel.add(lNumero);
        panel.add(numero);

        spring.putConstraint(SpringLayout.WEST, lNumero, 10, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, lNumero, 82, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, numero, 82, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, numero, 42, SpringLayout.EAST, lNumero);

        panel.add(lCodigo);
        panel.add(codigo);

        spring.putConstraint(SpringLayout.WEST, lCodigo, 10, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, lCodigo, 107, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, codigo, 107, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.EAST, lCodigo);

        panel.add(cadastrar);
        panel.add(cancelar);

        spring.putConstraint(SpringLayout.WEST, cadastrar, 75, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, 135, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, cancelar, 135, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, cancelar, 10, SpringLayout.EAST, cadastrar);

        this.add(panel);
        this.setSize(350, 210);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
             //referencia os controles exemplar que estarão na main
            cAuxEmprestimo = new CtrlEmprestimo();
            cAuxExemplar = new CtrlExemplar();
            int eDia=0, eMes=0, eAno=0, eCod=0, eNum=0, eIsbn=0;
            LocalDate date = null;
            eDia = ((int) dia.getSelectedItem());
            eMes = ((int) mes.getSelectedItem());
            eAno = ((int) ano.getSelectedItem());
            
            date = LocalDate.of(eAno, eMes, eDia);
            
            if (ValidaNumero(codigo, lCodigo) && ValidaNumero(numero, lNumero) && ValidaNumero(isbn, lISBN)) {
                eCod = Integer.parseInt(codigo.getText());
                eNum = Integer.parseInt(numero.getText());
                eIsbn = Integer.parseInt(isbn.getText());
            }
        if(!cAuxEmprestimo.isEmprestado(eIsbn, eNum)){
            cAuxEmprestimo.realizaEmprestimo(eNum, eIsbn, date, eCod);
        }
        else {
            JOptionPane.showMessageDialog(null,"O exemplar já se encontra emprestado.");
        }
        this.setVisible(false); 
        codigo.setText("");
        numero.setText("");
        isbn.setText("");

        }
        else{
            if(e.getSource()==cancelar){
                this.setVisible(false);
            }
        }
        
        
    }

    public boolean ValidaNumero(JTextField Numero, JLabel label) {
        int valor;
        try {
            valor = Integer.parseInt(Numero.getText());
        } catch (NumberFormatException numberError) {
            JOptionPane.showMessageDialog(null, "O campo " + label.getText() + " só aceita números", "Informação", JOptionPane.INFORMATION_MESSAGE);
            Numero.grabFocus();
            Numero.setText("");
            return (false);
        }
        return (true);
    }

}
