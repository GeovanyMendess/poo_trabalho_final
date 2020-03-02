
package trabalhofinal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestaReferencia extends JFrame implements ActionListener{

    private JButton B,B2;
    private CtrlAssociado cAuxAssociado;
    
    public TestaReferencia(CtrlAssociado cAssociado){
        
        super ("OIIIIIII");
        
        this.setLayout(new FlowLayout());
        
        cAuxAssociado = cAssociado;
        
        B2 = new JButton("Testa Busca");
        B2.addActionListener(this);
        
        B = new JButton("HELLO");
        //B.addActionListener(this);
        
        this.add(B);
        this.add(B2);
        this.setSize(300,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==B){
            JOptionPane.showMessageDialog(null,cAuxAssociado.getListaDeAssociados().get(0).getNome());
            this.setVisible(false);
        }  
    }
    
}
