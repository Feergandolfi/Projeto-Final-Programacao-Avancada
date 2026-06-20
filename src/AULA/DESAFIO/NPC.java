package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class NPC {

    int       posX, posY;
    Rectangle area;
    String    mensagem;
    boolean   falando;

    public NPC(int x, int y, String mensagem) {
        this.posX     = x;
        this.posY     = y;
        this.area     = new Rectangle(x, y, 48, 48);
        this.mensagem = mensagem;
        this.falando  = false;
    }

    public void desenhar(Graphics2D d2) {
        d2.setColor(Color.blue);
        d2.fillRect(this.posX, this.posY, 48, 48);
        if (this.falando) {
            d2.setColor(Color.white);
            d2.drawString(this.mensagem, this.posX, this.posY - 5);
        }
    }
}
