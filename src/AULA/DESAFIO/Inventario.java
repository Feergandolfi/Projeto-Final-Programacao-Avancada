package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Inventario {

    ArrayList<String> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionar(String nome) {
        this.itens.add(nome);
    }

    public boolean contemItem(String nome) {
        return this.itens.contains(nome);
    }

    public void desenhar(Graphics2D d2) {
        d2.setColor(Color.black);
        d2.drawString("Inventario:", 10, 20);
        for (int i = 0; i < this.itens.size(); i++) {
            d2.drawString("- " + this.itens.get(i), 10, 40 + i * 20);
        }
    }
}
