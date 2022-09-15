package 字符串路径转化成tree;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.09.15
 */

public class ElectronicView implements Serializable {

    private Long id;
    private String name;
    private String categoryType;
    private String fileId;
    private List<ElectronicView> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public List<ElectronicView> getChildren() {
        return children;
    }

    public void setChildren(List<ElectronicView> children) {
        this.children = children;
    }
}
