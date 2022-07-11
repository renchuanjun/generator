package PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.07
 */
public class TestPDF {



    public static void main(String[] args) throws Exception {
        StringBuffer textHtml = new StringBuffer();
        File file = new File("D:/workspace/generator/src/main/resources/aaa.html");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            textHtml.append(tempString);
        }
        reader.close();
        writeToOutputStreamAsPDF(textHtml.toString());
    }

    private static void writeToOutputStreamAsPDF(String htmlStr) throws Exception {
        String targetFile = "D:/pdfDemo1.pdf";
        File targeFile = new File(targetFile);
        if (targeFile.exists()) {
            targeFile.delete();
        }

        //定义pdf文件尺寸，采用A4横切
        Document document = new Document(PageSize.A4, 25, 25, 15, 40);// 左、右、上、下间距
//        PdfWriter writer = PdfWriter.getInstance(document, baos);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(targetFile));
//        PdfPageEvent header = new PdfReportHeaderFooter("", 8, PageSize.A4);
//        writer.setPageEvent(new PdfPageEvent(maximumPageNumber));

        writer.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);
        document.open();

        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssAppliers cssAppliers = new CssAppliersImpl(new XMLWorkerFontProvider() {
            @Override
            public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
                try {
                    //用于中文显示的Provider
                    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    return new Font(bfChinese, size, style);
                } catch (Exception e) {
                    return super.getFont(fontname, encoding, size, style);
                }
            }
        });

        //html
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new AbstractImageProvider() {
            @Override
            public Image retrieve(String src) {
                //支持图片显示
                int pos = src.indexOf("base64,");
                try {
                    if (src.startsWith("data") && pos > 0) {
                        byte[] img = Base64.decode(src.substring(pos + 7));
                        return Image.getInstance(img);
                    } else if (src.startsWith("http")) {
                        return Image.getInstance(src);
                    }
                } catch (BadElementException ex) {
                    return null;
                } catch (IOException ex) {
                    return null;
                }
                return null;
            }

            @Override
            public String getImageRootPath() {
                return null;
            }
        });


        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(htmlStr.getBytes()));

        document.close();

    }
}
