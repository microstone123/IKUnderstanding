/*     */ package org.wltea.analyzer.lucene;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;

/*     */ import org.apache.lucene.analysis.Tokenizer;
/*     */ import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
/*     */ import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.AttributeSource;
/*     */ import org.apache.lucene.util.AttributeSource.AttributeFactory;
/*     */ import org.wltea.analyzer.core.IKSegmenter;
/*     */ import org.wltea.analyzer.core.Lexeme;
/*     */ 
/*     */ public class IKTokenizer extends Tokenizer
/*     */ {
/*     */   private IKSegmenter _IKImplement;
/*     */   private CharTermAttribute termAtt;
/*     */   private OffsetAttribute offsetAtt;
/*     */   private int finalOffset;
/*     */ 
/*     */   public IKTokenizer(Reader in, boolean useSmart)
/*     */   {
/*  61 */     this(AttributeSource.AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY, in, useSmart);
/*     */   }
/*     */ 
/*     */   public IKTokenizer(AttributeSource.AttributeFactory factory, Reader in, boolean useSmart) {
/*  65 */     super(factory, in);
/*  66 */     this.offsetAtt = ((OffsetAttribute)addAttribute(OffsetAttribute.class));
/*  67 */     this.termAtt = ((CharTermAttribute)addAttribute(CharTermAttribute.class));
/*  68 */     this._IKImplement = new IKSegmenter(in, useSmart);
/*     */   }
/*     */ 
/*     */   public boolean incrementToken()
/*     */     throws IOException
/*     */   {
/*  77 */     clearAttributes();
/*  78 */     Lexeme nextLexeme = this._IKImplement.next();
/*  79 */     if (nextLexeme != null)
/*     */     {
/*  82 */       this.termAtt.append(nextLexeme.getLexemeText());
/*     */ 
/*  84 */       this.termAtt.setLength(nextLexeme.getLength());
/*     */ 
/*  86 */       this.offsetAtt.setOffset(nextLexeme.getBeginPosition(), nextLexeme.getEndPosition());
/*     */ 
/*  88 */       this.finalOffset = nextLexeme.getEndPosition();
/*     */ 
/*  90 */       return true;
/*     */     }
/*     */ 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public void reset(Reader input)
/*     */     throws IOException
/*     */   {
/* 102 */     super.reset();
/* 103 */     this._IKImplement.reset(input);
/*     */   }
/*     */ 
/*     */   public final void end()
/*     */   {
/* 109 */     this.offsetAtt.setOffset(this.finalOffset, this.finalOffset);
/*     */   }
/*     */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.lucene.IKTokenizer
 * JD-Core Version:    0.6.2
 */