import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoMemoria extends JFrame implements ActionListener, MouseListener {
	private JPanel panel;
	private final int CARD_COUNT = 5;
	//teste
	private int score, pairs, numbers[];
	private int tempIndex = -1;
	private JLabel labelScore;
	private Color transparent;
	private JButton tempButton, button1[], button2[];
	private Random r;
	public JogoMemoria() {
		super("Jogo da Mem�ria");
		
		this.setSize(900, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		transparent = new Color(0f,0f,0f,0f);
		
		labelScore = new JLabel("Pontos: 0");
		labelScore.setBounds(10, 5, 80, 30);
		panel.add(labelScore);
		
		r = new Random();
		numbers = new int[CARD_COUNT];
		for (int i = 0; i < CARD_COUNT; i++) {
			numbers[i] = r.nextInt(99);
			for (int j = 0; j < i; j++) {
				if (numbers[i] == numbers[j]) {
					i--;
					break;
				}
			}
		}
		
		pairs = 5;
		
		button1 = new JButton[5];
		button2 = new JButton[5];
		
		for (int i = 0; i < 5; i++) {
		button1[i] = new JButton(""+numbers[i]);
			button1[i].setBounds(r.nextInt(750)+10, r.nextInt(420)+30, 120, 150);
			button1[i].setBackground(Color.GREEN);
			button1[i].setForeground(transparent);
			button1[i].addActionListener(this);
			button1[i].addMouseListener(this);
			panel.add(button1[i]);
			
			button2[i] = new JButton(""+numbers[i]);
			button2[i].setBounds(r.nextInt(750)+10, r.nextInt(420)+30, 120, 150);
			button2[i].setBackground(Color.RED);
			button2[i].setForeground(transparent);
			button2[i].addActionListener(this);
			button2[i].addMouseListener(this);
			panel.add(button2[i]);
		}
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae) {
		JButton res = (JButton) ae.getSource();
		res.setForeground(Color.WHITE);
		int index = -1;
		for (int i = 0; i < 5; i++) {
			if (ae.getActionCommand().equals(""+numbers[i]))
				index = i;
		}
		
		if (tempIndex == -1) {
			tempIndex = index;
			tempButton = res;
		}
		else if (index == tempIndex && !res.equals(tempButton)) {
			tempIndex = -1;
			score += 10;
			pairs--;
			labelScore.setText("Pontos: "+score);
			button1[index].setVisible(false);
			button2[index].setVisible(false);
			if (pairs<=0) {
				new Resultado(score).setVisible(true);
				this.setVisible(false);
			}
			System.out.println("Carta n�: "+numbers[index]+"\nErros: "+index+"\n-------------");
		}
		else {
			tempIndex = -1;
			score -= 5;
			tempButton.setForeground(transparent);
			labelScore.setText("Pontua��o: "+score);
			res.setForeground(transparent);
		}
	}
	
	public void mouseClicked(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {
		JButton b = (JButton) me.getSource();
		b.setForeground(Color.BLACK);
	}
	public void mouseReleased(MouseEvent me) {
		JButton b = (JButton) me.getSource();
		if (tempIndex == -1) {
			b.setForeground(transparent);
		}
		else {}
	}
}