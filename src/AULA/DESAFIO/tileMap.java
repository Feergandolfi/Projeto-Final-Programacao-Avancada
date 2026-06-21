package AULA.DESAFIO;

import java.awt.Graphics2D;

public class tileMap {

    tiles pecaDoCenario;
    int[][] cenarioValido;
    int     cenarioAtual;

    // =====================================================
    // LEGENDA:
    //   0 = parede de tijolo  (wall1.png)
    //   1 = areia             (sand1.png)
    //   2 = água              (water1.png)
    //   3 = grama             (grass1.png)
    //   4 = branco / portal   (white.png)
    //   5 = pedra cinza       (gray.png)
    // =====================================================

 // CENÁRIO 1 — Corrigido com caminho aberto até o portal
    int[][] cenario1DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 3, 3, 3, 0, 3, 3, 2, 2, 3, 3, 0, 3, 3, 3, 0},
        {0, 3, 0, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 0, 3, 0},
        {0, 3, 3, 3, 3, 0, 3, 2, 2, 3, 0, 3, 3, 3, 3, 0},
        {0, 0, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 0, 0},
        {0, 3, 3, 3, 3, 3, 1, 1, 1, 1, 3, 3, 3, 3, 3, 0},
        {0, 0, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 0, 0},
        {0, 3, 3, 3, 3, 0, 3, 2, 2, 3, 0, 3, 3, 3, 3, 0}, // linha 7 liberada na direita
        {0, 3, 0, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 4, 4}, // mudado de 0 para 3 antes do 4
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

 // CENÁRIO 2 — Corrigido para bater exatamente com a imagem real do jogo
    int[][] cenario2DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Entrada livre por cima se necessário
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 2, 2, 0}, // Caminho da esquerda livre (onde o player surge)
        {1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 1, 0},
        {0, 1, 1, 1, 1, 3, 3, 2, 3, 2, 2, 2, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 3, 3, 2, 3, 2, 2, 2, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3, 3,  0}, // Penúltima linha: liberando o final
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0}  // Portal (4) posicionado na saída correta
   
    };

 // CENÁRIO 3 — Corrigido com caminho aberto e portal na saída
    int[][] cenario3DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0},
        {0, 1, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 0, 0},
        {0, 1, 2, 2, 2, 1, 1, 1, 1, 0, 3, 3, 3, 2, 2, 0}, // Entrada livre na esquerda
        {1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 3, 3, 3, 3, 1, 0},
        {0, 1, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3, 3, 1, 0},
        {0, 1, 2, 2, 2, 3, 3, 2, 3, 2, 2, 2, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 3, 3, 2, 3, 2, 2, 2, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3, 3, 0}, // Aberto o final da linha 8
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0}  // Portal (4) posicionado no mesmo padrão
    };

    // CENÁRIO 4 — Labirinto de Pedras (Portal assume o piso de Grama).
    int[][] cenario4DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 3, 5, 5, 3, 3, 5, 5, 3, 3, 5, 5, 3, 3, 3, 0},
        {0, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 0},
        {0, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 0},
        {0, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 3, 3, 0},
        {0, 3, 5, 5, 3, 3, 5, 5, 3, 3, 5, 5, 3, 3, 3, 0},
        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0}, // Corredor limpo até o final
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0}  // Saída disfarçada de grama
    };

 // CENÁRIO 5 — Rio com Ponte (Portal assume o piso de Grama)
    int[][] cenario5DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 0},
        {3, 3, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 0},
        {0, 3, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 3, 0},
        {0, 3, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 3, 0},
        {0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 0},
        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0}
    };

 // CENÁRIO 6 — Castelo Final 
    int[][] cenario6DoJogo = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
        {0, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0},
        {0, 5, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 5, 0},
        {0, 3, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 5, 0},
        {0, 3, 3, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 3, 5, 0},
        {0, 5, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 5, 0},
        {0, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0},
        {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0}
    };

  
    public tileMap() {
        this.cenarioAtual  = 1;
        this.cenarioValido = this.cenario1DoJogo;
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

    // Retorna true quando a borda direita do jogador toca o tile 4 (portal)
    public boolean jogadorNoPortal(Player jogador) {
        int[][] mapa   = this.cenarioValido;
        int bordaDirX  = (int)(jogador.AreaColisao.getX() + jogador.AreaColisao.getWidth());
        int bordaTopoY = (int) jogador.AreaColisao.getY();
        int bordaBaseY = (int)(jogador.AreaColisao.getY() + jogador.AreaColisao.getHeight());

        int colDir = (bordaDirX + jogador.passo) / 48;
        int linT   = bordaTopoY / 48;
        int linB   = bordaBaseY / 48;

        if (colDir >= 0 && colDir < mapa[0].length) {
            if (linT >= 0 && linT < mapa.length && mapa[linT][colDir] == 4) return true;
            if (linB >= 0 && linB < mapa.length && mapa[linB][colDir] == 4) return true;
        }
        return false;
    }

    public void avancarCenario(Player jogador) {
        this.cenarioAtual++;
        if      (this.cenarioAtual == 2) this.cenarioValido = this.cenario2DoJogo;
        else if (this.cenarioAtual == 3) this.cenarioValido = this.cenario3DoJogo;
        else if (this.cenarioAtual == 4) this.cenarioValido = this.cenario4DoJogo;
        else if (this.cenarioAtual == 5) this.cenarioValido = this.cenario5DoJogo;
        else if (this.cenarioAtual == 6) this.cenarioValido = this.cenario6DoJogo;
        jogador.setPosicao(48, 192);
    }
}