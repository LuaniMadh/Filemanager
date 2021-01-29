package woerterAufraeumen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Filemanager {
	/**
	 * Row 1 is the first line
	 */
	public static String getRow(String FileName, int row) {
		String string = Filemanager.getFileContent(FileName);

		String[] lines = string.split(System.getProperty("line.separator"));

		return lines[row - 1];
	}

	public static int getRowCount(String FileName) {
		String string = Filemanager.getFileContent(FileName);

		String[] lines = string.split(System.getProperty("line.separator"));

		return lines.length;
	}

	public static void saveFile(String Name, ArrayList<String> FileContent) {
		ArrayList<String> fc = FileContent;

		for (int i = 0; i < fc.size(); i++) {
			if (!(fc.get(i) instanceof String)) {
				fc.set(i, fc.get(i).toString());
			}
		}

		String string = String.join("\r\n", fc);

		Filemanager.saveFile(Name, string);
	}

	public static void saveFile(String Name, String FileContent) {
		FileOutputStream fop = null;
		File file;
		String content = FileContent;

		try {

			file = new File(Name);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			// System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addLine(String Name, int i) {
		String l = "" + i;
		Filemanager.addLine(Name, l);
	}

	public static void addLine(String Name, String line) {
		String content = getFileContent(Name);
		content += "\r\n" + line;
		saveFile(Name, content);
	}

	public static String getFileContent(String Name) {
		return getFileContent(new File(Name));
	}

	public static String getFileContent(File file) {
		InputStreamReader fis = null;
		String Fcontent = "";

		try {

			fis = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));

			// System.out.println("Total file size to read (in bytes) : "+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				// System.out.print((char) content);
				Fcontent += (char) content;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return Fcontent;
	}

	public static String[] getFileContentArray(String Name) {
		// String string = Filemanager.getFileContent(Name);
		// String lines[] = string.split("\\r?\\n");
		// return lines;
		return getFileContentArray(new File(Name));
	}

	public static String[] getFileContentArray(File file) {
		String string = Filemanager.getFileContent(file);
		String lines[] = string.split(System.lineSeparator());
		return lines;
	}

	public static ArrayList<String> getFileContentArrayList(File f) {
		String str = getFileContent(f);
		ArrayList<String> res = new ArrayList<String>(Arrays.asList(str.split(System.lineSeparator())));
		return res;
	}

	public static ArrayList<String> getFileContentArrayList(String s) {
		return getFileContentArrayList(new File(s));
	}
}
