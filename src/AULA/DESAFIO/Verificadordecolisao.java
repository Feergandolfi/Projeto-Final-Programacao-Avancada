package AULA.DESAFIO;

public class Verificadordecolisao {

    private int     colEsqX;
    private int     colDirX;
    private int     rowTopoY;
    private int     rowBaseY;
    private boolean colidiu;

    public Verificadordecolisao() { }

    // AJUSTE: Removido o valor 4 (portal). Agora o portal não bloqueia mais o jogador.
    private boolean isSolido(int valor) {
        return valor == 0 || valor == 2 || valor == 5;
    }

    private boolean dentroDoLimite(int lin, int col, int[][] cenario) {
        return lin >= 0 && lin < cenario.length
            && col >= 0 && col < cenario[0].length;
    }

    public boolean OcorreuColisao(Player Jogador, tileMap CenaDoJogo, String Direcao) {
        colidiu = false;

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