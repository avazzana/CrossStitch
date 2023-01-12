import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Listener implements MouseListener,KeyListener, MouseMotionListener {
	
	public Component c;
	public JFrame mainFrame;
	
	public Listener(Component c, JFrame mainFrame) {
		this.c = c;
		this.mainFrame = mainFrame;
	}

	public void clickInit() {
		c.addColor.isClicked = false;
		c.save.isClicked = false;
		c.viewOtherColors.isClicked = false;
		c.recents.isClicked = false;
		c.cc.isClicked = false;
		for (Button b: c.colorButtons) {
			b.isClicked = false;
		}
		c.isel = -1;
		c.jsel = -1;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			c.jsel -= 1;
			c.setCurrent(c.isel, c.jsel);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			c.jsel += 1;
			c.setCurrent(c.isel, c.jsel);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			c.isel -= 1;
			c.setCurrent(c.isel, c.jsel);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			c.isel += 1;
			c.setCurrent(c.isel, c.jsel);
		}
		c.repaint();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		//clickInit();
		int x = arg0.getX() - c.x;
		int y = arg0.getY() - c.y;
		if (c.addColor.clicked(x, y)) {
			JFrame colorFrame = new JFrame();
			JPanel coloryThings = new JPanel(new GridLayout(3, 2));
			JSlider r = new JSlider();
			coloryThings.add(new JLabel("red"));
			coloryThings.add(r);
			JSlider g = new JSlider();
			coloryThings.add(new JLabel("green"));
			coloryThings.add(g);
			JSlider b = new JSlider();
			coloryThings.add(new JLabel("blue"));
			coloryThings.add(b);
			r.setMaximum(255);
			r.setMinimum(0);
			g.setMaximum(255);
			g.setMinimum(0);
			b.setMaximum(255);
			b.setMinimum(0);
			r.setValue(0);
			g.setValue(0);
			b.setValue(0);
			Hashtable<Integer, JLabel> rt = new Hashtable<Integer, JLabel>();
			rt.put( new Integer( 0 ), new JLabel("min") );
			rt.put( new Integer( 255 ), new JLabel("max") );
			r.setLabelTable( rt );
			r.setPaintLabels(true);
			
			Hashtable<Integer, JLabel> gt = new Hashtable<Integer, JLabel>();
			gt.put( new Integer( 0 ), new JLabel("min") );
			gt.put( new Integer( 255 ), new JLabel("max") );
			g.setLabelTable( gt );
			g.setPaintLabels(true);
			
			Hashtable<Integer, JLabel> bt = new Hashtable<Integer, JLabel>();
			bt.put( new Integer( 0 ), new JLabel("min") );
			bt.put( new Integer( 255 ), new JLabel("max") );
			b.setLabelTable( bt );
			b.setPaintLabels(true);
			
			ColorComponent cc = new ColorComponent();
			
			r.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					cc.r = r.getValue();
					cc.repaint();
				}
			});
			
			g.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					cc.g = g.getValue();
					cc.repaint();
				}
			});
			
			b.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					cc.b = b.getValue();
					cc.repaint();
				}
			});
			
			colorFrame.add(coloryThings, BorderLayout.CENTER);
			colorFrame.add(cc, BorderLayout.EAST);
			JButton cancelButton = new JButton("cancel");
			JButton continueButton = new JButton("continue");
			JPanel buttslol = new JPanel();
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					colorFrame.dispose();
					
				}
				
			});
			continueButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					c.colors.add(new Colour(c.colors.size(), cc.r, cc.g, cc.b));
					c.setColor(c.colors.size() - 1);
					colorFrame.dispose();
					c.repaint();
					
				}
				
			});
			buttslol.add(cancelButton);
			buttslol.add(continueButton);
			colorFrame.add(buttslol, BorderLayout.SOUTH);
			colorFrame.pack();
			colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			colorFrame.setVisible(true);
		}
		if (c.save.clicked(x, y)) {
			c.writeToFile();
			JOptionPane.showMessageDialog(null, "Saved");
		}
		for (int i = 0; i < c.colorButtons.size(); i++) {
			if (c.colorButtons.get(i).clicked(x, y)) {
				//System.out.println(i);
				c.setColor(i);
			}
		}
		Square sq = c.getSquareClicked(x, y);
		if (sq != null) {
			sq.setColor(c.colors.get(c.current));
		}
		c.repaint();

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		Square sq = c.getSquareClicked(x, y);
		if (sq != null) {
			sq.setColor(c.colors.get(c.current));
		}
		c.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
