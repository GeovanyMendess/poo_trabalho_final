
package trabalhofinal;

import java.io.Serializable;

public class Exemplar implements Serializable{
    private int numero;
    private int ISBN;
    private double preco;
    private boolean emprestimo;

    public boolean isEmprestimo() {
        return emprestimo;
    }

    public Exemplar(int numero,int ISBN, double preco) {
        this.numero = numero;
        this.ISBN = ISBN;
        this.preco = preco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    
}
