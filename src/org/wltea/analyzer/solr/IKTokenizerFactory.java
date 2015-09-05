/*    */ package org.wltea.analyzer.solr;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.util.Map;

/*    */ import org.apache.lucene.analysis.Tokenizer;
/*    */ import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeSource;
/*    */ import org.wltea.analyzer.lucene.IKTokenizer;
/*    */ 
/*    */ 
/*    */ public class IKTokenizerFactory extends TokenizerFactory
/*    */ {
/* 46 */   private boolean useSmart = false;
/*    */ 
/*    */   protected IKTokenizerFactory(Map<String, String> args) {
/* 49 */     super(args);
/* 50 */     String useSmartParam = (String)args.get("useSmart");
/* 51 */     this.useSmart = (useSmartParam != null ? Boolean.parseBoolean(useSmartParam) : false);
/*    */   }
/*    */ 
/*    */   public Tokenizer create(AttributeSource.AttributeFactory attributeFactory, Reader reader)
/*    */   {
/* 56 */     return new IKTokenizer(attributeFactory, reader, this.useSmart);
/*    */   }
/*    */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.solr.IKTokenizerFactory
 * JD-Core Version:    0.6.2
 */