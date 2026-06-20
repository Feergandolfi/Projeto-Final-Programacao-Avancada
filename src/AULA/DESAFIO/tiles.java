package AULA.DESAFIO;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class tiles {

    private final int largura = 48, altura = 48;
    private int posX, posY;
    private Image imgAtual;
    private Image imgGrass, imgSand, imgWall, imgWater;
    private Image imgWhite, imgGray;

    // ── DESAFIO 05 ─────────────────────────────────────
    // Atributo que indica se este tile bloqueia passagem
    private boolean colisao;
    // ───────────────────────────────────────────────────

    public tiles() {
        this.carregaImagensTile();
    }

    private void carregaImagensTile() {
        ImageIcon icon;
        icon = new ImageIcon("res/tiles/grass1.png");
        this.imgGrass = icon.getImage();
        icon = new ImageIcon("res/tiles/sand1.png");
        this.imgSand = icon.getImage();
        icon = new ImageIcon("res/tiles/water1.png");
        this.imgWater = icon.getImage();
        icon = new ImageIcon("res/tiles/wall1.png");
        this.imgWall = icon.getImage();
        icon = new ImageIcon("res/tiles/white.png");
        this.imgWhite = icon.getImage();
        icon = new ImageIcon("res/tiles/gray.png");
        this.imgGray = icon.getImage();
    }

    // ── DESAFIO 05 ─────────────────────────────────────
    // Define imagem e permissão de passagem para cada valor da matriz
    public void caregaPecaDaMatriz(int valorDaPeca) {
        if (valorDaPeca == 0) {
            this.imgAtual = this.imgWall;
            this.colisao  = true;  // não permite passagem
        }
        if (valorDaPeca == 1) {
            this.imgAtual = this.imgSand;
            this.colisao  = false; // permite passagem
        }
        if (valorDaPeca == 2) {
            this.imgAtual = this.imgWater;
            this.colisao  = true;  // não permite passagem
        }
        if (valorDaPeca == 3) {
            this.imgAtual = this.imgGrass;
            this.colisao  = false; // permite passagem
        }
        if (valorDaPeca == 4) {
            // DESAFIO 06 — tile de portal/passagem bloqueado para
            // impedir que o jogador saia do cenário atual
            this.imgAtual = this.imgWhite;
            this.colisao  = true;  // não permite saída do cenário
        }
        if (valorDaPeca == 5) {
            this.imgAtual = this.imgGray;
            this.colisao  = true;  // não permite passagem (pedra)
        }

        // ── Linhas auxiliares de depuração (DESAFIO 03 – deixar em comentário
        //    depois de verificar o comportamento)
        // if (this.colisao == true)  this.imgAtual = this.imgGray;
        // else                       this.imgAtual = this.imgWhite;
    }

    // ── DESAFIO 05 – getter e setter ───────────────────
    public boolean isColisao() {
        return colisao;
    }

    public void setColisao(boolean colisao) {
        this.colisao = colisao;
    }
    // ───────────────────────────────────────────────────

    public void desenhaTile(Graphics2D desenho, int linha, int coluna) {
        this.posX = coluna * this.largura;
        this.posY = linha  * this.altura;
        desenho.drawImage(this.imgAtual, this.posX, this.posY,
                          this.largura, this.altura, null);
    }
}