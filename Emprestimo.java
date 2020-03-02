
package trabalhofinal;

import java.io.Serializable;
import java.time.LocalDate;



public class Emprestimo implements Serializable {
    private int numero;
    private int ISBN;
    private LocalDate data;
    private int codigo;

    public Emprestimo(int numero, int ISBN, LocalDate data, int codigo) {
        this.numero = numero;
        this.ISBN = ISBN;
        this.data = data;
        this.codigo = codigo;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
