package application.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;

public class jogoTiroController {

    @FXML
    private Canvas canva;

    private double playerX = 200;
    private final double playerY = 500;
    private final double raio = 40;
    private final double larguraTela = 360;
    private final double alturaTela = 600;

    private int pontuacao = 0;
    private int vidas = 10;
    private boolean jogoAtivo = true;

    private final ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private final ArrayList<Tiro> tiros = new ArrayList<>();

    private final Random random = new Random();
    private boolean esquerda, direita;
    private boolean turboAtivo = false;

    private Image imagemPlayer;
    private Image imagemObstaculo;
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        imagemPlayer = new Image(getClass().getResourceAsStream("miku.jpg"));
        imagemObstaculo = new Image(getClass().getResourceAsStream("download0.jpg"));

        // Música de fundo
        try {
            URL resource = getClass().getResource("musica.mp3");
            if (resource != null) {
                Media media = new Media(resource.toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Música em loop
                mediaPlayer.play();
            } else {
                System.out.println("Arquivo de música não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        canva.setFocusTraversable(true);
        canva.requestFocus();

        GraphicsContext gc = canva.getGraphicsContext2D();

        // Eventos de teclado
        canva.setOnKeyPressed(e -> {
            if (jogoAtivo) {
                if (e.getCode() == KeyCode.LEFT) esquerda = true;
                if (e.getCode() == KeyCode.RIGHT) direita = true;
                if (e.getCode() == KeyCode.CONTROL) turboAtivo = true;
                if (e.getCode() == KeyCode.SPACE) {
                    tiros.add(new Tiro(playerX - 2.5, playerY - raio));
                }
            } else {
                if (e.getCode() == KeyCode.ENTER) {
                    reiniciarJogo();
                }
            }
        });

        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
            if (e.getCode() == KeyCode.CONTROL) turboAtivo = false;
        });

        AnimationTimer timer = new AnimationTimer() {
            long lastSpawn = 0;

            @Override
            public void handle(long now) {
                if (jogoAtivo) {
                    atualizar();
                    desenhar(gc);

                    // Gera novo obstáculo a cada 1 segundo
                    if (now - lastSpawn > 1_000_000_000) {
                        double posX = random.nextDouble() * (larguraTela - 70);
                        obstaculos.add(new Obstaculo(posX, -90));
                        lastSpawn = now;
                    }
                } else {
                    gc.setFill(Color.RED);
                    gc.setFont(Font.font(36));
                    gc.fillText("FIM DE JOGO", larguraTela / 2 - 100, alturaTela / 2);

                    gc.setFont(Font.font(20));
                    gc.setFill(Color.WHITE);
                    gc.fillText("Pressione ENTER para reiniciar", larguraTela / 2 - 130, alturaTela / 2 + 40);
                }
            }
        };

        timer.start();
    }

    private void atualizar() {
        double velocidade = turboAtivo ? 20 : 5;

        if (esquerda && playerX - raio > 0) {
            playerX -= velocidade;
        }

        if (direita && playerX + raio < larguraTela) {
            playerX += velocidade;
        }

        // Atualiza obstáculos
        Iterator<Obstaculo> itObs = obstaculos.iterator();
        while (itObs.hasNext()) {
            Obstaculo obs = itObs.next();
            obs.y += 4;

            if (obs.y > alturaTela) {
                itObs.remove();
            }
        }

        // Verifica colisão entre jogador e obstáculos
        Iterator<Obstaculo> itObsColisaoPlayer = obstaculos.iterator();
        while (itObsColisaoPlayer.hasNext()) {
            Obstaculo obs = itObsColisaoPlayer.next();
            boolean colidiuComPlayer = playerY - raio <= obs.y + obs.altura &&
                    playerY + raio >= obs.y &&
                    playerX + raio >= obs.x &&
                    playerX - raio <= obs.x + obs.largura;

            if (colidiuComPlayer) {
                vidas--;
                itObsColisaoPlayer.remove();

                if (vidas <= 0) {
                    jogoAtivo = false;
                    if (mediaPlayer != null) mediaPlayer.stop();
                }
            }
        }

        // Atualiza tiros
        Iterator<Tiro> itTiro = tiros.iterator();
        while (itTiro.hasNext()) {
            Tiro tiro = itTiro.next();
            tiro.y -= 8;

            if (tiro.y < 0) {
                itTiro.remove();
                continue;
            }

            // Verifica colisão com obstáculos
            Iterator<Obstaculo> itObsColisao = obstaculos.iterator();
            while (itObsColisao.hasNext()) {
                Obstaculo obs = itObsColisao.next();
                boolean colidiu = tiro.y <= obs.y + obs.altura &&
                        tiro.y + tiro.altura >= obs.y &&
                        tiro.x + tiro.largura >= obs.x &&
                        tiro.x <= obs.x + obs.largura;

                if (colidiu) {
                    pontuacao++;
                    itObsColisao.remove();
                    itTiro.remove();
                    break;
                }
            }
        }
    }

    private void desenhar(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        // Estrelas aleatórias
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            double estrelaX = random.nextDouble() * larguraTela;
            double estrelaY = random.nextDouble() * alturaTela;
            gc.fillOval(estrelaX, estrelaY, 2, 2);
        }

        // Nave do jogador
        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 2, raio * 2);

        // Obstáculos
        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        // Tiros
        gc.setFill(Color.YELLOW);
        for (Tiro tiro : tiros) {
            gc.fillRect(tiro.x, tiro.y, tiro.largura, tiro.altura);
        }

        // Pontuação e Vidas
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(18));
        gc.fillText("Pontuação: " + pontuacao, 10, 20);
        gc.fillText("Vidas: " + vidas, 10, 40);
    }

    private void reiniciarJogo() {
        playerX = 200;
        pontuacao = 0;
        vidas = 10;
        jogoAtivo = true;
        obstaculos.clear();
        tiros.clear();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.play();
        }
    }

    // Classe de obstáculos
    class Obstaculo {
        double x, y;
        final double largura = 70;
        final double altura = 90;

        Obstaculo(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Classe de tiros
    class Tiro {
        double x, y;
        final double largura = 5;
        final double altura = 10;

        Tiro(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}