
package trabalhofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CtrlPublicacao {
    
    //lista com todas as publicações cadastradas na biblioteca
    private ArrayList<Publicacao> listaDePublicacoes = new ArrayList<>();
    
    public CtrlPublicacao(){
        try{abreListaPublicacoes();}
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo.");
        }
    }
    
    //construtor da classe com parâmetros
    public void cadastraPublicacao(int ISBN, String titulo, String autor, String editora){
        //instancia um objeto da classe Publicacao e coloca na lista
        Publicacao pub = new Publicacao(ISBN,titulo,autor,editora);
        this.listaDePublicacoes.add(pub);
        try{salvaListaPublicacoes();}
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao salvar publicações.");
        }
    }
    //busca uma publicacao com base no isbn
    public Publicacao buscaPublicacao(int isbn) throws Exception{
        Publicacao encontrado = null;
        for (Publicacao p : this.listaDePublicacoes) {
            if (p.getISBN() == isbn) {
                encontrado = p;
            }
        }
        if (encontrado == null) {
            throw new Exception("Não existe publicacao com o código especificado.");
        }
        return(encontrado);
    }
    public boolean buscaPublicacaoBoolean(int isbn) throws Exception{
        boolean encontrado = false;
        for (Publicacao p : this.listaDePublicacoes) {
            if (p.getISBN() == isbn) {
                encontrado = true;
            }
        }
        if (encontrado == false) {
            throw new Exception("Não existe publicacao com o código especificado.");
        }
        return(encontrado);
    }
    public void abreListaPublicacoes() throws Exception {
        File documentoPublicacao = new File("Publicações.dat");
        if (documentoPublicacao.exists()) {
            FileInputStream fileInput = new FileInputStream("Publicações.dat");
            ObjectInputStream objIS = new ObjectInputStream(fileInput);
            //salvaListaPublicacoes();
            listaDePublicacoes = (ArrayList<Publicacao>) objIS.readObject();
        }
    }

    //salva dados dos associados
    public void salvaListaPublicacoes() throws Exception {
        FileOutputStream fileOS = new FileOutputStream("Publicações.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
        objOS.writeObject(listaDePublicacoes);
        objOS.flush();
        objOS.close();
    }
    //método getter
        public ArrayList<Publicacao> getListaDePublicacoes() {
        return listaDePublicacoes;
    }
    
}    
    