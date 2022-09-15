package 字符串路径转化成tree;

import java.math.BigDecimal;
import java.util.Date;

public class FileInfo implements java.io.Serializable{
    /**
     * 主键
     */
    private Long id;

    /**
     * 档案ID
     */
    private Long archivesId;

    /**
     * 流水号
     */
    private String serialNo;

    /**
     * 案号;同诉讼档案案号
     */
    private String caseNo;

    /**
     * 卷内流水号
     */
    private String dossierNo;

    /**
     * 卷内目录
     */
    private String dossierCatalogue;

    /**
     * 正副卷标识（0：正卷1：副卷）
     */
    private Integer zfjbs;

    /**
     * 保存附件报文
     */
    private String body;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private BigDecimal fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 文件编号
     */
    private String fileId;

    /**
     * 增加水银后的文件ID
     */
    private String newFileId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除;1删除 0 未删除
     */
    private Byte isDeleted;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 档案ID
     */
    public Long getArchivesId() {
        return archivesId;
    }

    /**
     * 档案ID
     */
    public void setArchivesId(Long archivesId) {
        this.archivesId = archivesId;
    }

    /**
     * 流水号
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 流水号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * 案号;同诉讼档案案号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 案号;同诉讼档案案号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    /**
     * 卷内流水号
     */
    public String getDossierNo() {
        return dossierNo;
    }

    /**
     * 卷内流水号
     */
    public void setDossierNo(String dossierNo) {
        this.dossierNo = dossierNo;
    }

    /**
     * 卷内目录
     */
    public String getDossierCatalogue() {
        return dossierCatalogue;
    }

    /**
     * 卷内目录
     */
    public void setDossierCatalogue(String dossierCatalogue) {
        this.dossierCatalogue = dossierCatalogue;
    }

    /**
     * 正副卷标识（0：正卷1：副卷）
     */
    public Integer getZfjbs() {
        return zfjbs;
    }

    /**
     * 正副卷标识（0：正卷1：副卷）
     */
    public void setZfjbs(Integer zfjbs) {
        this.zfjbs = zfjbs;
    }

    /**
     * 保存附件报文
     */
    public String getBody() {
        return body;
    }

    /**
     * 保存附件报文
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件大小
     */
    public BigDecimal getFileSize() {
        return fileSize;
    }

    /**
     * 文件大小
     */
    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 页码
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 页码
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 文件编号
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 文件编号
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 增加水银后的文件ID
     */
    public String getNewFileId() {
        return newFileId;
    }

    /**
     * 增加水银后的文件ID
     */
    public void setNewFileId(String newFileId) {
        this.newFileId = newFileId;
    }

    /**
     * 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 是否删除;1删除 0 未删除
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 是否删除;1删除 0 未删除
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}