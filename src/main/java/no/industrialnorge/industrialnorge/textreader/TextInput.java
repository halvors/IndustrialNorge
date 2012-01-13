package no.industrialnorge.industrialnorge.textreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import no.industrialnorge.industrialnorge.IndustrialNorge;

public class TextInput implements IText {
//	private final IndustrialNorge plugin;
	
	private final List<String> lines = new ArrayList<String>();
	private final List<String> chapters = new ArrayList<String>();
	private final HashMap<String, Integer> bookmarks = new HashMap<String, Integer>();

	public TextInput(IndustrialNorge plugin, String filename, boolean createFile) throws IOException {
//		this.plugin = plugin;
		
		File file = null;
		
		if (file == null || !file.exists()) {
			file = new File(plugin.getDataFolder(), filename + ".txt");
		}
		
		if (file.exists()) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
			try {
				int lineNumber = 0;
				
				while (bufferedReader.ready()) {
					String line = bufferedReader.readLine();
					
					if (line == null) {
						break;
					}
					
					if (line.length() > 0 && line.charAt(0) == '#') {
						bookmarks.put(line.substring(1).toLowerCase(Locale.ENGLISH).replaceAll("&[0-9a-f]", ""), lineNumber);
						chapters.add(line.substring(1).replace('&', '§').replace("§§", "&"));
					}
					
					lines.add(line.replace('&', '§').replace("§§", "&"));
					
					lineNumber++;
				}
			} finally {
				bufferedReader.close();
			}
		} else {
			if (createFile) {
				InputStream input = plugin.getResource(filename + ".txt");
				OutputStream output = new FileOutputStream(file);
				
				try {
					byte[] buffer = new byte[1024];
					int length = 0;
					length = input.read(buffer);
					
					while (length > 0) {
						output.write(buffer, 0, length);
						length = input.read(buffer);
					}
				} finally {
					output.close();
					input.close();
				}
				
				throw new FileNotFoundException("File " + filename + ".txt does not exist. Creating one for you.");
			}
		}
	}

	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public List<String> getChapters() {
		return chapters;
	}

	@Override
	public Map<String, Integer> getBookmarks() {
		return bookmarks;
	}
}
