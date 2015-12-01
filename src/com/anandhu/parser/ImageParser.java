package com.anandhu.parser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ImageParser extends JScrollPane{
	Elements images;
	Document document;
	JPanel panel;
	public ImageParser(Document document){
	this.document = document;
	images = document.select("img[src~=(?i)\\.(png|jpe?g|jif)]");
	panel = new JPanel(new GridLayout(images.size(), 1));
	parseImages();
	setViewportView(panel);
	setPreferredSize(new Dimension(350 , 350));
	}
	private void parseImages() {
		for(Element image :images){
			String str = image.attr("src");
			if (str.length() > 0) {
				if (str.length() < 4) 
						str = document.baseUri() + str.substring(1);

				 else if (!str.substring(0, 4).equals("http")) 
					str = document.baseUri() + str.substring(1);
				JLabel label = new JLabel(str);
				panel.add(label);
				
			}
			
		}
		
		
	}
}