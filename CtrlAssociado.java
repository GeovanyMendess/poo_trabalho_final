package trabalhofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CtrlAssociado{

    private ArrayList<Associado> listaDeAssociados = new ArrayList<>();

    public CtrlAssociado(){
        try{abreListaAssociados();}
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo.");
        };
    }

    public void cadastraAssociado(int codigo, String nome, String endereco, String email, String status) {
        Associado novoAssociado = new Associado(codigo, nome, endereco, email, status);
        this.listaDeAssociados.add(novoAssociado);
        try {salvaListaAssociados();}
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Foi impossível salvar os dados.");
        }
    }

    //busca um associado na listaDeAssociados com base no código retorna ele
    public Associado buscaAssociado(int codigo) throws Exception {
        Associado encontrado = null;
        for (Associado a : this.listaDeAssociados) {
            if (a.getCodigo() == codigo) {
                encontrado = a;
            }
        }
        if (encontrado == null) {
            throw new Exception("Não existe associado com o código especificado.");
        }
        return (encontrado);
    }

    //método getter
    public ArrayList<Associado> getListaDeAssociados() {
        return listaDeAssociados;
    }

    //lê dados dos associados salvos
    public void abreListaAssociados() throws Exception {
        File documentoAssociados = new File("Associados.dat");
        if (documentoAssociados.exists()) {
            FileInputStream fileInput = new FileInputStream("Associados.dat");
            ObjectInputStream objIS = new ObjectInputStream(fileInput);
            //salvaListaAssociados();
            listaDeAssociados = (ArrayList<Associado>) objIS.readObject();
        }
    }

    //salva dados dos associados
    public void salvaListaAssociados() throws Exception {
        FileOutputStream fileOS = new FileOutputStream("Associados.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
        objOS.writeObject(listaDeAssociados);
        objOS.flush();
        objOS.close();
    }
}
