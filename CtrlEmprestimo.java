package trabalhofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;

public class CtrlEmprestimo implements Constantes {

    private ArrayList<Emprestimo> listaDeEmprestimos = new ArrayList<>();

    //Construtor
    public CtrlEmprestimo() {
        try {
            abreEmprestimos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir lista de Empréstimos.");
        }
    }

    //método para realizar o empréstimo e salvá-lo
    public void realizaEmprestimo(int numero, int ISBN, LocalDate data, int codigo) {
        //verifica se um exemplar existe
        Exemplar encontrado = null;
        List<Exemplar> lista = null;
        CtrlExemplar cExemplar = new CtrlExemplar();
        for (List<Exemplar> l : cExemplar.getListaDeExemplares()) {
            if (l.get(0).getISBN() == ISBN) {
                for (Exemplar e : l) {
                    if (e.getNumero() == numero) {
                        encontrado = e;
                    }
                }
            }
        }
        if (encontrado != null) {
            Emprestimo pEmprestimo = new Emprestimo(numero, ISBN, data, codigo);
            this.listaDeEmprestimos.add(pEmprestimo);
            try {
                salvaEmprestimos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar empréstimos.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"O exemplar não se encontra cadastrado na biblioteca.");
        }

    }

    //busca um empréstimo com base no código do associado
    public Emprestimo buscaEmprestimo(int cod) throws Exception {
        Emprestimo encontrado = null;
        for (Emprestimo eEmprestimo : listaDeEmprestimos) {
            if (eEmprestimo.getCodigo() == cod) {
                encontrado = eEmprestimo;
            }
        }
        if (encontrado == null) {
            throw new Exception("Não existe nenhum empréstimo aquivado desse associado");
        }
        return (encontrado);
    }

    //verifica se um Exemplar está emprestado com base na lista de empréstimos
    public boolean isEmprestado(int ISBN, int numero) {
        boolean emprestado = false;
        for (Emprestimo eEmprestimo : listaDeEmprestimos) {
            if (eEmprestimo.getISBN() == ISBN) {
                if (eEmprestimo.getNumero() == numero) {
                    emprestado = true;
                }
            }
        }
        return (emprestado);
    }
    //função para listar os Exemplares que estão com devolução atrasada

    //salva em arquivo os empréstimos realizados
    public void salvaEmprestimos() throws Exception {
        FileOutputStream fileOS = new FileOutputStream("Empréstimos.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
        objOS.writeObject(listaDeEmprestimos);
        objOS.flush();
        objOS.close();
    }

    //abre o arquivo com os empréstimos realizados
    public void abreEmprestimos() throws Exception {
        File documentoEmprestimos = new File("Empréstimos.dat");
        if (documentoEmprestimos.exists()) {
            FileInputStream fileInput = new FileInputStream("Empréstimos.dat");
            ObjectInputStream objIS = new ObjectInputStream(fileInput);
            //salvaEmprestimos();
            listaDeEmprestimos = (ArrayList<Emprestimo>) objIS.readObject();
        }
    }

    public ArrayList<Emprestimo> getListaDeEmprestimos() {
        return listaDeEmprestimos;
    }

}
