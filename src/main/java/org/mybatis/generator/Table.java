package org.mybatis.generator;

import java.util.List;

public class Table {
	
	private String modelPath;
	
	private String daoPath;
	
	private String xmlPath;
	
	private String tableName;
	
	private String domainObjectName;
	
	private List<String> columnOverrideList;

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public String getDaoPath() {
		return daoPath;
	}

	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDomainObjectName() {
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}

	public List<String> getColumnOverrideList() {
		return columnOverrideList;
	}

	public void setColumnOverrideList(List<String> columnOverrideList) {
		this.columnOverrideList = columnOverrideList;
	}
	
	
}
