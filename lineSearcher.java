package LineSearcher;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class lineSearcher {
	
	private List<String> lines = null;
	
	public lineSearcher() throws IOException {
		Path fileName = Paths.get("C:/Users/chari/Downloads/Hamlet.txt");
		Charset charset = StandardCharsets.UTF_8;
		lines = Files.readAllLines(fileName, charset);
	}
	
	public List<String> getLinesAround(int lineNum) {
		List<String> returnLines = new ArrayList<>();
		int sub2 = lineNum - 2;
		int sub1 = lineNum - 1;
		int add2 = lineNum + 2;
		int add1 = lineNum + 1;
		
	
	//add preceding lines if greater than zero
		
		if(sub2 >= 0) {
			returnLines.add(lines.get(sub2));
		}
		if (sub1 >0) {
			returnLines.add(lines.get(sub1));
		}
		
		//add the requested line
		if (lineNum >= 0 && lineNum < lines.size()) {
					returnLines.add(lines.get(lineNum));
		}
		
		//add following lines if line num is smaller than text size
		if (add2 < lines.size()) {
			returnLines.add(lines.get(add2));
		}
		if (add1 < lines.size()) {
			returnLines.add(lines.get(add1));
		}
		
		return returnLines;
}

	public static void main(String[] args) {
		int lineNum = 35;
		
		try {
		lineSearcher lineSearch = new lineSearcher();
		List<String> linesAround = lineSearch.getLinesAround(lineNum);
			for (String line: linesAround) {
				System.out.println(line);
		}
			
	}
		catch (IOException e) {
			System.err.println("Error Reading File: " +e.getMessage());
		}
		
		
		
	}
	}


