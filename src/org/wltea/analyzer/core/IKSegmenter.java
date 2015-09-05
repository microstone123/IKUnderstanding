/*     */ package org.wltea.analyzer.core;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.wltea.analyzer.cfg.Configuration;
/*     */ import org.wltea.analyzer.dic.Dictionary;
/*     */ 
/*     */ public final class IKSegmenter
/*     */ {
/*     */   private Reader input;
/*     */   private Configuration cfg;
/*     */   private AnalyzeContext context;
/*     */   private List<ISegmenter> segmenters;
/*     */   private IKArbitrator arbitrator;
/*     */ 
/*     */   public IKSegmenter(Reader input, boolean useSmart)
/*     */   {
/*  61 */     this.input = input;
/*  62 */     this.cfg = new Configuration();
/*  63 */     this.cfg.setUseSmart(useSmart);
/*  64 */     init();
/*     */   }
/*     */ 
/*     */   public IKSegmenter(Reader input, Configuration cfg)
/*     */   {
/*  74 */     this.input = input;
/*  75 */     this.cfg = cfg;
/*  76 */     init();
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/*  84 */     Dictionary.getInstance(this.cfg);
/*     */ 
/*  86 */     this.context = new AnalyzeContext(this.cfg);
/*     */ 
/*  88 */     this.segmenters = loadSegmenters();
/*     */ 
/*  90 */     this.arbitrator = new IKArbitrator();
/*     */   }
/*     */ 
/*     */   private List<ISegmenter> loadSegmenters()
/*     */   {
/*  98 */     List segmenters = new ArrayList(4);
/*     */ 
/* 100 */     segmenters.add(new LetterSegmenter());
/*     */ 
/* 102 */     segmenters.add(new CN_QuantifierSegmenter());
/*     */ 
/* 104 */     segmenters.add(new CJKSegmenter());
/* 105 */     return segmenters;
/*     */   }
/*     */ 
/*     */   public synchronized Lexeme next()
/*     */     throws IOException
/*     */   {
/* 114 */     if (this.context.hasNextResult())
/*     */     {
/* 116 */       return this.context.getNextLexeme();
/*     */     }
/*     */ 
/* 123 */     int available = this.context.fillBuffer(this.input);
/* 124 */     if (available <= 0)
/*     */     {
/* 126 */       this.context.reset();
/* 127 */       return null;
/*     */     }
/*     */ 
/* 131 */     this.context.initCursor();
/*     */     do
/*     */     {
/* 134 */       for (ISegmenter segmenter : this.segmenters) {
/* 135 */         segmenter.analyze(this.context);
/*     */       }
/*     */     }
/* 138 */     while ((!this.context.needRefillBuffer()) && (
/* 142 */       this.context.moveCursor()));
/*     */ 
/* 144 */     for (ISegmenter segmenter : this.segmenters) {
/* 145 */       segmenter.reset();
/*     */     }
/*     */ 
/* 149 */     this.arbitrator.process(this.context, this.cfg.useSmart());
/*     */ 
/* 151 */     this.context.processUnkownCJKChar();
/*     */ 
/* 153 */     this.context.markBufferOffset();
/*     */ 
/* 155 */     if (this.context.hasNextResult()) {
/* 156 */       return this.context.getNextLexeme();
/*     */     }
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   public synchronized void reset(Reader input)
/*     */   {
/* 167 */     this.input = input;
/* 168 */     this.context.reset();
/* 169 */     for (ISegmenter segmenter : this.segmenters)
/* 170 */       segmenter.reset();
/*     */   }
/*     */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.core.IKSegmenter
 * JD-Core Version:    0.6.2
 */