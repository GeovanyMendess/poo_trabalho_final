package trabalhofinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LimitePrincipal extends JFrame implements ActionListener {
    //menu com os elementos
    private JMenu menu;
    //barra com elementos do menu
    private JMenuBar menuBar;
    //itens do menu
    private JMenuItem cadastrarExemplar, cadastrarAssociado, cadastrarPublicacao,
            emprestar, devolucao, relatorio, consulta;
    //separators 
    private JSeparator J1, J2, J3;
    //limites para serem chamados pelo limite principal
    private LimiteAssociado limAuxAssociado;
    private LimitePublicacao limAuxPublicacao;
    private LimiteExemplar limAuxExemplar;
    private LimiteEmprestimo limAuxEmprestimo;
    //auxiliares para consultar publicação
    private JButton ok;
    private JTextField aux;
    private JFrame auxF;

    //construtor 
    public LimitePrincipal() {

        super("Menu principal");

        limAuxAssociado = new LimiteAssociado();
        limAuxPublicacao = new LimitePublicacao();
        limAuxExemplar = new LimiteExemplar();
        limAuxEmprestimo = new LimiteEmprestimo();

        //inicializa o menu e a caixa que contém os elementos do menu
        menu = new JMenu("Menu", true);
        menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        //inicializa os itens do menu
        cadastrarAssociado = new JMenuItem("Cadastrar Associado");
        cadastrarPublicacao = new JMenuItem("Cadastrar Publicação");
        cadastrarExemplar = new JMenuItem("Cadastrar Exemplar");

        menu.add(cadastrarAssociado);
        menu.add(cadastrarPublicacao);
        menu.add(cadastrarExemplar);
        J1 = new JSeparator();
        J2 = new JSeparator();
        J3 = new JSeparator();

        menu.add(J1);

        emprestar = new JMenuItem("Realizar Empréstimo");
        devolucao = new JMenuItem("Realizar Devolução");
        menu.add(emprestar);
        menu.add(devolucao);

        menu.add(J2);

        consulta = new JMenuItem("Consulta Publicação");
        relatorio = new JMenuItem("Gerar Relatório");

        menu.add(consulta);
        menu.add(relatorio);

        menu.add(J3);

        menuBar.add(menu);

        //adiciona os listeners
        cadastrarAssociado.addActionListener(this);
        cadastrarPublicacao.addActionListener(this);
        cadastrarExemplar.addActionListener(this);
        consulta.addActionListener(this);
        relatorio.addActionListener(this);
        emprestar.addActionListener(this);
        devolucao.addActionListener(this);
        
        //inserindo a imagem do menu principal
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("biblioteca.JPG");
        label.setIcon(icon);
        
        this.add(label);

        this.setSize(1375, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrarAssociado) {
            limAuxAssociado.setVisible(true);
        } else {
            if (e.getSource() == cadastrarPublicacao) {
                limAuxPublicacao.setVisible(true);
            } else {
                if (e.getSource() == cadastrarExemplar) {
                    limAuxExemplar.setVisible(true);
                } else {
                    if (e.getSource() == emprestar) {
                        limAuxEmprestimo.setVisible(true);
                    } else {
                        if (e.getSource() == consulta) {

                            JFrame J = new JFrame();
                            JLabel label = new JLabel("ISBN");
                            JPanel p = new JPanel();
                            JTextField t = new JTextField(15);
                            SpringLayout spring = new SpringLayout();
                            p.setLayout(spring);
                            JButton b = new JButton("OK");
                            b.addActionListener(this);
                            p.add(label);
                            p.add(t);
                            p.add(b);
                            spring.putConstraint(SpringLayout.WEST, label, 15, SpringLayout.WEST, p);
                            spring.putConstraint(SpringLayout.NORTH, label, 25, SpringLayout.NORTH, p);
                            spring.putConstraint(SpringLayout.NORTH, t, 25, SpringLayout.NORTH, p);
                            spring.putConstraint(SpringLayout.WEST, t, 20, SpringLayout.EAST, label);
                            spring.putConstraint(SpringLayout.WEST, b, 105, SpringLayout.WEST, p);
                            spring.putConstraint(SpringLayout.NORTH, b, 50, SpringLayout.NORTH, p);
                            ok = b;
                            aux=t;
                            auxF=J;
                            J.add(p);
                            J.setSize(270, 125);
                            J.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            J.setVisible(true);
                        } else {
                            if (e.getSource() == devolucao) {
                                CtrlPrincipal c = new CtrlPrincipal();
                                c.realizaDevolucao();
                            } else {
                                if (e.getSource() == relatorio) {
                                    CtrlPrincipal c = new CtrlPrincipal();
                                    c.geraRelatorio();
                                } else {
                                    if (e.getSource() == ok) {
                                        CtrlPrincipal c = new CtrlPrincipal();
                                        c.consultaPublicacao(Integer.parseInt(aux.getText()));
                                        auxF.setVisible(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
