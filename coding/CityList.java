package com.hunter.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CityList {
	public static void main(String[] args) {
		String url = "http://www.58.com/changecity.aspx?PGTID=0d100000-02c5-5adc-60af-3ab01816e1c2&ClickID=1";
		getPureUrl(url);
		url = "http://g.58.com/city";
		getUrl(url);
		Map<String, String> map = getMap(
				"http://www.58.com/changecity.aspx?PGTID=0d100000-02c5-5adc-60af-3ab01816e1c2&ClickID=1");
		for (Entry<String, String> entry : map.entrySet()) {
			map.put(entry.getKey(), getString(entry.getValue()));
			System.out.println("key=" + entry.getKey() + " and value=" + entry.getValue());
		}
		map2Json(map,"D://test.json");
	}
	/*
	 * 将map数据写入到json文件中
	 */
	public static void map2Json(Map<String, String> map,String path){
		File file = new File(path);
		if(file.exists()){
			if(file.delete()){
				System.out.println("文件删除成功!");
			}else{
				System.out.println("文件删除失败!");
			}
		}
		try {
			file.createNewFile();
			System.out.println("文件已创建!");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write("{");
			for (Entry<String, String> entry : map.entrySet()) {
				bw.write("\""+entry.getKey()+"\""+":"+"\""+entry.getValue()+"\""+",");
				bw.newLine();
				bw.flush();
			}
			bw.write("}");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取查询的字符串 将匹配的字符串取出
	 */
	private static String getString(String str) {
		String text = null;
		Pattern pattern = Pattern.compile("//([a-zA-Z_0-9]*)");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			text = matcher.group(1);
		}
		return text;
	}

	public static void getUrl(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");

			print("\nMedia: (%d)", media.size());
			for (Element src : media) {
				if (src.tagName().equals("img"))
					print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
							src.attr("height"), trim(src.attr("alt"), 20));
				else
					print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}

			print("\nImports: (%d)", imports.size());
			for (Element link : imports) {
				print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
			}

			print("\nLinks: (%d)", links.size());
			for (Element link : links) {
				print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getPureUrl(String url) {
		Document doc = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");

			print("\nMedia: (%d)", media.size());
			for (Element src : media) {
				if (src.tagName().equals("img"))
					print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
							src.attr("height"), trim(src.attr("alt"), 20));
				else
					print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}

			print("\nImports: (%d)", imports.size());
			for (Element link : imports) {
				print("%s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
			}

			print("\nLinks: (%d)", links.size());
			for (Element link : links) {
				print("<%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
				map.put(trim(link.text(), 35), link.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(
				"map size : " + map.size() + "---------------------------------------------------------------------");
		for (Entry<String, String> entry : map.entrySet()) {
			// Map.entry<Integer,String> 映射项（键-值对） 有几个方法：用上面的名字entry
			// entry.getKey() ;entry.getValue(); entry.setValue();
			// map.entrySet() 返回此映射中包含的映射关系的 Set视图。
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		System.out
				.println("end---------------------------------------------------------------------------------------");
	}

	public static Map<String, String> getMap(String url) {
		Document doc = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");

			print("\nMedia: (%d)", media.size());
			for (Element src : media) {
				if (src.tagName().equals("img"))
					print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
							src.attr("height"), trim(src.attr("alt"), 20));
				else
					print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}

			print("\nImports: (%d)", imports.size());
			for (Element link : imports) {
				print("%s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
			}

			print("\nLinks: (%d)", links.size());
			for (Element link : links) {
				print("<%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
				map.put(trim(link.text(), 35), link.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(
				"map size : " + map.size() + "---------------------------------------------------------------------");
		for (Entry<String, String> entry : map.entrySet()) {
			// Map.entry<Integer,String> 映射项（键-值对） 有几个方法：用上面的名字entry
			// entry.getKey() ;entry.getValue(); entry.setValue();
			// map.entrySet() 返回此映射中包含的映射关系的 Set视图。
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		System.out
				.println("end---------------------------------------------------------------------------------------");
		return map;
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
}
