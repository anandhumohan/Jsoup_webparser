package com.anandhu.parser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("serial")
public class LinkParser extends JTabbedPane {
	
	  Document document; 
	  JScrollPane pane;
	  
	  public LinkParser() { 
		  try {
			document = Jsoup.connect("http://google.com").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  getLinks();
		  addTab("links",pane);
		  addTab("images",new ImageParser(document));
		  addTab("word count", new WordCount(document));
	  
	  }
	 
	  
	  public void getLinks(){
	  
	  Elements links = document.getElementsByTag("a");
		JPanel linkpanel = new JPanel();
		linkpanel.setLayout(new GridLayout(links.size(), 1));
		for (Element link : links) {
			String str = link.attr("href");
			if (str.length() > 0) {
				if (str.length() < 4) 
						str = document.baseUri() + str.substring(1);

				 else if (!str.substring(0, 4).equals("http")) 
					str = document.baseUri() + str.substring(1);
				JLabel label = new JLabel(str);
				linkpanel.add(label);
				
			}
			
		}
		pane = new JScrollPane(linkpanel);
		pane.setPreferredSize(new Dimension(350, 350));
	  }
	  
	  
	
	public static void main(String[] args) throws IOException {
		
				JFrame frame = new JFrame("website parser");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LinkParser w = new LinkParser();
				frame.add(w);
				frame.setVisible(true);
				frame.setSize(400, 400);
				
				

		

	}
}