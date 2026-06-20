package AULA.DESAFIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

// ── DESAFIO 02 ─────────────────────────────────────────────────────────────
// Removida herança de Rectangle; agora usamos um Rectangle interno (AreaColisao)
// ───────────────────────────────────────────────────────────────────────────
public class Player {

    Image[] imgPlayerDown  = new Image[3];
    Image[] imgPlayerRight = new Image[3];
    Image[] imgPlayerLeft  = new Image[3];
    Image[] imgPlayerUp    = new Image[3];

    Image imagemPlayer;

    private int frameJogador = 0;

    // ── DESAFIO 02 – novos atributos ────────────────────
    Rectangle AreaColisao;       // retângulo sólido de colisão
    private int posX, posY;      // posição do sprite na tela
    private int Larg, Altu;      // dimensões do sprite
    public  int passo = 3;       // velocidade de deslocamento (px/frame)
    // ────────────────────────────────────────────────────

    public Player() {
        // ── atributos do Jogador ────────────────────────
        this.posX = 200;
        this.posY = 100;
        this.Larg = 48;
        this.Altu = 48;

        // ── atributos da Área de Colisão ────────────────
        // A área é menor que o sprite e fica na metade inferior
        // para dar sensação de profundidade (os pés colidem)
        AreaColisao = new Rectangle();
        this.AreaColisao.x      = this.posX + 3;
        this.AreaColisao.y      = this.posY + this.Altu / 2;
        this.AreaColisao.width  = this.Larg - 20;
        this.AreaColisao.height = this.Altu / 2;

        // ── carrega sprites ─────────────────────────────
        for (int i = 0; i < 3; i++) {
            this.imgPlayerDown[i]  = new ImageIcon("res/PLAYER/down"  + (i + 1) + ".png").getImage();
            this.imgPlayerRight[i] = new ImageIcon("res/PLAYER/right" + (i + 1) + ".png").getImage();
            this.imgPlayerLeft[i]  = new ImageIcon("res/PLAYER/left"  + (i + 1) + ".png").getImage();
            this.imgPlayerUp[i]    = new ImageIcon("res/PLAYER/up"    + (i + 1) + ".png").getImage();
        }

        this.imagemPlayer = this.imgPlayerDown[this.frameJogador];
    }

    // ── DESAFIO 02 – método atualizado ──────────────────
    public void desenhaJogador(Graphics2D d2) {
        // Descomenta as linhas abaixo para depurar a área sólida (quadrado preto)
        // d2.setColor(Color.black);
        // d2.fillRect(this.AreaColisao.x, this.AreaColisao.y,
        //             this.AreaColisao.width, this.AreaColisao.height);

        d2.drawImage(imagemPlayer, posX, posY, Larg, Altu, null);
    }

    // Mantido para compatibilidade com Painel.java (chama DesenharPLayer)
    public void DesenharPLayer(Graphics2D d2) {
        desenhaJogador(d2);
    }

    // ── DESAFIO 02 – método atualizado ──────────────────
    public void atualizaPosicaoJogador(boolean ME, boolean MC, boolean MD, boolean MB) {
        if (ME) this.posX -= passo;
        if (MD) this.posX += passo;
        if (MC) this.posY -= passo;
        if (MB) this.posY += passo;

        // Atualiza a posição da área sólida junto com o jogador
        this.AreaColisao.x = this.posX + 3;
        this.AreaColisao.y = this.posY + this.Altu / 2;

        // ── DESAFIO 03 – linhas de depuração (comentadas na versão final)
        // System.out.println("Coluna :" + (int) this.AreaColisao.x / 48);
        // System.out.println("Linha  :" + (int) this.AreaColisao.y / 48);
    }

    // Sobrecarga mantida para que SpriteLoop e outros callers antigos funcionem
    public void atualizaPosicao(boolean ME, boolean MC, boolean MD, boolean MB) {
        atualizaPosicaoJogador(ME, MC, MD, MB);
    }

    public void setPosicao(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.AreaColisao.x = this.posX + 3;
        this.AreaColisao.y = this.posY + this.Altu / 2;
    }

    // ── DESAFIO 08 (capítulo anterior) – animação de sprite ─────────────────
    public void atualizaSprite(boolean moveEsq, boolean moveCima,
                                boolean moveDir, boolean moveBaixo) {
        this.frameJogador++;

        if (moveEsq) {
            if (frameJogador >= this.imgPlayerLeft.length) frameJogador = 0;
            this.imagemPlayer = this.imgPlayerLeft[frameJogador];
        } else if (moveDir) {
            if (frameJogador >= this.imgPlayerRight.length) frameJogador = 0;
            this.imagemPlayer = this.imgPlayerRight[frameJogador];
        } else if (moveCima) {
            if (frameJogador >= this.imgPlayerUp.length) frameJogador = 0;
            this.imagemPlayer = this.imgPlayerUp[frameJogador];
        } else if (moveBaixo) {
            if (frameJogador >= this.imgPlayerDown.length) frameJogador = 0;
            this.imagemPlayer = this.imgPlayerDown[frameJogador];
        }
    }
}