package trabalhofinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class CtrlPrincipal implements ActionListener, Constantes {

    private JTextField tISBN;
    private JTextField tNumero;
    private JButton confirmar;
    private JPanel panel;
    private JFrame frame;

    public CtrlPrincipal() {

    }

    public void realizaDevolucao() {
        //janela que aparece para inserção dos dados da devolução
        JFrame devolucao = new JFrame("Devolução de livro");

        frame = devolucao;

        JLabel isbn = new JLabel("ISBN");
        JLabel numero = new JLabel("Número");

        tISBN = new JTextField(15);
        tNumero = new JTextField(15);

        SpringLayout spring = new SpringLayout();

        panel = new JPanel();
        panel.setLayout(spring);

        confirmar = new JButton("Confirmar");
        confirmar.addActionListener(this);

        panel.add(numero);
        panel.add(tNumero);
        spring.putConstraint(SpringLayout.WEST, numero, 15, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, numero, 25, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, tNumero, 25, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, tNumero, 14, SpringLayout.EAST, numero);

        panel.add(isbn);
        panel.add(tISBN);
        spring.putConstraint(SpringLayout.WEST, isbn, 15, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, isbn, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.NORTH, tISBN, 50, SpringLayout.NORTH, panel);
        spring.putConstraint(SpringLayout.WEST, tISBN, 32, SpringLayout.EAST, isbn);

        panel.add(confirmar);
        spring.putConstraint(SpringLayout.WEST, confirmar, 90, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, confirmar, 75, SpringLayout.NORTH, panel);

        devolucao.add(panel);
        devolucao.setSize(270, 170);
        devolucao.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        devolucao.setVisible(true);

    }

    public void devolveEmprestimo() {
        Emprestimo eEmprestimo = null;
        CtrlEmprestimo cEmprestimo = new CtrlEmprestimo();
        for (Emprestimo e : cEmprestimo.getListaDeEmprestimos()) {
            if (e.getISBN() == Integer.parseInt(tISBN.getText())
                    && e.getNumero() == Integer.parseInt(tNumero.getText())) {
                eEmprestimo = e;
            }
        }
        verificaAtraso(eEmprestimo.getData(), eEmprestimo.getCodigo());
        cEmprestimo.getListaDeEmprestimos().remove(eEmprestimo);
        try {
            cEmprestimo.salvaEmprestimos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //verifica se há atraso com base na data do empréstimo e código do associado
    //e mostra na tela o valor da multa se for o caso
    public void verificaAtraso(LocalDate dataA, int codigo) {
        Period tempo = Period.between(dataA, LocalDate.now());
        long dias;
        dias = tempo.getDays() + 365 * tempo.getYears() + 30 * tempo.getMonths();
        CtrlAssociado cAssociado = new CtrlAssociado();
        Associado encontrado = null;
        for (Associado a : cAssociado.getListaDeAssociados()) {
            if (a.getCodigo() == codigo) {
                encontrado = a;
            }
        }
        if (encontrado.getStatus().equals(Constantes.GRADUACAO)) {
            if (dias > 7) {
                JOptionPane.showMessageDialog(null, "Multa a ser paga: R$" + (dias - 7) + ",00");
            }
        } else {
            if (encontrado.getStatus().equals(Constantes.POS)) {
                if (dias > 10) {
                    JOptionPane.showMessageDialog(null, "Multa a ser paga: R$" + (dias - 10) + ",00");
                }
            } else {
                if (dias > 14) {
                    JOptionPane.showMessageDialog(null, "Multa a ser paga: R$" + (dias - 14) + ",00");
                }
            }
        }

    }

    public void consultaPublicacao(int ISBN) {
        JTextArea area = new JTextArea();
        String conteudo = "";
        List<Exemplar> lista;
        CtrlExemplar cExemplar = new CtrlExemplar();
        lista = cExemplar.buscaExemplar(ISBN);

        for (Exemplar e : lista) {
            if (cExemplar.verificaEmprestimo(ISBN, e.getNumero())) {
                conteudo = conteudo + "Exemplar " + e.getNumero() + ": Emprestado\n";
            } else {
                conteudo = conteudo + "Exemplar " + e.getNumero() + ": Disponível\n";
            }
        }
        area.setText(conteudo);
        JOptionPane.showMessageDialog(null, area, "Exemplares da Publicação: ", JOptionPane.INFORMATION_MESSAGE);

    }

    public void geraRelatorio() {
        Period tempo;
        int dias;
        String relatorio = "";
        CtrlEmprestimo cEmprestimo = new CtrlEmprestimo();
        CtrlAssociado cAssociado = new CtrlAssociado();
        CtrlPublicacao cPublicacao = new CtrlPublicacao();
        Associado encontrado = null;
        Publicacao pub = null;
        JTextArea area = new JTextArea();
        for (Emprestimo e : cEmprestimo.getListaDeEmprestimos()) {
            for (Publicacao p : cPublicacao.getListaDePublicacoes()) {
                if (e.getISBN() == p.getISBN()) {
                    pub = p;
                }                
                for (Associado a : cAssociado.getListaDeAssociados()) {
                    if (a.getCodigo() == e.getCodigo()) {
                        encontrado = a;
                    }
                    else {encontrado = null;}
                    tempo = Period.between(e.getData(), LocalDate.now());
                    dias = tempo.getDays();
                    if ((encontrado != null)) {
                        if (encontrado.getStatus().equals(Constantes.GRADUACAO)) {
                            if (dias > 7) {
                                relatorio = relatorio + "Nome: " + encontrado.getNome() + "\t"
                                        + "Código: " + encontrado.getCodigo() + "\t" + " Publicação: " + pub.getTitulo() + "\n";
                                
                            }
                        } else {
                            if (encontrado.getStatus().equals(Constantes.POS)) {
                                if (dias > 10) {
                                    relatorio = relatorio + "Nome: " + encontrado.getNome() + "\t"
                                            + "Código: " + encontrado.getCodigo()+ "\t" + " Publicação: " + pub.getTitulo() + "\n";
                                    
                                }
                            } else {
                                if (dias > 14) {
                                    relatorio = relatorio + "Nome: " + encontrado.getNome() + "\t"
                                            + "Código: " + encontrado.getCodigo() + "\t" +" Publicação: " + pub.getTitulo() + "\n";
                                   
                                }
                            }
                        }
                    }

                }
            
            }

        }
        area.setText(relatorio);
        JOptionPane.showMessageDialog(null, area, "Exemplares da Publicação: ", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmar) {
            devolveEmprestimo();
            frame.setVisible(false);

        }
    }

}
