import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//testColorComponent();
		run();

	}
	
	public static void testColorComponent() {
		JFrame frame = new JFrame();
		frame.add(new ColorComponent());
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void run() {
		JFrame frame = new JFrame();
		int d = 900;
		int num = 60; 
		Component c = new Component("test3", 1, d, num, 0, 0);
		Listener l = new Listener(c, frame);
		c.addMouseListener(l);
		c.addMouseMotionListener(l);
		frame.addKeyListener(l);
		frame.add(c);
		frame.setSize(d + 500, d + 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
