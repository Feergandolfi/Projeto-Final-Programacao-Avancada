package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Painel extends JPanel {

    private String Posicao;
    Player Jogador = new Player();
    GameLoop GL;
    EscutadorTeclado ET;
    SpriteLoop SL;
    tileMap cenario;

    public Painel(String Posicao) {
        this.Posicao = Posicao;

        if (this.Posicao.equals("Centro")) {
            this.setBackground(Color.black);
            this.setPreferredSize(new Dimension(768, 480));

            ET = new EscutadorTeclado();
            this.addKeyListener(ET);
            this.setFocusable(true);

            // cenario criado ANTES das threads para evitar NullPointerException
            this.cenario = new tileMap();

            GL = new GameLoop(this, ET);
            GL.start();

            SL = new SpriteLoop(this, ET);
            SL.start();

        } else {
            this.setBackground(Color.yellow);
            this.setPreferredSize(new Dimension(768, 100));
        }
    }

    @Override
    public void paintComponent(Graphics D) {
        super.paintComponent(D);
        Graphics2D D2 = (Graphics2D) D;
        D2.setColor(this.getBackground());
        D2.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (this.Posicao.equals("Centro")) {
            this.cenario.desenhar(D2);
            Jogador.DesenharPLayer(D2);
        }
    }
}