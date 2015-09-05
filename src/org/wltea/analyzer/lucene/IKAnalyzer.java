/*    */ package org.wltea.analyzer.lucene;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import org.apache.lucene.analysis.Analyzer;
/*    */ import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
/*    */ import org.apache.lucene.analysis.Tokenizer;
/*    */ 
/*    */ public class IKAnalyzer extends Analyzer
/*    */ {
/*    */   private boolean useSmart;
/*    */ 
/*    */   public boolean useSmart()
/*    */   {
/* 40 */     return this.useSmart;
/*    */   }
/*    */ 
/*    */   public void setUseSmart(boolean useSmart) {
/* 44 */     this.useSmart = useSmart;
/*    */   }
/*    */ 
/*    */   public IKAnalyzer()
/*    */   {
/* 53 */     this(false);
/*    */   }
/*    */ 
/*    */   public IKAnalyzer(boolean useSmart)
/*    */   {
/* 63 */     this.useSmart = useSmart;
/*    */   }
/*    */ 
/*    */   protected Analyzer.TokenStreamComponents createComponents(String fieldName, Reader reader)
/*    */   {
/* 68 */     Tokenizer tokenizer = new IKTokenizer(reader, this.useSmart);
/* 69 */     return new Analyzer.TokenStreamComponents(tokenizer);
/*    */   }
/*    */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.lucene.IKAnalyzer
 * JD-Core Version:    0.6.2
 */