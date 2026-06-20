package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Painel extends JPanel {

    private String Posicao;
    Player Jogador = new Player();
    GameLoop GL;
    EscutadorTeclado ET;
    SpriteLoop SL;
    tileMap cenario;
    Inventario      inventario;
    ArrayList<Item> itens;
    NPC             npc;

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

            inventario = new Inventario();

            itens = new ArrayList<>();
            itens.add(new Item("Espada", 240, 48));
            itens.add(new Item("Escudo", 48,  240));

            npc = new NPC(192, 192, "Bem-vindo, aventureiro!");

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
            for (int i = 0; i < itens.size(); i++) {
                itens.get(i).desenhar(D2);
            }
            npc.desenhar(D2);
            Jogador.DesenharPLayer(D2);
        } else {
            if (inventario != null) {
                inventario.desenhar(D2);
            }
        }
    }
}