package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Item {

    String    nome;
    int       posX, posY;
    Rectangle area;
    boolean   coletado;

    public Item(String nome, int x, int y) {
        this.nome     = nome;
        this.posX     = x;
        this.posY     = y;
        this.area     = new Rectangle(x, y, 48, 48);
        this.coletado = false;
    }

    public void desenhar(Graphics2D d2) {
        if (!this.coletado) {
            d2.setColor(Color.yellow);
            d2.fillRect(this.posX, this.posY, 48, 48);
        }
    }
}
