package cn.com.perf.beihe.pinter.mode;

public class MyFile {
    private Integer id;

    private String fileName;

    private Long size;

    private String uploadTime;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String toString() {
        return "MyFile{id=" + this.id + ", fileName='" + this.fileName + '\'' + ", uploadTime=" + this.uploadTime + '}';
    }

}
