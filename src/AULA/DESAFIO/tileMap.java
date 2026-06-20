package AULA.DESAFIO;

import java.awt.Graphics2D;

public class tileMap {

    tiles pecaDoCenario;
    int[][] cenarioValido;

    // =====================================================
    // LEGENDA:
    //   0 = parede de tijolo  (wall1.png)
    //   1 = areia             (sand1.png)
    //   2 = água              (water1.png)
    //   3 = grama             (grass1.png)
    //   4 = branco / portal   (white.png)
    //   5 = pedra cinza       (gray.png)
    // =====================================================

    // CENÁRIO 1 — do capítulo anterior
    int[][] cenario1DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 3, 3, 3, 0, 3, 3, 2, 2, 3, 3, 0, 3, 3, 3, 0},
        {0, 3, 0, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 0, 3, 0},
        {0, 3, 3, 3, 3, 0, 3, 2, 2, 3, 0, 3, 3, 3, 3, 0},
        {0, 0, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 0, 0},
        {0, 3, 3, 3, 3, 3, 1, 1, 1, 1, 3, 3, 3, 3, 3, 0},
        {0, 0, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 0, 0},
        {0, 3, 3, 3, 3, 0, 3, 2, 2, 3, 0, 3, 3, 3, 3, 0},
        {0, 3, 0, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 0, 4, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    // CENÁRIO 2 — do capítulo anterior
    int[][] cenario2DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 0},
        {0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0},
        {0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0},
        {0, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 0},
        {0, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 0},
        {0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0},
        {0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0},
        {0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 4, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    // CENÁRIO 3 — novo (Cap11, conforme PDF)
    int[][] cenario3DoJogo = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 2, 2, 0},
        {0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 1, 0},
        {0, 1, 1, 1, 1, 3, 3, 2, 3, 2, 2, 2, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 3, 3, 2, 3, 2, 2, 2, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 2, 2, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0}
    };

    public tileMap() {
        this.cenarioValido = this.cenario3DoJogo;
        this.pecaDoCenario = new tiles();
    }

    public void desenhar(Graphics2D d2) {
        int pecaDaMatriz;
        for (int col = 0; col < this.cenarioValido[0].length; col++) {
            for (int lin = 0; lin < this.cenarioValido.length; lin++) {
                pecaDaMatriz = this.cenarioValido[lin][col];
                this.pecaDoCenario.caregaPecaDaMatriz(pecaDaMatriz);
                this.pecaDoCenario.desenhaTile(d2, lin, col);
            }
        }
    }
}