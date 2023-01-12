import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;

public class Component extends JComponent {
	public Square[][] squares;
	public String file;
	public String fileoutline;
	public int s;
	public int num;
	public boolean c;
	public int x;
	public int y;
	public int current;
	public double r;
	public double xc;
	public double yc;
	public ArrayList<Colour> colors;
	public int isel;
	public int jsel;
	public ArrayList<Button> colorButtons;
	public Button addColor;
	public Button save;
	public Button viewOtherColors;
	public Button cc;
	public Button recents;
	public int ex;
	public int ey;
	public int sx;
	public int sy;
	public int vx;
	public int vy;
	public int ax;
	public int ay;
	public boolean saved;
	public int ccx;
	public int ccy;
	public final int ccw = 200;
	public final int cch = 100;
	public final int clw = 200;
	public final int clh = 50;
	public int rx;
	public int ry;
	public final int rw = ccw;
	public final int rh = 30;
	public int cx;
	public int cy;
	public final int bw = 200;
	public final int bh = 50;
	public int brushSize;
	
	public Component(String filename, int i, int d, int num, int x, int y) {
		this.x = x;
		this.y = y;
		brushSize = 2;
		saved = true;
		ccx = 950 + x;
		ccy = 100 + y;
		rx = ccx;
		ry = ccy + cch + 20;
		cx = ccx;
		cy = ry + rh;
		ax = cx;
		ay = ry + 20;
		ex = cx + bw + 20;
		ey = y + 200;
		sx = cx;
		sy = y + 850;
		vx = cx + bw + 20;
		vy = y + 250;
		addColor = new Button(ax, ay, bw, bh, Color.white, "add new color");
		save = new Button(sx, sy, bw, bh, Color.white, "save");
		cc = new Button(ccx, ccy, ccw, cch, Color.white, "current");
		recents = new Button(rx, ry, rw, rh, Color.white, "recents");
		colorButtons = new ArrayList<Button>();
		isel = -1;
		jsel = -1;
		r = d / 2.0;
		xc = x + r;
		yc = y + r;
		this.file = filename + ".txt";
		this.fileoutline = filename + "outline.txt";
		this.num = num;
		this.s = d / num;
		squares = new Square[num][num];
		colors = new ArrayList<Colour>();
		current = 0;
		c = false;
		if (i == 0) {
			populateDefault();
		}
		else {
			scanIn();
		}
		initColorButtons();
	}
	
