package PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description: 生成PDF文件流
 * @author: 任传君
 * @date: 2022.07.08
 */
public class PDFUtils {


    public static ByteArrayOutputStream OutputPDF(String htmlStr) throws Exception {
        //创建字节流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //定义pdf文件尺寸，采用A4横切  左、右、上、下间距
        Document document = new Document(PageSize.A4, 25, 25, 15, 40);
        //创建PDF加入字节流
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);
        document.open();

        //css 样式加载
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssAppliers cssAppliers = new CssAppliersImpl(new XMLWorkerFontProvider() {
            @Override
            public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
                try {
                    //用于中文显示的Provider
                    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    Font font = new Font(bfChinese, size, style);
                    font.setColor(color);
                    return font;
                } catch (Exception e) {
                    return super.getFont(fontname, encoding, size, style);
                }
            }
        });
        //html加载,图片保存base64
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

        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(htmlStr.getBytes()));

        document.close();
        return baos;
    }
}
