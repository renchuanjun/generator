package org.mybatis.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.ColumnOverride;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


public class GeneratorUtil {
	
	public void generator() throws IOException, XMLParserException, Exception{
		List<Table> listTable = analysisXML();
		for (Table table : listTable) {
			List<String> warnings = new ArrayList<String>();
		    boolean overwrite = true;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("generatorConfig.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
		    List<Context> list = config.getContexts();
		    for (Context context : list) {
		    	//dao路径
		    	String daoPath = table.getDaoPath();
//		    	context.getJavaClientGeneratorConfiguration().getTargetPackage();
		    	context.getJavaClientGeneratorConfiguration().setTargetPackage(daoPath);
		    	//model路径
		    	String modelPath = table.getModelPath();
//		    	context.getJavaModelGeneratorConfiguration().getTargetPackage();
		    	context.getJavaModelGeneratorConfiguration().setTargetPackage(modelPath);
		    	//xml生成路径
		    	String xmlPath = table.getXmlPath();
//		    	context.getSqlMapGeneratorConfiguration().getTargetPackage();
		    	context.getSqlMapGeneratorConfiguration().setTargetPackage(xmlPath);
		    	List<TableConfiguration> tableList = context.getTableConfigurations();
		    	for (TableConfiguration tableConfiguration : tableList) {
		    		//表名
		    		String tableName = table.getTableName();
//		    		tableConfiguration.getTableName();
		    		tableConfiguration.setTableName(tableName);
		    		//实体类名称
		    		String domain = table.getDomainObjectName();
//		    		tableConfiguration.getDomainObjectName();
		    		tableConfiguration.setDomainObjectName(domain);
		    		List<String> stringList = table.getColumnOverrideList();
		    		if(null != stringList && 0 < stringList.size()){
			    		List<ColumnOverride> ColumnOverridesList = tableConfiguration.getColumnOverrides();
			    		ColumnOverride columnOverride = ColumnOverridesList.get(0);
			    		ColumnOverridesList.clear();
			    		for (String strings : stringList) {
//			    			//需要转化的表字段
//			    			columnOverride.getColumnName();
//			    			//生成xml时类型设置
//			    			columnOverride.getJdbcType();
			    			columnOverride.setJdbcType("VARCHAR");
//			    			//转化成java变量类型
//			    			columnOverride.getJavaType();	
			    			columnOverride.setJavaType("java.lang.String");
			    			ColumnOverridesList.add(columnOverride);
						}
		    		}else{
		    			List<ColumnOverride> ColumnOverridesList = tableConfiguration.getColumnOverrides();
		    			ColumnOverridesList.clear();
		    		}
		    	}

			}
		    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		    myBatisGenerator.generate(null);
		}

	
	}
	
	
	private List<Table> analysisXML() throws  Exception{
		List<Table> list = new ArrayList<Table>();
		String filePath = this.getClass().getResource("/").getPath()+"tableConfig.xml";
		System.out.println(filePath);
		File file = new File(filePath);
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		//获取根元素
		Element root = document.getRootElement();
        System.out.println("Root: " + root.getName());
        // 获取所有子元素
        List<Element> childList1 = root.elements();
        for (Element child1 : childList1) {
        	Table table = new Table();
        	String daoPath = child1.attributeValue("daoPath");
			table.setDaoPath(daoPath);
			String xmlPath = child1.attributeValue("xmlPath");
			table.setXmlPath(xmlPath);
			String modelPath = child1.attributeValue("modelPath");
			table.setModelPath(modelPath);
			String tableName = child1.attributeValue("tableName");
			table.setTableName(tableName);
			String domainObjectName = child1.attributeValue("domainObjectName");
			table.setDomainObjectName(domainObjectName);
			List<Element> childList2 = child1.elements();
			List<String> list2 = new ArrayList<String>();
			if(null != childList2 && 0 < childList2.size()){
			for (Element child2 : childList2) {
				String column = child2.attributeValue("column");
				list2.add(column);
			}
			table.setColumnOverrideList(list2);
			}
			list.add(table);
        }
       
		
        
        
        return list;
	}
}
