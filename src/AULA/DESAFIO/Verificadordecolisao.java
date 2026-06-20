package AULA.DESAFIO;

public class Verificadordecolisao {

    private int     colEsqX;
    private int     colDirX;
    private int     rowTopoY;
    private int     rowBaseY;
    private boolean colidiu;

    public Verificadordecolisao() { }

    // Retorna true se o tile bloqueia passagem.
    // Apenas o tijolo cinza (valor 0 = wall1.png) bloqueia.
    private boolean isSolido(int valor) {
        return valor == 0;
    }

    // Protege contra ArrayIndexOutOfBounds (Desafio 06)
    private boolean dentroDoLimite(int lin, int col, int[][] cenario) {
        return lin >= 0 && lin < cenario.length
            && col >= 0 && col < cenario[0].length;
    }

    public boolean OcorreuColisao(Player Jogador, tileMap CenaDoJogo, String Direcao) {
        colidiu = false;

        // Lê o array diretamente — evita race condition com o objeto tiles
        // que é compartilhado com a thread de renderização
        int[][] mapa = CenaDoJogo.cenarioValido;

        int bordaEsqX  = (int) Jogador.AreaColisao.getX();
        int bordaDirX  = (int) Jogador.AreaColisao.getX() + (int) Jogador.AreaColisao.getWidth();
        int bordaTopoY = (int) Jogador.AreaColisao.getY();
        int bordaBaseY = (int) Jogador.AreaColisao.getY() + (int) Jogador.AreaColisao.getHeight();

        this.colEsqX  = bordaEsqX  / 48;
        this.colDirX  = bordaDirX  / 48;
        this.rowTopoY = bordaTopoY / 48;
        this.rowBaseY = bordaBaseY / 48;

        if (Direcao.equals("cima")) {
            int proxLin = (bordaTopoY - Jogador.passo) / 48;
            if (dentroDoLimite(proxLin, colEsqX, mapa)) { if (isSolido(mapa[proxLin][colEsqX])) colidiu = true; } else colidiu = true;
            if (dentroDoLimite(proxLin, colDirX, mapa)) { if (isSolido(mapa[proxLin][colDirX])) colidiu = true; } else colidiu = true;
        }
        else if (Direcao.equals("baixo")) {
            int proxLin = (bordaBaseY + Jogador.passo) / 48;
            if (dentroDoLimite(proxLin, colEsqX, mapa)) { if (isSolido(mapa[proxLin][colEsqX])) colidiu = true; } else colidiu = true;
            if (dentroDoLimite(proxLin, colDirX, mapa)) { if (isSolido(mapa[proxLin][colDirX])) colidiu = true; } else colidiu = true;
        }
        else if (Direcao.equals("direita")) {
            int proxCol = (bordaDirX + Jogador.passo) / 48;
            if (dentroDoLimite(rowTopoY, proxCol, mapa)) { if (isSolido(mapa[rowTopoY][proxCol])) colidiu = true; } else colidiu = true;
            if (dentroDoLimite(rowBaseY, proxCol, mapa)) { if (isSolido(mapa[rowBaseY][proxCol])) colidiu = true; } else colidiu = true;
        }
        else if (Direcao.equals("esquerda")) {
            int proxCol = (bordaEsqX - Jogador.passo) / 48;
            if (dentroDoLimite(rowTopoY, proxCol, mapa)) { if (isSolido(mapa[rowTopoY][proxCol])) colidiu = true; } else colidiu = true;
            if (dentroDoLimite(rowBaseY, proxCol, mapa)) { if (isSolido(mapa[rowBaseY][proxCol])) colidiu = true; } else colidiu = true;
        }

        return colidiu;
    }
}