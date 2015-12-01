package com.anandhu.parser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordCount extends JScrollPane{
	String text;
	JPanel panel;
	HashMap<String, Integer> counter;
	ArrayList<Entry<String, Integer>> list;

	WordCount(Document document) {
		counter = new HashMap<String, Integer>();
		text = document.body().text();
		wordCounts();
		list = sortValue(counter);
		panel = new JPanel(new GridLayout(counter.size(), 2));
		
		for(Entry<String,Integer> entry : list){
			JLabel label = new JLabel(entry.getKey());
			JLabel value = new JLabel(entry.getValue().toString());
			panel.add(label);
			panel.add(value);
			
		}
		setViewportView(panel);
		setPreferredSize(new Dimension(350 ,350));
		
		
		
		
	}

	

	private ArrayList<Entry<String, Integer>> sortValue(HashMap<String, Integer> map) {
		ArrayList<Entry<String ,Integer>> list1 = new ArrayList<Entry<String ,Integer>>(map.entrySet());
		Comparator<Entry<String ,Integer>> sortval = new Comparator<Entry<String,Integer>>() {
			
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
				
				
			}
		};
		Collections.sort(list1 ,sortval);

		
		
		return list1;
	}



	private void wordCounts() {
 StringTokenizer st = new StringTokenizer(text);
 while(st.hasMoreTokens()){
	 String string = st.nextToken();
	 int count = counter.containsKey(string) ? counter.get(string) : 0;
	 counter.put(string, count+1);
	 
 }
		
	}
}
