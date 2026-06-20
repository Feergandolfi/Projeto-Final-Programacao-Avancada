package AULA.DESAFIO;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Moldura extends JFrame {

    public Moldura() {
        this.setTitle("Cap11 - Detecção de Colisão - parte 2");
        this.setLayout(new BorderLayout());

        Painel painelCentro = new Painel("Centro");
        Painel painelSul    = new Painel("Sul");

        this.add(painelCentro, BorderLayout.CENTER);
        this.add(painelSul,    BorderLayout.SOUTH);

        // Adiciona o teclado no JFrame também — garante que as teclas
        // funcionem mesmo que nenhum painel esteja com foco
        this.addKeyListener(painelCentro.ET);

        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}