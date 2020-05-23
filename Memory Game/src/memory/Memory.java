package memory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import memory.Window;

public class Memory {
	// INSTANCE VARIABLES
	private JFrame frame;
	private JPanel panelTitle, panelGrid, panelControl;
	private JButton buttonNew, buttonSolve, buttonAbout;
	private Window _w;
	private Window _w2;

	private buttonGame buttonLastClicked;
	private final Images imagens;

	private JLabel labelTitle;

	Integer intCombined;
	ArrayList listShuffle;

	private List<buttonGame> listButtons;

	private class buttonGame extends JButton {
		Integer iCod;

		/*
		 * This is all to create the buttons in the window
		 */
		public buttonGame(Integer iCod) {
			this.iCod = iCod;
		}
	}

	// This method simply increases the size of the font
	private void enlargeFont(java.awt.Component c, float factor) {
		c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
	}

	// CONSTRUCTOR
	public Memory() {
		_w = new Window();
		_w.msg("Your name is Ash Ketchum from Pallet town! You're on you next journey which takes place in the \r\n"
				+ "Galar Region of the Pokemon world. You've come a long way with your team of Pokemon and Pikachu. \r\n"
				+ "You've just received your eighth gym badge and were about to fight Champion Leon, but were made \r\n"
				+ "to challenge and defeat the Chairman of the League, Rose, who has summoned the legendary Pokemon \r\n"
				+ "Eternatus. You've won the battle, but Eternatus has pulled a trick on all of Galar! The legendary \r\n"
				+ "beast has duplicated every Pokemon in the region, and now there are issues of overpopulation! Every \r\n"
				+ "Pokemon has had their stats halved and thus your team has been rendered all but useless. In order to \r\n"
				+ "fix this you must check all of your Pokeballs and match the Pokemon that are the same, which will put \r\n"
				+ "them back into just one creature. Once this is done you'll be able to challenge Eternatus and save \r\n"
				+ "the fabled Galar region!");
		imagens = new Images();
		intCombined = 0;

		listShuffle = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			listShuffle.add(i);
			listShuffle.add(i);
		}
		Collections.shuffle(listShuffle);
	}

	/*
	 * This method clarifies the condition for success, a winning game
	 */
	private void Solve(Boolean bMostrarCliques) {
		if (intCombined == -1)
			return;
		labelTitle.setText("                                       Pokemon Caught: "
				+ (bMostrarCliques ? intCombined.toString() : "Auto Resolution"));

		intCombined = 10;
		buttonLastClicked = null;

		for (int i = 0; i < listButtons.size(); i++) {
			buttonGame button = listButtons.get(i);
			button.setIcon(imagens.IconFactory((Integer) listShuffle.get(i)));
			button.iCod = 0;
			listButtons.set(i, button);
		}
		panelGrid.repaint();
	}

	/*
	 * Creates a randomized instance of a new game, inputs all the images randomly
	 * into their positions with the for loop
	 */
	private void NewGame() {
		Collections.shuffle(listShuffle);
		intCombined = 0;
		labelTitle.setText("                                       Pokemon Caught: 0");
		buttonLastClicked = null;

		for (int i = 0; i < listButtons.size(); i++) {
			buttonGame button = listButtons.get(i);
			button.iCod = (Integer) listShuffle.get(i);
			button.setIcon(imagens.IconFactory(-1));
			listButtons.set(i, button);
		}
		panelGrid.repaint();

	}

	/*
	 * This method creates the actual Window, JPanel, and inputs the JFrames and
	 * JLabels that are going to be placed into the game. Essentially creates the
	 * basics for GUI
	 */
	public void ShowWindow() {
		frame = new JFrame("Memory");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Title
		labelTitle = new JLabel("                                       Pokemon Caught: 0");
		enlargeFont(labelTitle, 2);

		panelTitle = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
		panelTitle.add(labelTitle);
		frame.add(panelTitle, BorderLayout.NORTH);

		// Controls
		panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		panelControl.setBorder(new BevelBorder(BevelBorder.RAISED));
		buttonNew = new JButton("Start Again");
		enlargeFont(buttonNew, 2);
		panelControl.add(buttonNew);
		buttonAbout = new JButton("Help");
		enlargeFont(buttonAbout, 2);
		panelControl.add(buttonAbout);
		frame.add(panelControl, BorderLayout.SOUTH);

		buttonNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGame();
			}
		});

		buttonAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Match your duplicated Pokemon so they can fuse back together and help you fight Eternatus! \r\n"
								+ "Clicking on a Pokeball will show you the creature in it, and clicking on another \r\n"
								+ "will hide the previous Pokemon and show you the new one you selected. If you click on \r\n"
								+ "the Pokeball that contains the same Pokemon as the one that is visible, they will \r\n"
								+ "fuse and join your team once more! There's ten Pokemon to catch once more.");
			}
		});

		// grid principal
		panelGrid = new JPanel(new GridBagLayout());
		panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));
		// 6 x 4 = 24
		// 24 have two possibilities of 12
		listButtons = new ArrayList<>();
		buttonLastClicked = null;
		int x = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				Integer intNumSorteado = (Integer) listShuffle.get(x);
				buttonGame buttonItem = new buttonGame(intNumSorteado);

				buttonItem.setIcon(imagens.IconFactory(-1));
				x++;

				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = .5;
				c.weighty = .5;
				c.gridx = i;
				c.gridy = j;
				panelGrid.add(buttonItem, c);

				// list botoes usado no novo jogo
				listButtons.add(buttonItem);

				buttonItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (buttonItem.iCod == 0) {
							return;
						}
						// rule
						// if there was a repeated click on the same button it is not worth...
						if (buttonItem.equals(buttonLastClicked))
							return;

						labelTitle.setText("                                       Pokemon Caught: " + intCombined);

						buttonItem.setIcon(imagens.IconFactory(buttonItem.iCod));

						if (buttonLastClicked == null) {
							buttonLastClicked = buttonItem;
							return;
						}

						if (Objects.equals(buttonItem.iCod, buttonLastClicked.iCod)) {

							buttonItem.setIcon(imagens.IconFactory(0));
							buttonItem.iCod = 0;

							buttonLastClicked.setIcon(imagens.IconFactory(0));
							buttonLastClicked.iCod = 0;

							buttonLastClicked = null;
							intCombined++;
							if (intCombined >= 10) {
								Solve(true);
								JOptionPane.showMessageDialog(frame,
										"You've brought all your Pokemon back together and your team is stronger than ever! \r\n"
												+ "Go now to defeat the fabled Eternatus!");
							}

						} else {
							buttonLastClicked.setIcon(imagens.IconFactory(-1));
							buttonLastClicked = buttonItem;
						}
					}
				});
			}
		}
		frame.add(panelGrid, BorderLayout.CENTER);

		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Memory mem = new Memory();
				mem.ShowWindow();

			}
		});

	}

}
