package application.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class jogoController {

    @FXML
    private Canvas canva;

	private double playerX=200;
	private final double playerY=500;
	private final double raio=16;
	private final double larguraTela=360;
	private final double alturaTela=600;
	private int pontuacao=0;
	private ArrayList<Obstaculo> obstaculos= new ArrayList();
	private Random random = new Random();
	private boolean esquerda,direita;
	private Image imagemPlayer;
	private Image imagemObstaculo;
	@FXML
	public void initialize() {
		InputStream playerStream = getClass().getResourceAsStream("f1_azul.png");
		InputStream obstaculoStream = getClass().getResourceAsStream("f1_vermelho.png");
		imagemPlayer = new Image(playerStream);
		imagemObstaculo = new Image(obstaculoStream);
		
		/*iniciar com o foco no jogo*/
		canva.setFocusTraversable(true);
		canva.requestFocus();
		
		/*INICIA O DESENHO NO CANVAS*/
		GraphicsContext gc = canva.getGraphicsContext2D();
		
		/*RECONHECE AS TECLAS DIREITA E ESQUERDA AO SEREM PRESSIONADAS*/
		canva.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.LEFT){ esquerda=true;}
			if(e.getCode()==KeyCode.RIGHT){ direita=true;}
		});
		
		/*RECONHECE AS TECLAS DIREITA E ESQUERDA AO SEREM SOLTAS*/
		canva.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.LEFT){ esquerda=false;}
			if(e.getCode()==KeyCode.RIGHT){ direita=false;}
		});
		
		AnimationTimer timer = new AnimationTimer() {
			long ultimoSpaw=0;
			long intervaloSpaw=1_000_000_000;
			
			@Override
			public void handle(long now) {
				atualizar();
				desenhar(gc);
				long velocidade=1_000_000_000;
				if (now - ultimoSpaw > intervaloSpaw) {					

				obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 40), -40));				
				ultimoSpaw=now;
				}
				

			}
			
		};
		timer.start();
	}
	
	private void desenhar(GraphicsContext gc) {
		//PLANO DE FUNDO
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 0, larguraTela, alturaTela);
	//
gc.drawImage(imagemPlayer, playerX-raio, playerY-raio, raio*4, raio*4);

	//
		for(Obstaculo obs: obstaculos) {
			gc.drawImage(imagemObstaculo, obs.x-raio, obs.y-raio,raio*4,raio*4);		
		}
		
		gc.setFill(Color.BLACK);
		gc.setFont(javafx.scene.text.Font.font(18));
		gc.fillText("Pontuação "+pontuacao, 10, 20);
	}
	
	private void atualizar() {
		if(esquerda && playerX-raio > 0 )  playerX-=5;
		if(direita && playerX+raio < larguraTela)  playerX+=5;
		
		double velocidade=4;
		
		Iterator<Obstaculo> it = obstaculos.iterator();
		
		while(it.hasNext()) {
			Obstaculo obs = it.next();
			obs.y+=velocidade;
			
			double centroPlayerX=playerX+raio;
			double centroPlayerY=playerY+raio;
			double obsTopo=obs.y;
			double obsBase=obs.y+obs.altura;
			double obsEsquerda=obs.x;
			double obsDireita=obs.x+obs.largura;
			
			boolean colidiu= centroPlayerX >= obsEsquerda &&
					centroPlayerX <= obsDireita &&
					centroPlayerY >= obsTopo &&
					centroPlayerY<=obsBase;
					
			if(colidiu) {
				pontuacao=0;
				it.remove();
			}else if (obs.y>alturaTela) {
				pontuacao++;
				it.remove();
			}
								
		}

	}
	
	//classe de obstaculos
	class Obstaculo {
		double x,y;
		final double largura=70;
		final double altura=60;
		Obstaculo(double x, double y){
			this.x=x;
			this.y=y;
		}
	}
}