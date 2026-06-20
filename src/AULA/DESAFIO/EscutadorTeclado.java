package AULA.DESAFIO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EscutadorTeclado implements KeyListener {

    public boolean movePraBaixo, movePraCima, movePraEsq, movePraDir;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)    movePraCima  = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)  movePraBaixo = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  movePraEsq   = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) movePraDir   = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)    movePraCima  = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)  movePraBaixo = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  movePraEsq   = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) movePraDir   = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NÃO SERÁ UTILIZADA, MAS NÃO PODE SER APAGADA
    }
}