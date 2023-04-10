package servlet;

public class FileDTO {
	private String path;
	private String fileName;
	private String type;
	
	public FileDTO() { }
	
	public FileDTO(String path, String fileName, String type) {
		super();
		this.path = path;
		this.fileName = fileName;
		this.type = type;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "FileDTO [path=" + path + ", fileName=" + fileName + ", type=" + type + "]";
	}
	
	
}
