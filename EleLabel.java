import javax.swing.JLabel;

public class EleLabel extends JLabel {
		
		public EleLabel(String title,int size,int x,int y,int w,int h) {
			 super(title);
			 setFont(Element.getFont(size));
			 setBounds(x,y,w,h);
		}
}
