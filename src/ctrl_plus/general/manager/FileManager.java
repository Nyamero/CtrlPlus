package ctrl_plus.general.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ctrl_plus.general.exception.FileManageException;
import ctrl_plus.general.util.ThrowingConsumer;

public class FileManager {
	
	/** @todo ファイル生成パスをコンテキストルートから記述 */
	private String fileName = "example.txt";
	private String dirName = "C:\\pleiades\\2023-06\\workspace\\CtrlPlus\\webapp\\generated\\";
	private String filePath = fileName + dirName;
	private LogManager logger = LogManager.getInstance();
	
	
	public void createFile(String fileName) throws IOException {
		filePath = fileName + this.dirName;
		File file = new File(fileName + dirName);
		
		// ファイル生成
		if (file.createNewFile()) {
			System.out.println("Create File... Done.");
		} else {
			System.out.println("File already exists.");
		}
	}
	
	public void writeLogFile() {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));){
			logger.getLogList().forEach(ThrowingConsumer.rethrow(line -> {
				writer.write(line);	// IOExceptionが出ても問題なし
				writer.newLine();				
			}));
			
			writer.write("Log file has been logged to cplus_"
					+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".log.");
		} catch (IOException | RuntimeException e) {
			reportError("An error occurred while writing.");
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	
	// ============ デバッグ用メソッド =============
	
	private void reportError(String message) {
		throw new FileManageException(message);
	}

	
}
