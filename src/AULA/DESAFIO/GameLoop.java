package AULA.DESAFIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameLoop extends Thread implements Runnable, ActionListener {

    private int   FPS = 60;
    private Timer ControleDoTempoDoJogo;
    private long  contadorDeFPS;

    Painel           cenaDoJogo;
    EscutadorTeclado ET;

    public GameLoop(Painel P, EscutadorTeclado eT) {
        System.out.println("GameLoop instanciado!");
        this.cenaDoJogo = P;
        this.ET         = eT;
    }

    @Override
    public void run() {
        this.contadorDeFPS = 0;
        this.ControleDoTempoDoJogo = new Timer(1000, this);
        this.ControleDoTempoDoJogo.start();

        double frameRate              = 1000000000.0 / this.FPS;
        double tempoDecorrido         = 0;
        long   tempoUltimaMedidaDoLoop = System.nanoTime();
        long   tempoAtualDoLoop;

        while (this.isAlive()) {
            tempoAtualDoLoop   = System.nanoTime();
            tempoDecorrido     = tempoDecorrido +
                    (tempoAtualDoLoop - tempoUltimaMedidaDoLoop) / frameRate;
            tempoUltimaMedidaDoLoop = tempoAtualDoLoop;

            if (tempoDecorrido >= 1) {

                // segurança: aguarda cenario estar pronto
                if (cenaDoJogo.cenario == null) {
                    tempoDecorrido = 0;
                    continue;
                }

                String direcao = "";
                if (ET.movePraCima)  direcao = "cima";
                if (ET.movePraBaixo) direcao = "baixo";
                if (ET.movePraDir)   direcao = "direita";
                if (ET.movePraEsq)   direcao = "esquerda";

                Verificadordecolisao colisao = new Verificadordecolisao();
                boolean bateu = colisao.OcorreuColisao(
                        cenaDoJogo.Jogador, cenaDoJogo.cenario, direcao);

                if (!bateu) {
                    cenaDoJogo.Jogador.atualizaPosicaoJogador(
                            ET.movePraEsq, ET.movePraCima,
                            ET.movePraDir, ET.movePraBaixo);
                }

                if (cenaDoJogo.cenario.jogadorNoPortal(cenaDoJogo.Jogador)
                        && cenaDoJogo.cenario.cenarioAtual < 6) {
                    cenaDoJogo.cenario.avancarCenario(cenaDoJogo.Jogador);
                }

                cenaDoJogo.repaint();
                this.contadorDeFPS++;
                tempoDecorrido = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("FPS GameLoop: " + this.contadorDeFPS);
        this.contadorDeFPS = 0;
    }
}