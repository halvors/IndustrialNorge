package no.industrialnorge.industrialnorge.textreader;

import java.util.List;
import java.util.Map;

public interface IText {
	public List<String> getLines();
	
	public List<String> getChapters();

	public Map<String, Integer> getBookmarks();
}
