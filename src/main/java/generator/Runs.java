package generator;

import java.io.IOException;

import org.mybatis.generator.exception.XMLParserException;

public class Runs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneratorUtil generatorUtil = new GeneratorUtil();
		try {
			generatorUtil.generator();
			System.out.println("完成");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