	public void populateDefault() {
		colors.add(new Colour(0, 255, 255, 255));
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				double x1 = 0.0 + i * s;
				double y1 = 0.0 + j * s;
				double dis = distance(x1, y1);
				boolean b = dis > r;
				squares[i][j] = new Square(colors.get(0), x + i * s, y + j * s, s, b, new int[] {0,0,0,0});
			}
		}
	}
	
	public double distance(double x1, double y1) {
		double x2 = Math.pow((x1 - xc), 2);
		double y2 = Math.pow((y1 - yc), 2);
		double ans = Math.sqrt(x2 + y2);
		return ans;
	}
	
	public Square getSquareClicked(int xx, int yy) {
		int xr = xx - x;
		int yr = yy - y;
		int i = xr / s;
		int j = yr / s;
		if (i < num && j < num && i >= 0 && j >= 0) {
			isel = i;
			jsel = j;
			return squares[i][j];
		}
		else {
			return null;
		}
	}
	
	public void scanIn() {
		try {
			Scanner sc = new Scanner(new File(file));
			Scanner sc2 = new Scanner(new File(fileoutline));
			int i = 0;
			boolean cdone = false;
			while (sc.hasNext()) {
				String str = sc.next();
				if (!cdone) {
					if (str.equals("42069lol")) {
						cdone = true;
					}
					else {
						String[] things  = str.split("_");
						
							colors.add(new Colour(Integer.parseInt(things[0]), Integer.parseInt(things[1]), 
									Integer.parseInt(things[2]), Integer.parseInt(things[3])));
						
						
					}
				}
				else {
					String str2 = sc2.next();
					String[] things  = str.split("_");
					String[] things2 = str2.split("_");
					for (int a = 0; a < things.length; a++) {
						int k = Integer.parseInt(things[a]);
						int k2 = Integer.parseInt(things2[a]);
						double x1 = 0.0 + i * s;
						double y1 = 0.0 + a * s;
						double dis = distance(x1, y1);
						boolean b = dis > r;
						int[] lines = new int[] {0, 0, 0, 0};
						if (k2 > 7) {
							lines[0] = 1;
							k2 -= 8;
						}
						if (k2 > 3) {
							lines[1] = 1;
							k2 -= 4;
						}
						if (k2 > 1) {
							lines[2] = 1;
							k2 -= 2;
						}
						if (k2 > 0) {
							lines[3] = 1;
						}
						squares[i][a] = new Square(colors.get(k), x + i * s, y + a * s, s, b, lines);
						
					}
					i ++;
				}
			}
			sc.close();
			sc2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setCurrent(int i, int j) {
		int i1 = i;
		int j1 = j;
		squares[i1][j1].setColor(colors.get(current));
		saved = false;
	}
	
	public void addColor(int r, int g, int b) {
		
		int i = colors.size();
		colors.add(new Colour(i, r, g, b));
		current = i;
		saved = false;
		System.out.println("color added, size is now " + colors.size());
	}
	
	public void setColor(int i) {
		current = i;
	}
	
	public void writeToFile() {
		File f = new File(file);
		File f2 = new File(fileoutline);
		try {
			if (f.createNewFile()) {
				FileWriter fw = new FileWriter(f);
				fw.write(buildFileString());
				fw.close();
				FileWriter fw2 = new FileWriter(f2);
				fw2.write(buildOtherFileString());
				fw2.close();
				saved = true;
			}
			else {
				File tempfile = new File("fuck.txt");
				File otherTempFile = new File("fuck2.txt");
				tempfile.createNewFile();
				otherTempFile.createNewFile();
				FileWriter fw = new FileWriter(tempfile);
				fw.write(buildFileString());
				FileWriter fw2 = new FileWriter(otherTempFile);
				fw2.write(buildOtherFileString());
				fw.close();
				f.delete();
				fw2.close();
				f2.delete();
				tempfile.renameTo(f);
				otherTempFile.renameTo(f2);
				saved = true;
				
			}
		} catch (IOException e) {
			System.out.println("an error occurred");
			e.printStackTrace();
		}
	}
	
	public String buildFileString() {
		String ans = "";
		for (Colour col: colors) {
			ans += col.toString();
		}
		ans += "42069lol\n";
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (j == num - 1) {
					ans += squares[i][j].col.i + "\n";
				}
				else {
					ans += squares[i][j].col.i + "_";
				}
			}
		}
		return ans;
	}
	
	public String buildOtherFileString() {
		String ans = "";
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (j == num - 1) {
					ans += squares[i][j].getLinesToInt() + "\n";
				}
				else {
					ans += squares[i][j].getLinesToInt() + "_";
				}
			}
		}
		return ans;
	}
	
	public void initColorButtons() {
		colorButtons = new ArrayList<Button>();
		for (int i = 0; i < colors.size(); i++) {
			colorButtons.add(new Button(cx, cy + clh * i, clw, clh, colors.get(i).getColor(), ""));
		}
		//System.out.println(colors.size());
	}
	

	
	public void paintComponent(Graphics g) {
		//System.out.println(current);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(x, y, 1400, 1000);
		for (Square[] sq: squares) {
			for (Square squ: sq) {
				squ.setC(c);
				squ.draw(g);
			}
		}
		for (int i = 0; i < squares.length; i ++) {
			for (int j = 0; j < squares.length; j++) {
				squares[i][j].setC(c);
				squares[i][j].draw(g);
			}
		}
		
		if (-1 != isel && -1 != jsel) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(x + s * isel, y + s * jsel, s, s);
			g2.setStroke(new BasicStroke());
		}
		initColorButtons();
		cc.setColor(colors.get(current).getColor());
		cc.draw(g);
		addColor.y = ry + colors.size() * bh + bh + 20;
		addColor.draw(g);
		save.draw(g);
		recents.draw(g);
		for (Button b: colorButtons) {
			b.draw(g);
		}
		g.setColor(Color.black);
		for (int i = 0; i <= num; i++) {
			g.drawLine(x, y + i * s, x + num * s, y + i * s);
			g.drawLine(x + i * s, y, x + i * s, y + num * s);
			
		}
	}

}
