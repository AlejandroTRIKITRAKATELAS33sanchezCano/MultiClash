package pokemonGUI;

import java.util.Random;

import funcionalidad.SClip;

public class musica {
	private SClip undertale1= new SClip("..\\Pokemon\\src\\resources\\audio\\undertale1.wav");
	private SClip dragonball1= new SClip("..\\Pokemon\\src\\resources\\audio\\dragonball1.wav");
	private SClip pokemon1= new SClip("..\\Pokemon\\src\\resources\\audio\\pokemon1.wav");
	private SClip pokemon2= new SClip("..\\Pokemon\\src\\resources\\audio\\pokemon2.wav");
	private SClip spiderman1= new SClip("..\\Pokemon\\src\\resources\\audio\\spiderman1.wav");
	private SClip thesimsons1= new SClip("..\\Pokemon\\src\\resources\\audio\\thesimsons1.wav");
	private SClip clashroyale1= new SClip("..\\Pokemon\\src\\resources\\audio\\clashroyale1.wav");
	private SClip undertale2= new SClip("..\\Pokemon\\src\\resources\\audio\\undertale2.wav");
	private SClip undertale3= new SClip("..\\Pokemon\\src\\resources\\audio\\undertale3.wav");
	private SClip undertale4= new SClip("..\\Pokemon\\src\\resources\\audio\\undertale4.wav");
	private int cancion;
	
	public void musicadecombate() {
		Random random=new Random();
		switch (random.nextInt(7))
		{
		case 0: undertale1.loop();
		cancion=0;
		break;
		case 1: dragonball1.loop();
		cancion=1;
		break;
		case 2: pokemon1.loop();
		cancion=2;
		break;
		case 3: pokemon2.loop();
		cancion=3;
		break;
		case 4: spiderman1.loop();
		cancion=4;
		break;
		case 5: thesimsons1.loop();
		cancion=5;
		break;
		case 6: clashroyale1.loop();
		cancion=6;
		break;
		}
	}
	
	public void finmusicadecombate() {
		switch (cancion)
		{
		case 0: undertale1.stop();
		break;
		case 1: dragonball1.stop();
		break;
		case 2: pokemon1.stop();
		break;
		case 3: pokemon2.stop();
		break;
		case 4: spiderman1.stop();
		break;
		case 5: thesimsons1.stop();
		break;
		case 6: clashroyale1.stop();
		break;
		}
	}
		
	public void musicadetension() {
		undertale2.loop();
	}
	
	public void finmusicadetension() {
		undertale2.stop();
	}
	
	public void musicadecasa() {
		undertale3.loop();
	}
	
	public void finmusicadecasa() {
		undertale3.stop();
	}
	
	public void musicadegestion() {
		undertale4.loop();
	}
	
	public void finmusicadegestion() {
		undertale4.stop();
	}
}
