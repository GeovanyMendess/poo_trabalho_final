
package trabalhofinal;

import java.io.Serializable;


public class Associado implements Constantes, Serializable{
  private int codigo;
  private String nome;
  private String endereco;
  private String email;
  private String status;
  
    

    public Associado(){
    }
    public Associado(int codigo, String nome, String endereco, String email, String status) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     public int dias(){
         if(this.status.equals(GRADUACAO)){
             return(7);
         }
         else{
             if(this.status.equals(POS)){
                 return(10);
             }
             else{
                 return(14);
             }
         }
     } 
}
