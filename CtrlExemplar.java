
package trabalhofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CtrlExemplar {

    //conjunto ArrayLists que contém, cada uma, listas dos exemplares de uma mesma publicação    
    private ArrayList<List<Exemplar>> listasDeExemplares = new ArrayList<List<Exemplar>>();

    public CtrlExemplar() {
        try {
            abreListasExemplares();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir lista de Exemplares.");
        }
    }

    public void cadastraExemplar(int ISBN, float preco) {
        int index = 0;
        List<Exemplar> e;
        Exemplar eExemplar;
        boolean existe = false;
        
        
        //verifica se já existe uma publicação com o ISBN especificado
        CtrlPublicacao cPublicacao = new CtrlPublicacao();
        for (Publicacao pub : cPublicacao.getListaDePublicacoes()) {
            if (pub.getISBN() == ISBN) {
                existe = true;
            }
        }
        if (existe) {
            //busca em listasDeExemplares para ver se já existe um ArrayList com exemplares da mesma publicação
            if (buscaExemplar(ISBN) == null) {
                e = new ArrayList<>();
                eExemplar = new Exemplar(index+1, ISBN, preco);
                e.add(eExemplar);
                this.listasDeExemplares.add(e);
            } else {
                e = buscaExemplar(ISBN);
                for (Exemplar elemento : e) {
                    index = elemento.getNumero();
                    index++;
                }
                eExemplar = new Exemplar(index, ISBN, preco);
                e.add(eExemplar);
            }
            JOptionPane.showMessageDialog(null, "Exemplar " + eExemplar.getNumero() + " cadastrado.");
            try {
                salvaListaExemplares();
            } catch (Exception c) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Não existe publicação com esse ISBN");
        }

    }

    public void abreListasExemplares() throws Exception {
        File documentoExemplares = new File("Exemplares.dat");
        if (documentoExemplares.exists()) {
            FileInputStream fileInput = new FileInputStream("Exemplares.dat");
            ObjectInputStream objIS = new ObjectInputStream(fileInput);
            listasDeExemplares = (ArrayList<List<Exemplar>>) objIS.readObject();
        }
    }

    //salva dados dos associados
    public void salvaListaExemplares() throws Exception {
        FileOutputStream fileOS = new FileOutputStream("Exemplares.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
        objOS.writeObject(listasDeExemplares);
        objOS.flush();
        objOS.close();
    }

    //método getter
    public ArrayList<List<Exemplar>> getListaDeExemplares() {
        return listasDeExemplares;
    }

    //busca a lista de exemplares de uma publicação
    public List<Exemplar> buscaExemplar(int ISBN) {
        List<Exemplar> encontrado = null;
        for (List<Exemplar> l : this.listasDeExemplares) {
            if (l.get(0).getISBN() == ISBN) {
                encontrado = l;
            }
        }
        return (encontrado);
    }

    //busca um exemplar específico com base no número
    public Exemplar buscaExemplarEsp(List<Exemplar> list, int numero) {
        Exemplar encontrado = null;
        for (Exemplar e : list) {
            if (e.getNumero() == numero) {
                encontrado = e;
            }
        }
        return (encontrado);
    }

    //verifica se um exemplar está emprestado com base no ISBN e no número do exemplar
    public boolean verificaEmprestimo(int ISBN, int numero) {
        boolean verifica = false;
        List<Exemplar> lista;
        lista = buscaExemplar(ISBN);
        Exemplar elementoInteresse = null;

        elementoInteresse = buscaExemplarEsp(lista, numero);

        CtrlEmprestimo cEmprestimo = new CtrlEmprestimo();
        for (Emprestimo e : cEmprestimo.getListaDeEmprestimos()) {
            if (e.getISBN() == ISBN) {
                if (e.getNumero() == numero) {
                    verifica = true;
                }
            }
        }

        return (verifica);
    }

}
